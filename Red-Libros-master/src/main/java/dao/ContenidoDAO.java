package dao;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import pojo.Contenido;
import utiles.hibernate.UtilesHibernate;

public class ContenidoDAO {
	
private static Session session;
	
	
	public static Contenido getContenidoById(Integer id) throws FileNotFoundException, IOException, ParseException {
		if(id==null) return null;
		if(session == null) {
			SessionFactory factory = UtilesHibernate.getSessionFactory();
			session = factory.getCurrentSession();
		}
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		
		Contenido contenido = (Contenido) session.get(Contenido.class, id);
		if(contenido == null) return null;
		else return contenido;

	}
}
