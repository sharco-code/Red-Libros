package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import dao.AlumnoDAO;
import dao.CursoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import pojo.Alumno;
import pojo.Curso;
import service.LoaderService;

public class DevolucionesController implements Initializable {

	@FXML
    private VBox xVBoxMAIN;

	@FXML
	private TableView<Alumno> xTableMain;

	@FXML
	private ComboBox<Curso> xComboBoxCurso;

	@FXML
	private ComboBox<Integer> xComboBoxCursoEscolar;

	private List<Curso> listaCursos = new ArrayList<>();

	@FXML
	private RadioButton xRadioButtonNIA;

	@FXML
	private RadioButton xRadioButtonEXPEDIENTE;

	@FXML
	private TextField xTextFieldSearch;

	private int radioButtonSelected = 1; // 1: NIA, 2: Expediente

	private CursoDAO cursoDAO = new CursoDAO();
	
	private AlumnoDAO alumnoDAO = new AlumnoDAO();
	
	private List<Alumno> listaAlumnos = new ArrayList<>();

	private List<Alumno> alumnosFiltrados = new ArrayList<>();

	private LoaderService loaderService;
	
	

	public DevolucionesController(LoaderService loaderService) {
		super();
		this.loaderService = loaderService;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				filtrar(newValue);
			}
		});
		
		try {
			listaCursos = cursoDAO.getAll();

			xComboBoxCurso.setDisable(true);

			rellenarCursoEscolar();

			setMostrarComboBoxCurso();

			setListenerComboBoxCurso();
			
			setListenerComboBoxCursoEscolar();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@FXML
	void xRadioButtonEXPEDIENTE_Action(ActionEvent event) {
		this.xRadioButtonNIA.setSelected(false);
		this.xTextFieldSearch.setText("");

		this.xTextFieldSearch.setPromptText("Buscar por Expediente");
		radioButtonSelected = 2;
	}

	@FXML
	void xRadioButtonNIA_Action(ActionEvent event) {
		this.xRadioButtonEXPEDIENTE.setSelected(false);
		this.xTextFieldSearch.setText("");

		this.xTextFieldSearch.setPromptText("Buscar por NIA");
		radioButtonSelected = 1;
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
			alumnosFiltrados = listaAlumnos.stream().filter(alumno -> alumno.getCursoBean().getId().contains(newSelection.getId())).collect((Collectors.toList()));
			xTableMain.getItems().clear();
			xTableMain.getItems().addAll(alumnosFiltrados);
			
		});
		
	}

	public void reload() {

		xTableMain.getSelectionModel().selectedItemProperty().addListener((obs, oldAlumno, newAlumno) -> {
			if (newAlumno != null) {
				this.loaderService.loadDevolucionDetalle(newAlumno);
			}
		});

		xTableMain.getItems().clear();

		setTabla();

		getAlumnos();

		xTableMain.getItems().addAll(listaAlumnos);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setTabla() {
		TableColumn apellido1Column = new TableColumn("Primer apellido");
		apellido1Column.setCellValueFactory(new PropertyValueFactory("apellido1"));

		apellido1Column.setMaxWidth(1300);

		TableColumn apellido2Column = new TableColumn("Segundo apellido");
		apellido2Column.setCellValueFactory(new PropertyValueFactory("apellido2"));
		apellido2Column.setMaxWidth(1300);

		TableColumn nombreColumn = new TableColumn("Nombre");
		nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
		nombreColumn.setMaxWidth(1900);
		
		TableColumn niaColumn = new TableColumn("NIA");
		niaColumn.setCellValueFactory(new PropertyValueFactory("nia"));
		niaColumn.setMaxWidth(900);
		
		TableColumn expedienteColumn = new TableColumn("Expediente");
		expedienteColumn.setCellValueFactory(new PropertyValueFactory("expediente"));

		xTableMain.getColumns().addAll(apellido1Column, apellido2Column, nombreColumn, niaColumn, expedienteColumn);
		xTableMain.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private void getAlumnos() {
		listaAlumnos = alumnoDAO.getAll();
	}

	public void filtrar(String newValue) {
		if(radioButtonSelected==1) {
			filtrarPorNia(newValue);
		}
		else {
			filtrarPorExpediente(newValue);
		}
	}
	
	public void filtrarPorNia(String newValue) {
		alumnosFiltrados = listaAlumnos.stream().filter(alumno -> alumno.getNia().contains(newValue))
				.collect((Collectors.toList()));
		
		xTableMain.getItems().clear();
		xTableMain.getItems().addAll(alumnosFiltrados);

	}
	
	public void filtrarPorExpediente(String newValue) {
		alumnosFiltrados = listaAlumnos.stream().filter(alumno -> alumno.getExpediente().contains(newValue))
				.collect((Collectors.toList()));
		
		xTableMain.getItems().clear();
		xTableMain.getItems().addAll(alumnosFiltrados);

	}
	
	

}