package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.EjemplarHistorial;
import model.HistorialTabla;
import pojo.Ejemplare;
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

	private int radioButtonSelected = 1; // 1: ejemplar, 2: libro

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
		this.xTableEjemplare.setPlaceholder(new Label("No hay ejemplares"));
		this.xTableHistorial.setPlaceholder(new Label("No hay historial"));;

	}
	
	
	@FXML
	void xRadioButtonEjemplar_Action(ActionEvent event) {
		this.xRadioButtonEjemplar.setSelected(false);
		this.xTextFieldSearch.setText("");
		
		this.xTextFieldSearch.setPromptText("Buscar por ejemplar");
		radioButtonSelected = 1;
	}

	@FXML
	void xRadioButtonLibro_Action(ActionEvent event) {
		this.xRadioButtonLibro.setSelected(false);
		this.xTextFieldSearch.setText("");
		
		this.xTextFieldSearch.setPromptText("Buscar por libro");
		radioButtonSelected = 2;
		
	}
	
	public void reload(){
		xTableEjemplare.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				this.setHistorialTable(newSelection.getEjemplar());
				this.xTextEJEMPLAR.setText(newSelection.getEjemplar().getCodigo()+" - "+newSelection.getNombreLibro());
				this.xTextESTADO.setText(HistorialService.prestadoTabla(newSelection.getEjemplar())+"");
			}
		});

		xTableEjemplare.getItems().clear();
		
		setTablaEjemplar();

		this.listaEjemplares = this.historialService.getEjemplares();

		xTableEjemplare.getItems().addAll(listaEjemplares);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTablaEjemplar() {
		TableColumn ejemplarColumn = new TableColumn("Ejemplar");
		ejemplarColumn.setCellValueFactory(new PropertyValueFactory<>("codigoEjemplar"));

		TableColumn libroColumn = new TableColumn("Libro");
		libroColumn.setCellValueFactory(new PropertyValueFactory("nombreLibro"));
		
		TableColumn estadoColumn = new TableColumn("Estado actual");
		estadoColumn.setCellValueFactory(new PropertyValueFactory("estado"));
	

		xTableEjemplare.getColumns().addAll(ejemplarColumn, libroColumn,estadoColumn);
		xTableEjemplare.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setHistorialTable(Ejemplare ejemplar) {
		
		List<HistorialTabla> listaHistorialTabla = new ArrayList<>();
		
		TableColumn niaColumn = new TableColumn("NIA");
		niaColumn.setCellValueFactory(new PropertyValueFactory("nia"));
		niaColumn.setMaxWidth(2000);
		
		TableColumn inicialColumn = new TableColumn("Estado inicial");
		inicialColumn.setCellValueFactory(new PropertyValueFactory("estado_inicial"));
		
		TableColumn finalColumn = new TableColumn("Estado final");
		finalColumn.setCellValueFactory(new PropertyValueFactory("estado_final"));
		
		TableColumn nombreColumn = new TableColumn("Se entregó a");
		nombreColumn.setCellValueFactory(new PropertyValueFactory("nombreCompleto"));
		
		TableColumn fechaEntregaColumn = new TableColumn("Fecha de entrega");
		fechaEntregaColumn.setCellValueFactory(new PropertyValueFactory("fechaEntrega"));
		
		TableColumn fechaDevolucionColumn = new TableColumn("Fecha de devolucion");
		fechaDevolucionColumn.setCellValueFactory(new PropertyValueFactory("fechaDevolucion"));
		
		xTableHistorial.getColumns().clear();
		xTableHistorial.getColumns().addAll(nombreColumn,niaColumn,inicialColumn,finalColumn,fechaEntregaColumn,fechaDevolucionColumn);
		xTableHistorial.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			
		listaHistorialTabla = this.historialService.getHistorial(ejemplar);
		
		xTableHistorial.getItems().clear();
		xTableHistorial.getItems().addAll(listaHistorialTabla);
	}
	
	
	private void filtrar(String newValue) {
		if(radioButtonSelected==1) filtrarPorEjemplar(newValue);
		else filtrarPorLibro(newValue);
	}
	
	
	private void filtrarPorEjemplar(String newValue) {
		listaEjemplaresFiltrados = listaEjemplares.stream().filter(ejemplar -> ejemplar.getCodigoEjemplar().contains(newValue))
				.collect((Collectors.toList()));
		xTableEjemplare.getItems().clear();
		xTableEjemplare.getItems().addAll(listaEjemplaresFiltrados);

	}

	private void filtrarPorLibro(String newValue) {
		listaEjemplaresFiltrados = listaEjemplares.stream().filter(ejemplar -> ejemplar.getNombreLibro().contains(newValue))
				.collect((Collectors.toList()));
		xTableEjemplare.getItems().clear();
		xTableEjemplare.getItems().addAll(listaEjemplaresFiltrados);

	}
	
}
