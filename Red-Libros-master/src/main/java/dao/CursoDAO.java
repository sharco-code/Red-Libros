package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import pojo.Curso;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;

public class CursoDAO {
	 private static List<Curso> listaCursos = new ArrayList<>();
	 private static Session session;
	 
	 
	 private static void getCursosDDBB() throws FileNotFoundException, IOException, ParseException {
		if(session == null) {
			SessionFactory factory = UtilesHibernate.getSessionFactory();
			session = factory.getCurrentSession();
		}
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		Query q = session.createQuery("SELECT e FROM Curso e");
		listaCursos = q.getResultList();
	}
		
		
		
	public static List<Curso> getCursos() {
		try {
			getCursosDDBB();
			return listaCursos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}

}
