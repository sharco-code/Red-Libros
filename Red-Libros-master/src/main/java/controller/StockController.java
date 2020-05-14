package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.json.simple.parser.ParseException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DefaultStringConverter;
import model.EjemplarTabla;
import model.StockTabla;
import pojo.Ejemplare;
import pojo.Libro;
import service.BarcodeService;
import service.EjemplarTablaService;
import service.StockTablaService;
import dao.LibroDAO;

public class StockController implements Initializable {

	@FXML
    private TextField xTextFieldEJE1;

    @FXML
    private TextField xTextFieldEJE2;

    @FXML
    private TextField xTextFieldEJE3;

    @FXML
    private TextField xTextFieldEJE4;

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

	private int radioButton_Selected = 1; // 1: NOMBRE, 2: CODIGO

	@FXML
	void xRadioButtonCODIGO_Action(ActionEvent event) {
		this.xRadioButtonNOMBRE.setSelected(false);
		this.xTextFieldSearch.setText("");

		this.xTextFieldSearch.setPromptText("Buscar por codigo");
		radioButton_Selected = 2;
	}

	@FXML
	void xRadioButtonNOMBRE_Action(ActionEvent event) {
		this.xRadioButtonCODIGO.setSelected(false);
		this.xTextFieldSearch.setText("");

		this.xTextFieldSearch.setPromptText("Buscar por nombre");
		radioButton_Selected = 1;
	}

	@FXML
	private VBox xVBoxMAIN;

	private List<Libro> listaLibros = new ArrayList<>();

	private List<Libro> librosFiltrados = new ArrayList<>();

	private StockTablaService StockTablaService = new StockTablaService();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				filtrar(newValue);
			}
		});

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void reload() throws SQLException, Exception {

		xTableLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {

				Libro libroSeleccionado = libroDAO.findById(newSelection.getId());

				
				this.xTextFieldEJE1.setText(libroSeleccionado.getUnidades() + "");
				
				this.xTextFieldEJE2.setText(libroSeleccionado.getContenido().getMatriculas().size()+"");
				
				
				
				int eje3 = 0, eje4 = 0;
				for (int i = 0; i < libroSeleccionado.getEjemplares().size(); i++) {
					if(libroSeleccionado.getEjemplares().get(i).getPrestado()==1) {
						eje3++;
					}
					eje4++;
				}
				eje4 = eje4 - eje3;
				this.xTextFieldEJE3.setText(eje3 + "");
				this.xTextFieldEJE4.setText(eje4 + "");
				

			}
		});

		xTableLibros.getItems().clear();

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

		getLibros();

		xTableLibros.getItems().addAll(this.StockTablaService.converToStockTabla(listaLibros));

	}

	private LibroDAO libroDAO = new LibroDAO();

	private void getLibros() {

		listaLibros = libroDAO.getAll().stream().filter(libro -> {
			return libro.getObsoleto() == 0;
		}).collect((Collectors.toList()));

	}

	private void filtrar(String newValue) {
		if (radioButton_Selected == 1)
			filtrarPorNombre(newValue);
		else
			filtrarPorCodigo(newValue);
	}

	private void filtrarPorNombre(String newValue) {
		// TODO Auto-generated method stub
		librosFiltrados = listaLibros.stream().filter(libro -> libro.getNombre().contains(newValue))
				.collect((Collectors.toList()));
		xTableLibros.getItems().clear();
		xTableLibros.getItems().addAll(this.StockTablaService.converToStockTabla(librosFiltrados));

	}

	private void filtrarPorCodigo(String newValue) {
		// TODO Auto-generated method stub
		librosFiltrados = listaLibros.stream().filter(libro -> libro.getCodigo().contains(newValue))
				.collect((Collectors.toList()));
		xTableLibros.getItems().clear();
		xTableLibros.getItems().addAll(this.StockTablaService.converToStockTabla(librosFiltrados));

	}

	@SuppressWarnings({ "rawtypes" })
	private TableColumn codigoColumn = new TableColumn("Codigo");
	@SuppressWarnings("unchecked")
	private TableColumn<EjemplarTabla, String> estadoColumn = new TableColumn("Estado");
	@SuppressWarnings("unchecked")
	private TableColumn<EjemplarTabla, String> prestadoColumn = new TableColumn("Prestado");

	private EjemplarTablaService ejemplarTablaService = new EjemplarTablaService();
	private ObservableList<String> listaEstados = FXCollections.observableArrayList();
	private ObservableList<String> listaPrestado = FXCollections.observableArrayList();

}