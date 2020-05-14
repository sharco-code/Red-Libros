package controller;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import model.EjemplarTabla;
import pojo.Contenido;
import pojo.Curso;
import pojo.Ejemplare;
import pojo.Libro;
import service.BarcodeService;
import service.EjemplarTablaService;
import service.PdfService;
import utiles.hibernate.UtilesHibernate;
import view.Toast;

public class LibroDetalleController implements Initializable {

	private Libro libro = null;

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

	private List<Curso> listaCursos = new ArrayList<>();

	@FXML
	private Text xButtonBorrarTEXT;

	private boolean isNuevoLibro = false;

	private LibrosController librosController;
	
	private EjemplarTablaService ejemplarTablaService = new EjemplarTablaService();

	private LibroDAO libroDAO = new LibroDAO();
	private CursoDAO cursoDAO = new CursoDAO();
	private ContenidoDAO contenidoDAO = new ContenidoDAO();
	private EjemplarDAO ejemplarDAO = new EjemplarDAO();
	
	private ObservableList<String> listaEstados =  FXCollections.observableArrayList();
	private ObservableList<String> listaPrestado =  FXCollections.observableArrayList();

	public void setLibrosController(LibrosController librosController) {
		this.librosController = librosController;
	}

	public void setNuevoLibro() {
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

	@FXML
	private HBox xButtonAddEjemplar;

	@FXML
	private HBox xButtonDeleteEjemplar;

	@FXML
	void xButtonAddEjemplarCLICKED(MouseEvent event) {
		Ejemplare newEjemplare = new Ejemplare();

		newEjemplare.setEstado(0);
		newEjemplare.setPrestado(new Byte("0"));

		
		int id = 0;
		for (int i = 0; i < libro.getEjemplares().size(); i++) {
			String[] x = libro.getEjemplares().get(i).getId().split(libro.getId());
			
			if( Integer.parseInt(x[1]) > id ) id = Integer.parseInt(x[1]);
		}
		id++;
		Formatter obj = new Formatter();

        String numeroCeros = String.valueOf(obj.format("%03d", id));
        
		newEjemplare.setId( libro.getId()+numeroCeros );
		newEjemplare.setCodigo(libro.getId()+numeroCeros  );

		this.libro.setUnidades(this.libro.getUnidades() + 1);
		this.xTextFieldUnidadesTotales.setText(this.libro.getUnidades() + "");
		
		this.libro.addEjemplare(newEjemplare);

		this.libroDAO.merge(this.libro);
		
		showToast("Ejemplar añadido");

		reloadEjemplares();
	}

	@FXML
	void xButtonDeleteEjemplarCLICKED(MouseEvent event) {
		if (this.xTableViewEjemplar.getSelectionModel().getSelectedItem() == null) {
			showToast("Debes seleccionar un\nejemplar para borrarlo");
		} else {
			
			System.out.println(this.libro.getId());
			this.libro.removeEjemplare(this.ejemplarDAO.findById(this.xTableViewEjemplar.getSelectionModel().getSelectedItem().getId()));

			this.libro.setUnidades(this.libro.getUnidades() - 1);
			this.xTextFieldUnidadesTotales.setText(this.libro.getUnidades() + "");


			this.libroDAO.merge(this.libro);
			this.ejemplarDAO.delete(this.ejemplarDAO.findById(this.xTableViewEjemplar.getSelectionModel().getSelectedItem().getId()));
			showToast("Ejemplar borrado");
			
			reloadEjemplares();
			
		}
		

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		xTableViewEjemplar.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
			// TODO: handle exception
			e.printStackTrace();
		}
		this.xComboBoxAsignatura.setDisable(true);
		this.xCheckBoxObsoleto.setDisable(true);
		this.xComboBoxCursoEscolar.setDisable(true);
		this.xComboBoxCurso.setDisable(true);
		this.xCheckBoxObsoleto.setDisable(true);

	}

	@FXML
	private HBox xButtonEDITAR;

	@FXML
	private HBox xButtonGUARDAR;

	@FXML
	private HBox xButtonBORRAR;

	private boolean isButtonGuardarEnabled = false;

	@FXML
	private HBox xButtonIMPRIMIR;

