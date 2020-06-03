package controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.Query;
import app.Main;
import dao.ContenidoDAO;
import dao.CursoDAO;
import dao.EjemplarDAO;
import dao.LibroDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import model.EjemplarTabla;
import pojo.Contenido;
import pojo.Curso;
import pojo.Ejemplare;
import pojo.Libro;
import service.BarcodeService;
import service.EjemplarTablaService;
import service.LoaderService;
import view.Toast;

public class LibroDetalleController implements Initializable {

	

	@FXML
	private VBox xVBoxMAIN;

	@FXML
	private TextField xTextFieldCodigo;

	@FXML
	private TextField xTextFieldNombre;

	@FXML
	private TextField xTextFieldISBN;

	@FXML
	private ComboBox<Integer> xComboBoxCursoEscolar;

	@FXML
	private ComboBox<Curso> xComboBoxCurso;

	@FXML
	private ComboBox<Contenido> xComboBoxAsignatura;

	@FXML
	private TextField xTextFieldUnidadesTotales;

	@FXML
	private TextField xTextFieldPrecio;

	@FXML
	private CheckBox xCheckBoxObsoleto;

	@FXML
	private TableView<EjemplarTabla> xTableViewEjemplar;
	
	@FXML
	private HBox xButtonAddEjemplar;

	@FXML
	private HBox xButtonDeleteEjemplar;

	private List<Curso> listaCursos = new ArrayList<>();

	@FXML
	private Text xButtonBorrarTEXT;
	
	@FXML
	private HBox xButtonEDITAR;

	@FXML
	private HBox xButtonGUARDAR;

	@FXML
	private HBox xButtonBORRAR;
	
	@FXML
	private HBox xButtonIMPRIMIR;
	
	@FXML
	private ImageView xImageView;
	
	@FXML
    private HBox xButtonIMPRIMIRseleccion;
	
	private Libro libro = null;

	@SuppressWarnings({ "rawtypes" })
	private TableColumn codigoColumn = new TableColumn("Codigo");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private TableColumn<EjemplarTabla, String> estadoColumn = new TableColumn("Estado");
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private TableColumn<EjemplarTabla, String> prestadoColumn = new TableColumn("Prestado");

	private boolean isButtonGuardarEnabled = false;

	private boolean isNuevoLibro = false;

	private EjemplarTablaService ejemplarTablaService = new EjemplarTablaService();

	private LibroDAO libroDAO = new LibroDAO();
	
	private CursoDAO cursoDAO = new CursoDAO();
	
	private ContenidoDAO contenidoDAO = new ContenidoDAO();
	
	private EjemplarDAO ejemplarDAO = new EjemplarDAO();

	private ObservableList<String> listaEstados = FXCollections.observableArrayList();
	
	private ObservableList<String> listaPrestado = FXCollections.observableArrayList();
	
	private LoaderService loaderService;
	
	
	
