package controller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;
public class LibrosController implements Initializable{

	@FXML
    private TableView<Libro> xTableLibros;
	
	Session sesion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		sesion = factory.getCurrentSession();
		
		TableColumn precioColumn = new TableColumn("Precio");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        xTableLibros.getColumns().addAll(nombreColumn,precioColumn);
        xTableLibros.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        sesion.beginTransaction();
		Query q = sesion.createQuery("SELECT e FROM Libro e");
        List<Libro> lst = q.getResultList();
        sesion.getTransaction().commit();
        
        
        try {
        	xTableLibros.getItems().addAll(lst);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
