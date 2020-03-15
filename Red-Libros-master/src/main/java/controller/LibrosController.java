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

import app.Init;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;


public class LibrosController implements Initializable {

	@FXML
    private AnchorPane anchorpane;
	
	@FXML
    private TableView<Libro> xTableLibros;
	
	private Session session;
	
	private List<Libro> listaLibros = new ArrayList<>();
	
	private List<Libro> librosFiltrados = new ArrayList<>();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void reload() throws SQLException, Exception {
		
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		session = factory.getCurrentSession();
		
		xTableLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	Parent root = null;
	        	try {
	        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));
	        		
	        		LibroDetalleController libroController = new LibroDetalleController();
	    			loader.setController(libroController);
	    			//root = FXMLLoader.load(getClass().getResource("/view/libroDetalleComponent.fxml"));
	    			root = loader.load();
	    			libroController.setLibro(newSelection);
	    			anchorpane.getChildren().clear();
	    			anchorpane.getChildren().add(root);
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
		session.beginTransaction();
		Query q = session.createQuery("SELECT e FROM Libro e");
        listaLibros = q.getResultList();
        session.getTransaction().commit();
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