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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pojo.Libro;
import dao.LibroDAO;

public class LibrosController implements Initializable {

	@FXML
	private TableView<Libro> xTableLibros;

	@FXML
	private RadioButton xRadioButtonNOMBRE;

	@FXML
	private RadioButton xRadioButtonCODIGO;

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
				Parent root = null;

				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));

					LibroDetalleController libroDetalleController = new LibroDetalleController();
					loader.setController(libroDetalleController);
					// root =
					// FXMLLoader.load(getClass().getResource("/view/libroDetalleComponent.fxml"));
					root = loader.load();
					root.getStylesheets().add(getClass().getResource("/style/table_style_small.css").toExternalForm());
					libroDetalleController.setLibro(newSelection);

					this.xVBoxMAIN.getChildren().clear();
					this.xVBoxMAIN.getChildren().add(root);

					libroDetalleController.disableComboBoxes();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		xTableLibros.getItems().clear();
		
		TableColumn codigoColumn = new TableColumn("Codigo");
		codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		codigoColumn.setMaxWidth(450);
		
		TableColumn precioColumn = new TableColumn("Precio");
		precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));
		precioColumn.setMaxWidth(400);

		TableColumn nombreColumn = new TableColumn("Nombre");
		nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));

		xTableLibros.getColumns().addAll(codigoColumn, nombreColumn, precioColumn);
		xTableLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		getLibros();

		xTableLibros.getItems().addAll(listaLibros);

	}

	private LibroDAO libroDAO = new LibroDAO();
	
	private void getLibros() {
			
		
		listaLibros = libroDAO.getAll();
			
	}

	private void filtrar(String newValue) {
		if(radioButton_Selected==1) filtrarPorNombre(newValue);
		else filtrarPorCodigo(newValue);
	}
	
	private void filtrarPorNombre(String newValue) {
		// TODO Auto-generated method stub
		librosFiltrados = listaLibros.stream().filter(libro -> libro.getNombre().contains(newValue))
				.collect((Collectors.toList()));
		xTableLibros.getItems().clear();
		xTableLibros.getItems().addAll(librosFiltrados);

	}

	private void filtrarPorCodigo(String newValue) {
		// TODO Auto-generated method stub
		librosFiltrados = listaLibros.stream().filter(libro -> libro.getCodigo().contains(newValue))
				.collect((Collectors.toList()));
		xTableLibros.getItems().clear();
		xTableLibros.getItems().addAll(librosFiltrados);

	}
	
	@FXML
    private HBox xButtonADD;

    @FXML
    void AddCLICKED(MouseEvent event) {
    	Parent root = null;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));

			LibroDetalleController libroDetalleController = new LibroDetalleController();
			loader.setController(libroDetalleController);
			// root =
			// FXMLLoader.load(getClass().getResource("/view/libroDetalleComponent.fxml"));
			root = loader.load();
			libroDetalleController.setNuevoLibro();
			libroDetalleController.setLibrosController(this);
			
			this.xVBoxMAIN.getChildren().clear();
			this.xVBoxMAIN.getChildren().add(root);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void AddENTERED(MouseEvent event) {

    }

    @FXML
    void AddEXITED(MouseEvent event) {

    }
}