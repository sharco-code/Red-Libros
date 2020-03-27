package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;
import dao.LibroDAO;


public class LibrosController implements Initializable {
	
	@FXML
    private TableView<Libro> xTableLibros;
	
	 @FXML
	 private TextField xTextFieldSearch;
	
	 @FXML
	 private VBox xVBoxMAIN;
	 
	private List<Libro> listaLibros = new ArrayList<>();
	
	private List<Libro> librosFiltrados = new ArrayList<>();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
    	    if(newValue != null) {
    	    	filtrar(newValue);
    	    }
    	});
		
	}

	public void reload() throws SQLException, Exception {
		
		
		xTableLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	Parent root = null;
	        	
	        	try {
	        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));
	        		
	        		LibroDetalleController libroDetalleController = new LibroDetalleController();
	    			loader.setController(libroDetalleController);
	    			//root = FXMLLoader.load(getClass().getResource("/view/libroDetalleComponent.fxml"));
	    			root = loader.load();
	    			libroDetalleController.setLibro(newSelection);

	    			this.xVBoxMAIN.getChildren().clear();
	    			this.xVBoxMAIN.getChildren().add(root);

	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		
	            
	        }
        });
		
		xTableLibros.getItems().clear();
		
		TableColumn precioColumn = new TableColumn("Precio");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        xTableLibros.getColumns().addAll(nombreColumn,precioColumn);
        xTableLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        getLibros();
        
        
    	xTableLibros.getItems().addAll(listaLibros);

	}

	private void getLibros() {
        listaLibros = LibroDAO.getLibros();
	}

	public void filtrar(String newValue) {
		// TODO Auto-generated method stub
		librosFiltrados = listaLibros.stream()
				.filter(libro -> libro.getNombre().contains(newValue))
				.collect((Collectors.toList()));
		xTableLibros.getItems().clear();
		xTableLibros.getItems().addAll(librosFiltrados);
		
		
	}

	

}