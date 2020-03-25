package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import javafx.fxml.Initializable;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;

public class LibroDAO {
	
	private static List<Libro> listaLibros = new ArrayList<>();
	private static Session session;
	
	
	


	
	private static void getLibrosDDBB() throws FileNotFoundException, IOException, ParseException {
		if(session == null) {
			SessionFactory factory = UtilesHibernate.getSessionFactory();
			session = factory.getCurrentSession();
		}
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		Query q = session.createQuery("SELECT e FROM Libro e");
        listaLibros = q.getResultList();
	}
	
	
	
	public static List<Libro> getLibros() {
		try {
			getLibrosDDBB();
			return listaLibros;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static Libro getLibro(String id) throws FileNotFoundException, IOException, ParseException {
		
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		session = factory.getCurrentSession();
		session.beginTransaction();
		Libro libro = session.get(Libro.class, id);
		return libro;
	}


}