	void imprimir(String ruta) {
		PdfService pdfService = new PdfService();
		BarcodeService barcodeService = new BarcodeService();

		List<com.itextpdf.text.Image> codigos = new ArrayList<>();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int i = 0; i < libro.getEjemplares().size(); i++) {
				ImageIO.write(barcodeService.generateImage(libro.getEjemplares().get(i).getCodigo()), "png", baos);
				codigos.add(com.itextpdf.text.Image.getInstance(baos.toByteArray()));
				baos.reset();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			pdfService.createPDF(codigos, ruta);
			showToast("PDF generado corectamente");
			System.out.println(ruta + "\\barcodes.pdf");
			File pdfFile = new File(ruta + "\\barcodes.pdf");
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}

			} else {
				System.out.println("File is not exists!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			showToast("No se ha podido generar el PDF");
		}
	}

	@FXML
	void ImprimirCLICKED(MouseEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("src"));

		File selectedDirectory = directoryChooser.showDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		imprimir(selectedDirectory.getAbsolutePath());

	}

	@FXML
	void ImprimirENTERED(MouseEvent event) {

	}

	@FXML
	void ImprimirEXITED(MouseEvent event) {

	}

	@FXML
	private ImageView xImageView;
	
	@SuppressWarnings({ "rawtypes" })
	private TableColumn codigoColumn = new TableColumn("Codigo");
	@SuppressWarnings("unchecked")
	private TableColumn<EjemplarTabla,String> estadoColumn = new TableColumn("Estado");
	@SuppressWarnings("unchecked")
	private TableColumn<EjemplarTabla, String> prestadoColumn = new TableColumn("Prestado");
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	void reloadEjemplares() {
		// cada vez que se llama la funcion, estas dos lineas es para eliminar los
		// elemenots que hay, si no saldrán duplicados
		
		
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

		codigoColumn = new TableColumn("Codigo");
		codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));

		estadoColumn = new TableColumn("Estado");
		estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
		estadoColumn.setCellFactory(ComboBoxTableCell.<EjemplarTabla,String>forTableColumn(new DefaultStringConverter(),this.listaEstados));
		estadoColumn.setOnEditCommit(( TableColumn.CellEditEvent<EjemplarTabla, String> e ) ->{
			String newValue = e.getNewValue();
			int index = e.getTablePosition().getRow();
			e.getTableView().getItems().get( index ).setEstado(newValue);
		});

		prestadoColumn = new TableColumn("Prestado");
		prestadoColumn.setCellValueFactory(new PropertyValueFactory("prestado"));
		prestadoColumn.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(),this.listaPrestado));
		prestadoColumn.setOnEditCommit(( TableColumn.CellEditEvent<EjemplarTabla, String> e ) ->{
			String newValue = e.getNewValue();
			int index = e.getTablePosition().getRow();
			e.getTableView().getItems().get( index ).setPrestado(newValue);
		});
		xTableViewEjemplar.getColumns().addAll(codigoColumn, prestadoColumn, estadoColumn);
		xTableViewEjemplar.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		xTableViewEjemplar.setEditable(true);

		
		estadoColumn.setEditable(false);
		prestadoColumn.setEditable(false);
		
		List<Ejemplare> listaEjemplares = new ArrayList<>();

		listaEjemplares = libro.getEjemplares();

		xTableViewEjemplar.getItems().addAll(ejemplarTablaService.converToEjemplarTabla(listaEjemplares));
	}

	@FXML
	void BorrarCLICKED(MouseEvent event) {

		if (isNuevoLibro) {
			// Si es nuevo libro: cancelar y vuelve atras
			this.xVBoxMAIN.getChildren().clear();
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/librosComponent.fxml"));
				loader.setController(this.librosController);
				VBox vbox = (VBox) loader.load();

				VBox.setVgrow(vbox, Priority.ALWAYS);

				this.xVBoxMAIN.getChildren().add(vbox);

				this.librosController.reload();
			} catch (Exception e) {
				e.printStackTrace();

			}

		} else {
			// Si es ver un libro ya existente: borrar

			// ventana de confirmacion
			this.xVBoxMAIN.getChildren().clear();
			try {
				// le paso el libro por si tiene que volver, para actualizarlo o para borrarlo
				ConfirmacionController confirmacionController = new ConfirmacionController();
				confirmacionController.setLibro(this.libro);
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/confirmacionComponent.fxml"));
				loader.setController(confirmacionController);
				VBox vbox = (VBox) loader.load();

				VBox.setVgrow(vbox, Priority.ALWAYS);

				this.xVBoxMAIN.getChildren().add(vbox);

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	@FXML
	void EditarCLICKED(MouseEvent event) {
		
		estadoColumn.setEditable(true);
		prestadoColumn.setEditable(true);
		
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

	private boolean isNumberOrEmpty(String cadena) {
		if(cadena.isEmpty() || cadena.isEmpty() ) return true;
		try {
			double num = Double.parseDouble(cadena);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	@FXML
	void GuardarCLICKED(MouseEvent event) {
		if(xTextFieldNombre.getText().isBlank() || xTextFieldNombre.getText().isEmpty()) {
			showToastRED("El nombre del libro no puede estar vacio");
			return;
		}
		if(xTextFieldCodigo.getText().isBlank() || xTextFieldCodigo.getText().isEmpty()) {
			showToastRED("El codigo del libro no puede estar vacio");
			return;
		}
		if(!isNumberOrEmpty(xTextFieldPrecio.getText())) {
			showToastRED("El precio del libro tiene que ser numerico");
			return;
		}
		if(this.xComboBoxAsignatura.getSelectionModel().getSelectedItem() == null) {
			showToastRED("Debes seleccionar una asignatura");
			return;
		}
		
		// Si es aÃ±adir o actualizar uno existente
		if (isNuevoLibro) {
			
			this.libro = new Libro();
			this.libro.setId(xTextFieldCodigo.getText());
			this.libro.setCodigo(xTextFieldCodigo.getText());
			this.libro.setIsbn(xTextFieldISBN.getText());
			this.libro.setNombre(xTextFieldNombre.getText());
			
			//si no pone precio, por defecto se pone a 0
			if(xTextFieldPrecio.getText().isBlank() || xTextFieldPrecio.getText().isEmpty()) {
				this.libro.setPrecio(0);
			} else {
				this.libro.setPrecio(Double.parseDouble(xTextFieldPrecio.getText()));
			}
			this.libro.setUnidades(Integer.parseInt(xTextFieldUnidadesTotales.getText()));

			this.libro.setContenido(
					contenidoDAO.findById(this.xComboBoxAsignatura.getSelectionModel().getSelectedItem().getId()));

			try {
				libroDAO.persist(this.libro);
			} catch (RuntimeException e) {
				showToastRED("No puedes usar un código ya registrado");
				return;
			}
			
			
			showToast("Libro creado con éxito");

		} else {
			estadoColumn.setEditable(false);
			prestadoColumn.setEditable(false);
			
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
			
			//CAMBIAR DE ejemplarTAbla a ejemplare
			
			for(EjemplarTabla ejemplarTabla:this.xTableViewEjemplar.getItems()) {
				for(Ejemplare ejemplarLibro:this.libro.getEjemplares()) {
					if(ejemplarTabla.getId().equals(ejemplarLibro.getId())) {
						ejemplarLibro.setEstado(ejemplarTablaService.convertToEstadoLibro(ejemplarTabla.getEstado()));
						ejemplarLibro.setPrestado(ejemplarTablaService.convertToPrestadoLibro(ejemplarTabla.getPrestado()));
						ejemplarDAO.merge(ejemplarLibro);
					}
				}
			}
			

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

		this.xTextFieldPrecio.setStyle("-fx-background-color: TRANSPARENT;");
		this.xTextFieldCodigo.setStyle("-fx-background-color: TRANSPARENT;");
		this.xTextFieldISBN.setStyle("-fx-background-color: TRANSPARENT;");
		this.xTextFieldNombre.setStyle("-fx-background-color: TRANSPARENT;");

		this.xButtonEDITAR.setStyle("-fx-background-color: #1f93ff;");
		this.xButtonEDITAR.setDisable(false);
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

	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; // 3.5 seconds
		int fadeInTime = 150; // 0.5 seconds
		int fadeOutTime = 300; // 0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}

	public void setLibro(Libro libroViejo) {

		try {
			isNuevoLibro = false;
			this.libro = libroDAO.findById(libroViejo.getId());

			xTextFieldCodigo.setText(libro.getCodigo());
			xTextFieldISBN.setText(libro.getIsbn());
			xTextFieldNombre.setText(libro.getNombre());
			xTextFieldPrecio.setText("" + libro.getPrecio());
			xTextFieldUnidadesTotales.setText("" + libro.getUnidades());

			xComboBoxCursoEscolar.getSelectionModel().select(
					xComboBoxCursoEscolar.getItems().indexOf(libro.getContenido().getCursoBean().getCursoEscolar()));

			// Hay que hacerlo asi por que la instacia es de otra sesion entonces no
			// coinciden cuando haces un == (Por la serializacion)
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
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	private void showToastRED(String toastMsg) {
		int toastMsgTime = 1000; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeTextRED(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}

}