	public LibroDetalleController(LoaderService loaderService) {
		super();
		this.loaderService = loaderService;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		xTableViewEjemplar.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		xTableViewEjemplar.getSelectionModel().setSelectionMode( SelectionMode.MULTIPLE	);
		try {
			this.listaEstados.add("Perfecto");
			this.listaEstados.add("Regular");
			this.listaEstados.add("Mal");

			this.listaPrestado.add("No prestado");
			this.listaPrestado.add("Prestado");

			listaCursos = cursoDAO.getAll();

			xComboBoxCurso.setDisable(true);
			xComboBoxAsignatura.setDisable(true);

			rellenarCursoEscolar();

			setMostrarComboBoxAsignatura();

			setMostrarComboBoxCurso();

			setListenerComboBoxCurso();
			setListenerComboBoxCursoEscolar();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.xComboBoxAsignatura.setDisable(true);
		this.xCheckBoxObsoleto.setDisable(true);
		this.xComboBoxCursoEscolar.setDisable(true);
		this.xComboBoxCurso.setDisable(true);
		this.xCheckBoxObsoleto.setDisable(true);

	}

	public void setNuevoLibro() {
		this.xButtonIMPRIMIRseleccion.setVisible(false);
		this.xButtonAddEjemplar.setVisible(false);
		this.xButtonDeleteEjemplar.setVisible(false);
		this.isNuevoLibro = true;
		this.xButtonIMPRIMIR.setVisible(false);
		this.xButtonBorrarTEXT.setText("Cancelar");
		this.xButtonEDITAR.setVisible(false);
		isButtonGuardarEnabled = true;
		this.xButtonGUARDAR.setStyle("-fx-background-color: #00d142;");
		this.xButtonGUARDAR.setDisable(!isButtonGuardarEnabled);
		this.xComboBoxAsignatura.setDisable(false);
		this.xCheckBoxObsoleto.setDisable(false);
		this.xComboBoxCursoEscolar.setDisable(false);
		this.xComboBoxCurso.setDisable(false);

		this.xTextFieldCodigo.setEditable(true);

		this.xTextFieldISBN.setEditable(true);

		this.xTextFieldNombre.setEditable(true);
		this.xTextFieldPrecio.setEditable(true);

		this.xTextFieldPrecio.setStyle("-fx-background-color: WHITE;");
		this.xTextFieldCodigo.setStyle("-fx-background-color: WHITE;");
		this.xTextFieldISBN.setStyle("-fx-background-color: WHITE;");
		this.xTextFieldNombre.setStyle("-fx-background-color: WHITE;");

		this.xTextFieldUnidadesTotales.setText("0");
	}

	public void disableComboBoxes() {
		this.xComboBoxAsignatura.setDisable(true);
		this.xComboBoxCurso.setDisable(true);
		this.xComboBoxCursoEscolar.setDisable(true);
	}

	

	@SuppressWarnings("deprecation")
	@FXML
	void xButtonAddEjemplarCLICKED(MouseEvent event) {
		Ejemplare newEjemplare = new Ejemplare();

		newEjemplare.setEstado(0);
		newEjemplare.setPrestado(new Byte("0"));

		int id = 0;
		for (int i = 0; i < libro.getEjemplares().size(); i++) {
			String[] x = libro.getEjemplares().get(i).getId().split(libro.getId());

			if (Integer.parseInt(x[1]) > id)
				id = Integer.parseInt(x[1]);
		}
		id++;
		Formatter formatter = new Formatter();

		String numeroCeros = String.valueOf(formatter.format("%03d", id));
		
		formatter.close();

		newEjemplare.setId(libro.getId() + numeroCeros);
		newEjemplare.setCodigo(libro.getId() + numeroCeros);

		this.libro.setUnidades(this.libro.getUnidades() + 1);
		this.xTextFieldUnidadesTotales.setText(this.libro.getUnidades() + "");

		this.libro.addEjemplare(newEjemplare);

		this.libroDAO.merge(this.libro);

		this.loaderService.loadToast("Ejemplar añadido");

		reloadEjemplares();
	}

	@FXML
	void xButtonDeleteEjemplarCLICKED(MouseEvent event) {
		if (this.xTableViewEjemplar.getSelectionModel().getSelectedItem() == null) {
			this.loaderService.loadToast("Debes seleccionar un\nejemplar para borrarlo");
			return;
		}
		Ejemplare ejemplar = this.ejemplarDAO.findById(this.xTableViewEjemplar.getSelectionModel().getSelectedItem().getId());
		this.libro.removeEjemplare(	ejemplar);

		this.libro.setUnidades(this.libro.getUnidades() - 1);
		this.xTextFieldUnidadesTotales.setText(this.libro.getUnidades() + "");

		this.ejemplarDAO.delete(ejemplar);
		this.loaderService.loadToast("Ejemplar borrado");

		reloadEjemplares();

	}

	


	@FXML
	void ImprimirCLICKED(MouseEvent event) {
		
		String[] listaCodigos = new String[this.libro.getEjemplares().size()];
		for (int i = 0; i < listaCodigos.length; i++) {
			
			listaCodigos[i] = this.libro.getEjemplares().get(i).getCodigo();
		}
		imprimir(listaCodigos);

	}

	
	void reloadEjemplares() {
		this.libro = libroDAO.findById(libro.getId());

		xTableViewEjemplar.getItems().clear();
		xTableViewEjemplar.getColumns().clear();

		BarcodeService barcodeService = new BarcodeService();
		xTableViewEjemplar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				this.xImageView.setImage(
						barcodeService.convertToFxImage(barcodeService.generateImage(newSelection.getCodigo())));
			}
		});

		xTableViewEjemplar.getItems().clear();

		setTablaEjemplar();

		List<Ejemplare> listaEjemplares = new ArrayList<>();

		listaEjemplares = libro.getEjemplares();

		xTableViewEjemplar.getItems().addAll(ejemplarTablaService.converToEjemplarTabla(listaEjemplares));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTablaEjemplar() {
		codigoColumn = new TableColumn("Codigo");
		codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		estadoColumn = new TableColumn("Estado");
		estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
		estadoColumn.setCellFactory(ComboBoxTableCell
				.<EjemplarTabla, String>forTableColumn(new DefaultStringConverter(), this.listaEstados));
		
		estadoColumn.setOnEditCommit((TableColumn.CellEditEvent<EjemplarTabla, String> e) -> {
			String newValue = e.getNewValue();
			int index = e.getTablePosition().getRow();
			e.getTableView().getItems().get(index).setEstado(newValue);
		});

		prestadoColumn = new TableColumn("Prestado");
		prestadoColumn.setCellValueFactory(new PropertyValueFactory("prestado"));
		prestadoColumn
				.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), this.listaPrestado));
		
		prestadoColumn.setOnEditCommit((TableColumn.CellEditEvent<EjemplarTabla, String> e) -> {
			String newValue = e.getNewValue();
			int index = e.getTablePosition().getRow();
			e.getTableView().getItems().get(index).setPrestado(newValue);
		});
		
		xTableViewEjemplar.getColumns().addAll(codigoColumn, prestadoColumn, estadoColumn);
		xTableViewEjemplar.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		xTableViewEjemplar.setEditable(true);

		estadoColumn.setEditable(false);
		prestadoColumn.setEditable(false);
	}

	@FXML
	void BorrarCLICKED(MouseEvent event) {

		if (isNuevoLibro) {
			this.loaderService.loadListaLibros();

		} else {
			
			this.loaderService.loadConfirmacion(this.libro);
		}
	}

	@FXML
	void EditarCLICKED(MouseEvent event) {

		isButtonGuardarEnabled = true;
		this.xButtonGUARDAR.setStyle("-fx-background-color: #00d142;");
		this.xButtonGUARDAR.setDisable(!isButtonGuardarEnabled);

		this.xComboBoxAsignatura.setDisable(false);
		this.xCheckBoxObsoleto.setDisable(false);
		this.xComboBoxCursoEscolar.setDisable(false);
		this.xComboBoxCurso.setDisable(false);

		this.xTextFieldISBN.setEditable(true);

		this.xTextFieldNombre.setEditable(true);
		this.xTextFieldPrecio.setEditable(true);

		this.xTextFieldPrecio.setStyle("-fx-background-color: WHITE;");
		this.xTextFieldISBN.setStyle("-fx-background-color: WHITE;");
		this.xTextFieldNombre.setStyle("-fx-background-color: WHITE;");

		this.xButtonEDITAR.setDisable(true);
		this.xButtonEDITAR.setStyle("-fx-background-color: GRAY;");
		
		this.xButtonIMPRIMIRseleccion.setVisible(false);
		this.xButtonIMPRIMIR.setVisible(false);
	}

	@FXML
	void EditarENTERED(MouseEvent event) {
		if (!isButtonGuardarEnabled) {
			this.xButtonEDITAR.setStyle("-fx-background-color:#0fbcf9; ");
		}
	}

	@FXML
	void EditarEXITED(MouseEvent event) {
		if (!isButtonGuardarEnabled) {
			this.xButtonEDITAR.setStyle("-fx-background-color:#1f93ff; ");
		}
	}

	@SuppressWarnings("unused")
	private boolean isNumberOrEmpty(String cadena) {
		if (cadena == null || cadena.isEmpty())
			return true;
		try {
			double num = Double.parseDouble(cadena);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@FXML
	void GuardarCLICKED(MouseEvent event) {
		if (xTextFieldNombre.getText().isBlank() || xTextFieldNombre.getText().isEmpty()) {
			this.loaderService.loadToastRED("El nombre del libro no puede estar vacio");
			return;
		}
		if (xTextFieldCodigo.getText().isBlank() || xTextFieldCodigo.getText().isEmpty()) {
			this.loaderService.loadToastRED("El codigo del libro no puede estar vacio");
			return;
		}
		if (!isNumberOrEmpty(xTextFieldPrecio.getText())) {
			this.loaderService.loadToastRED("El precio del libro tiene que ser numerico");
			return;
		}
		if (this.xComboBoxAsignatura.getSelectionModel().getSelectedItem() == null) {
			this.loaderService.loadToastRED("Debes seleccionar una asignatura");
			return;
		}

		if (isNuevoLibro) {

			crearLibro();

			try {
				libroDAO.persist(this.libro);
			} catch (RuntimeException e) {
				this.loaderService.loadToastRED("No puedes usar un código ya registrado");
				return;
			}

			this.loaderService.loadToast("Libro creado con éxito");

		} else {
			estadoColumn.setEditable(false);
			prestadoColumn.setEditable(false);

			cambiarLibro();


			ejemplarTablaToEjemplare();

			libroDAO.merge(this.libro);
		}

		isButtonGuardarEnabled = false;
		this.xButtonGUARDAR.setDisable(!isButtonGuardarEnabled);
		this.xButtonGUARDAR.setStyle("-fx-background-color: GRAY;");

		this.xComboBoxAsignatura.setDisable(true);
		this.xCheckBoxObsoleto.setDisable(true);
		this.xComboBoxCursoEscolar.setDisable(true);
		this.xComboBoxCurso.setDisable(true);
		this.xTextFieldCodigo.setEditable(true);
		this.xTextFieldISBN.setEditable(true);
		this.xTextFieldNombre.setEditable(true);
		this.xTextFieldPrecio.setEditable(true);
		
		if(!isNuevoLibro) {
			this.xButtonIMPRIMIRseleccion.setVisible(true);
			this.xButtonIMPRIMIR.setVisible(true);
		}
		
		this.xTextFieldPrecio.setStyle("-fx-background-color: TRANSPARENT;");
		this.xTextFieldCodigo.setStyle("-fx-background-color: TRANSPARENT;");
		this.xTextFieldISBN.setStyle("-fx-background-color: TRANSPARENT;");
		this.xTextFieldNombre.setStyle("-fx-background-color: TRANSPARENT;");

		this.xButtonEDITAR.setStyle("-fx-background-color: #1f93ff;");
		this.xButtonEDITAR.setDisable(false);
	}

	private void cambiarLibro() {
		this.libro.setCodigo(xTextFieldCodigo.getText());
		this.libro.setIsbn(xTextFieldISBN.getText());
		this.libro.setNombre(xTextFieldNombre.getText());
		this.libro.setPrecio(Double.parseDouble(xTextFieldPrecio.getText()));
		this.libro.setUnidades(Integer.parseInt(xTextFieldUnidadesTotales.getText()));

		this.libro.setContenido(
				contenidoDAO.findById(this.xComboBoxAsignatura.getSelectionModel().getSelectedItem().getId()));

		if (this.xCheckBoxObsoleto.isSelected() == true) {
			this.libro.setObsoleto((byte) 1);
		} else {
			this.libro.setObsoleto((byte) 0);
		}
	}

	private void ejemplarTablaToEjemplare() {
		for (EjemplarTabla ejemplarTabla : this.xTableViewEjemplar.getItems()) {
			for (Ejemplare ejemplarLibro : this.libro.getEjemplares()) {
				if (ejemplarTabla.getId().equals(ejemplarLibro.getId())) {
					ejemplarLibro.setEstado(ejemplarTablaService.convertToEstadoLibro(ejemplarTabla.getEstado()));
					ejemplarLibro
							.setPrestado(ejemplarTablaService.convertToPrestadoLibro(ejemplarTabla.getPrestado()));
					ejemplarDAO.merge(ejemplarLibro);
				}
			}
		}
	}

	private void crearLibro() {
		this.libro = new Libro();
		this.libro.setId(xTextFieldCodigo.getText());
		this.libro.setCodigo(xTextFieldCodigo.getText());
		this.libro.setIsbn(xTextFieldISBN.getText());
		this.libro.setNombre(xTextFieldNombre.getText());

		if (xTextFieldPrecio.getText().isBlank() || xTextFieldPrecio.getText().isEmpty()) {
			this.libro.setPrecio(0);
		} else {
			this.libro.setPrecio(Double.parseDouble(xTextFieldPrecio.getText()));
		}
		this.libro.setUnidades(Integer.parseInt(xTextFieldUnidadesTotales.getText()));

		this.libro.setContenido(
				contenidoDAO.findById(this.xComboBoxAsignatura.getSelectionModel().getSelectedItem().getId()));
	}

	@FXML
	void GuardarENTERED(MouseEvent event) {
		if (isButtonGuardarEnabled) {
			this.xButtonGUARDAR.setStyle("-fx-background-color: #66ff66;");
		}

	}

	@FXML
	void GuardarEXITED(MouseEvent event) {
		if (isButtonGuardarEnabled) {
			this.xButtonGUARDAR.setStyle("-fx-background-color: #33cc33;");
		}

	}

	private void setMostrarComboBoxAsignatura() {
		Callback<ListView<Contenido>, ListCell<Contenido>> cellFactory = new Callback<ListView<Contenido>, ListCell<Contenido>>() {

			@Override
			public ListCell<Contenido> call(ListView<Contenido> l) {
				return new ListCell<Contenido>() {

					@Override
					protected void updateItem(Contenido item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							setText(item.getCodigo() + " - " + item.getNombreCas());
						}
					}
				};
			}
		};
		xComboBoxAsignatura.setButtonCell(cellFactory.call(null));
		xComboBoxAsignatura.setCellFactory(cellFactory);
	}

	private void setMostrarComboBoxCurso() {
		Callback<ListView<Curso>, ListCell<Curso>> cellFactory = new Callback<ListView<Curso>, ListCell<Curso>>() {

			@Override
			public ListCell<Curso> call(ListView<Curso> l) {
				return new ListCell<Curso>() {

					@Override
					protected void updateItem(Curso item, boolean empty) {
						super.updateItem(item, empty);
						if (item == null || empty) {
							setGraphic(null);
						} else {
							setText(item.getAbreviatura() + " - " + item.getNombreCas());
						}
					}
				};
			}
		};
		xComboBoxCurso.setButtonCell(cellFactory.call(null));
		xComboBoxCurso.setCellFactory(cellFactory);
	}

	private void setListenerComboBoxCurso() {
		xComboBoxCurso.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				xComboBoxAsignatura.getItems().clear();
				xComboBoxAsignatura.getItems().addAll(((Curso) newSelection).getContenidos());
				xComboBoxAsignatura.setDisable(false);
			} else {
				xComboBoxAsignatura.setDisable(true);
			}
		});
	}

	private void setListenerComboBoxCursoEscolar() {
		xComboBoxCursoEscolar.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						xComboBoxCurso.getItems().clear();
						xComboBoxCurso.setDisable(false);
						for (Curso curso : listaCursos) {
							if (curso.getCursoEscolar() == newSelection) {
								xComboBoxCurso.getItems().add(curso);
							}
						}
					}
				});
	}

	private void rellenarCursoEscolar() {
		HashSet<Integer> listaYears = new HashSet<>();
		for (Curso curso : listaCursos) {
			listaYears.add(curso.getCursoEscolar());
		}
		Iterator<Integer> it = listaYears.iterator();

		xComboBoxCursoEscolar.getItems().clear();
		while (it.hasNext()) {
			int year = it.next();
			xComboBoxCursoEscolar.getItems().add(year);
		}
	}

	

	public void setLibro(Libro libroViejo) {

		try {
			isNuevoLibro = false;
			this.libro = libroDAO.findById(libroViejo.getId());

			rellenarTextField();

			xComboBoxCursoEscolar.getSelectionModel().select(
					xComboBoxCursoEscolar.getItems().indexOf(libro.getContenido().getCursoBean().getCursoEscolar()));

			int cursoIndex = -1;
			for (Curso curso : xComboBoxCurso.getItems()) {
				cursoIndex++;
				if (curso.getId().equals(libro.getContenido().getCursoBean().getId())) {
					break;
				}

			}

			xComboBoxCurso.getSelectionModel().select(cursoIndex);

			int asignaturaIndex = -1;
			for (Contenido asignatura : xComboBoxAsignatura.getItems()) {
				asignaturaIndex++;
				if (asignatura.getId() == libro.getContenido().getId()) {
					break;
				}

			}

			xComboBoxAsignatura.getSelectionModel().select(asignaturaIndex);

			if (libro.getObsoleto() != 0) {
				xCheckBoxObsoleto.setSelected(true);
			}

			reloadEjemplares();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void rellenarTextField() {
		xTextFieldCodigo.setText(libro.getCodigo());
		xTextFieldISBN.setText(libro.getIsbn());
		xTextFieldNombre.setText(libro.getNombre());
		xTextFieldPrecio.setText("" + libro.getPrecio());
		xTextFieldUnidadesTotales.setText("" + libro.getUnidades());
	}
	@FXML
    void ImprimirSeleccionCLICKED(MouseEvent event) {
		List<EjemplarTabla> listaEjemplares = xTableViewEjemplar.getSelectionModel().getSelectedItems();
		if(listaEjemplares == null || listaEjemplares.isEmpty()) {
			this.loaderService.loadToastRED("No hay ejemplares seleccionados");
			return;
		}
		
		
		String[] listaCodigos = getCodigosEjemplares(listaEjemplares);
		
		imprimir(listaCodigos);
		
    }

	private String[] getCodigosEjemplares(List<EjemplarTabla> listaEjemplares) {
		String[] listaCodigos = new String[listaEjemplares.size()];
		for (int i = 0; i < listaCodigos.length; i++) {
			listaCodigos[i] = listaEjemplares.get(i).getCodigo();
		}
		return listaCodigos;
	}
	
	void imprimir(String[] listaCodigos) {
		if(listaCodigos.length < 1) {
			this.loaderService.loadToastRED("Este libro no tiene ningun ejemplar");
			return;
		}
		
		this.loaderService.loadImpresion(libro, listaCodigos);
	}

	
	
	
	

}
