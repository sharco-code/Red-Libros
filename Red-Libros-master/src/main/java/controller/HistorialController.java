package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.EjemplarHistorial;
import model.HistorialTabla;
import pojo.Ejemplare;
import pojo.Libro;
import service.HistorialService;

public class HistorialController implements Initializable{

	@FXML
    private Text xTextEJEMPLAR;

    @FXML
    private Text xTextESTADO;
    
	@FXML
	private TableView<EjemplarHistorial> xTableEjemplare;

	@FXML
	private RadioButton xRadioButtonEjemplar;

	@FXML
	private RadioButton xRadioButtonLibro;

	@FXML
	private TextField xTextFieldSearch;
	
	@FXML
	private TableView<HistorialTabla> xTableHistorial;

	private int radioButton_Selected = 1; // 1: ejemplar, 2: libro

	private HistorialService historialService;
	
	private List<EjemplarHistorial> listaEjemplares;
	private List<EjemplarHistorial> listaEjemplaresFiltrados;
	
	
	public HistorialController() {
		this.historialService = new HistorialService();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				filtrar(newValue);
			}
		});

	}
	
	
	@FXML
	void xRadioButtonEjemplar_Action(ActionEvent event) {
		this.xRadioButtonEjemplar.setSelected(false);
		this.xTextFieldSearch.setText("");
		
		this.xTextFieldSearch.setPromptText("Buscar por ejemplar");
		radioButton_Selected = 1;
	}

	@FXML
	void xRadioButtonLibro_Action(ActionEvent event) {
		this.xRadioButtonLibro.setSelected(false);
		this.xTextFieldSearch.setText("");
		
		this.xTextFieldSearch.setPromptText("Buscar por libro");
		radioButton_Selected = 2;
		
	}
	
	public void reload() throws SQLException, Exception {
		xTableEjemplare.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				this.setHistorialTable(newSelection.getEjemplar());
				this.xTextEJEMPLAR.setText(newSelection.getEjemplar().getCodigo()+" - "+newSelection.getNombreLibro());
				this.xTextESTADO.setText(HistorialService.prestadoTabla(newSelection.getEjemplar())+"");
			}
		});

		xTableEjemplare.getItems().clear();
		
		TableColumn ejemplarColumn = new TableColumn("Ejemplar");
		ejemplarColumn.setCellValueFactory(new PropertyValueFactory<>("codigoEjemplar"));

		TableColumn libroColumn = new TableColumn("Libro");
		libroColumn.setCellValueFactory(new PropertyValueFactory("nombreLibro"));
		
		TableColumn estadoColumn = new TableColumn("Estado actual");
		estadoColumn.setCellValueFactory(new PropertyValueFactory("estado"));
	

		xTableEjemplare.getColumns().addAll(ejemplarColumn, libroColumn,estadoColumn);
		xTableEjemplare.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		this.listaEjemplares = this.historialService.getEjemplares();

		xTableEjemplare.getItems().addAll(listaEjemplares);

	}
	
	private void setHistorialTable(Ejemplare ejemplar) {
		
		List<HistorialTabla> listaHistorialTabla = new ArrayList<>();
		xTableHistorial.getItems().clear();
		
		TableColumn cursoColumn = new TableColumn("Curso");
		cursoColumn.setCellValueFactory(new PropertyValueFactory<>("curso"));
		cursoColumn.setMaxWidth(1000);
		
		TableColumn niaColumn = new TableColumn("NIA");
		niaColumn.setCellValueFactory(new PropertyValueFactory("nia"));
		niaColumn.setMaxWidth(2000);
		
		TableColumn inicialColumn = new TableColumn("Estado inicial");
		inicialColumn.setCellValueFactory(new PropertyValueFactory("estado_inicial"));
		
		TableColumn finalColumn = new TableColumn("Estado final");
		finalColumn.setCellValueFactory(new PropertyValueFactory("estado_final"));
		
		TableColumn nombreColumn = new TableColumn("Nombre completo");
		nombreColumn.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
		
		TableColumn fechaDevolucionColumn = new TableColumn("Fecha de devolucion");
		fechaDevolucionColumn.setCellValueFactory(new PropertyValueFactory("fechaDevolucion"));
		xTableHistorial.getColumns().clear();
		xTableHistorial.getColumns().addAll(cursoColumn, niaColumn,nombreColumn,inicialColumn,finalColumn,fechaDevolucionColumn);
		xTableHistorial.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		listaHistorialTabla = this.historialService.getHistorial(ejemplar);

		xTableHistorial.getItems().addAll(listaHistorialTabla);
	}
	
	
	private void filtrar(String newValue) {
		if(radioButton_Selected==1) filtrarPorEjemplar(newValue);
		else filtrarPorLibro(newValue);
	}
	
	
	private void filtrarPorEjemplar(String newValue) {
		// TODO Auto-generated method stub
		listaEjemplaresFiltrados = listaEjemplares.stream().filter(ejemplar -> ejemplar.getCodigoEjemplar().contains(newValue))
				.collect((Collectors.toList()));
		xTableEjemplare.getItems().clear();
		xTableEjemplare.getItems().addAll(listaEjemplaresFiltrados);

	}

	private void filtrarPorLibro(String newValue) {
		// TODO Auto-generated method stub
		listaEjemplaresFiltrados = listaEjemplares.stream().filter(ejemplar -> ejemplar.getNombreLibro().contains(newValue))
				.collect((Collectors.toList()));
		xTableEjemplare.getItems().clear();
		xTableEjemplare.getItems().addAll(listaEjemplaresFiltrados);

	}
	
}
