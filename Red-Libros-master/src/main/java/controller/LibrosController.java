package controller;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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


public class LibrosController{

	@FXML
    private AnchorPane anchorpane;
	
	@FXML
    private TableView<Libro> xTableLibros;
	
	private Session session;

	public void reload() throws SQLException, Exception {
		
		System.out.println("reload");
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		session = factory.getCurrentSession();
		
		xTableLibros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	            System.out.println(newSelection.getNombre());
	            
	        }
        });
		
		xTableLibros.getItems().clear();
		
		TableColumn precioColumn = new TableColumn("Precio");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        xTableLibros.getColumns().addAll(nombreColumn,precioColumn);
        xTableLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        session.beginTransaction();
		Query q = session.createQuery("SELECT e FROM Libro e");
        List<Libro> lst = q.getResultList();
        session.getTransaction().commit();
        
        
        	xTableLibros.getItems().addAll(lst);

	}

}