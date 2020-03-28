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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;


public class EntregasController implements Initializable {

	@FXML
    private AnchorPane anchorpane;
	
	@FXML
    private TableView<Libro> xTableMain;
	
	private Session session;
	
	private List<Libro> listaLibros = new ArrayList<>();
	
	private List<Libro> librosFiltrados = new ArrayList<>();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void reload() throws SQLException, Exception {
		
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		session = factory.getCurrentSession();
		
		xTableMain.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	Parent root = null;
	        	try {
	        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));
	        		
	        		LibroDetalleController libroDetalleController = new LibroDetalleController();
	    			loader.setController(libroDetalleController);
	    			//root = FXMLLoader.load(getClass().getResource("/view/libroDetalleComponent.fxml"));
	    			root = loader.load();
	    			libroDetalleController.setLibro(newSelection);
	    			anchorpane.getChildren().clear();
	    			anchorpane.getChildren().add(root);
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	            
	        }
        });
		
		xTableMain.getItems().clear();
		
		TableColumn precioColumn = new TableColumn("Precio");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        xTableMain.getColumns().addAll(nombreColumn,precioColumn);
        xTableMain.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        getLibros();
        
        
    	xTableMain.getItems().addAll(listaLibros);

	}

	@SuppressWarnings("unchecked")
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
		xTableMain.getItems().clear();
		xTableMain.getItems().addAll(librosFiltrados);
		
		
	}

	

}