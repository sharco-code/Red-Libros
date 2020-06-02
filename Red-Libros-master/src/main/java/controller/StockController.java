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
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.StockTabla;
import pojo.Libro;
import service.StockTablaService;
import dao.LibroDAO;

public class StockController implements Initializable {

	@FXML
    private TextField xTextFieldUnidades;

    @FXML
    private TextField xTextFieldNumeroMatriculas;

    @FXML
    private TextField xTextFieldEntregados;

    @FXML
    private TextField xTextFieldNoEntregados;

	@FXML
	private TableView<StockTabla> xTableLibros;

	@FXML
	private RadioButton xRadioButtonNOMBRE;

	@FXML
	private RadioButton xRadioButtonCODIGO;

	@FXML
	private VBox x;

	@FXML
	private ImageView xImageView;

	@FXML
	private TextField xTextFieldSearch;
	
	@FXML
	private VBox xVBoxMAIN;

	private int radioButtonSelected = 1; // 1: NOMBRE, 2: CODIGO

	private List<Libro> listaLibros = new ArrayList<>();

	private List<Libro> librosFiltrados = new ArrayList<>();

	private StockTablaService stockTablaService = new StockTablaService();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				filtrar(newValue);
			}
		});

	}
	
	@FXML
	void xRadioButtonCODIGO_Action(ActionEvent event) {
		this.xRadioButtonNOMBRE.setSelected(false);
		this.xTextFieldSearch.setText("");

		this.xTextFieldSearch.setPromptText("Buscar por codigo");
		radioButtonSelected = 2;
	}

	@FXML
	void xRadioButtonNOMBRE_Action(ActionEvent event) {
		this.xRadioButtonCODIGO.setSelected(false);
		this.xTextFieldSearch.setText("");

		this.xTextFieldSearch.setPromptText("Buscar por nombre");
		radioButtonSelected = 1;
	}

	public void reload(){

		xTableLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {

				rellenarTextField(newSelection);
				

			}
		});

		xTableLibros.getItems().clear();

		setTabla();

		getLibros();

		xTableLibros.getItems().addAll(this.stockTablaService.converToStockTabla(listaLibros));

	}

	private void rellenarTextField(StockTabla newSelection) {
		Libro libroSeleccionado = libroDAO.findById(newSelection.getId());

		this.xTextFieldUnidades.setText(libroSeleccionado.getUnidades() + "");
		
		this.xTextFieldNumeroMatriculas.setText(libroSeleccionado.getContenido().getMatriculas().size()+"");
		
		int ejemplaresEntregados = 0;
		int ejemplaresNoEntregados = 0;
		
		for (int i = 0; i < libroSeleccionado.getEjemplares().size(); i++) {
			if(libroSeleccionado.getEjemplares().get(i).getPrestado()==1) {
				ejemplaresEntregados++;
			}
			ejemplaresNoEntregados++;
		}
		ejemplaresNoEntregados = ejemplaresNoEntregados - ejemplaresEntregados;
		
		this.xTextFieldEntregados.setText(ejemplaresEntregados + "");
		this.xTextFieldNoEntregados.setText(ejemplaresNoEntregados + "");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTabla() {
		TableColumn codigoColumn = new TableColumn("Codigo");
		codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		codigoColumn.setMaxWidth(700);

		TableColumn precioColumn = new TableColumn("Precio");
		precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
		precioColumn.setMaxWidth(400);

		TableColumn cursoColumn = new TableColumn("Curso");
		cursoColumn.setCellValueFactory(new PropertyValueFactory<>("curso"));

		TableColumn nombreColumn = new TableColumn("Nombre");
		nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

		xTableLibros.getColumns().addAll(codigoColumn, nombreColumn, cursoColumn, precioColumn);
		xTableLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	private LibroDAO libroDAO = new LibroDAO();

	private void getLibros() {

		listaLibros = libroDAO.getAll().stream().filter(libro -> libro.getObsoleto() == 0)
				.collect((Collectors.toList()));

	}

	private void filtrar(String newValue) {
		if (radioButtonSelected == 1)
			filtrarPorNombre(newValue);
		else
			filtrarPorCodigo(newValue);
	}

	private void filtrarPorNombre(String newValue) {
		librosFiltrados = listaLibros.stream().filter(libro -> libro.getNombre().contains(newValue))
				.collect((Collectors.toList()));
		xTableLibros.getItems().clear();
		xTableLibros.getItems().addAll(this.stockTablaService.converToStockTabla(librosFiltrados));

	}

	private void filtrarPorCodigo(String newValue) {
		librosFiltrados = listaLibros.stream().filter(libro -> libro.getCodigo().contains(newValue))
				.collect((Collectors.toList()));
		xTableLibros.getItems().clear();
		xTableLibros.getItems().addAll(this.stockTablaService.converToStockTabla(librosFiltrados));

	}

	

}