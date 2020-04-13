package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import pojo.Alumno;
import pojo.Curso;
import utiles.hibernate.UtilesHibernate;

public class AlumnoDAO {
	
	
	 private static List<Alumno> listaAlumnos = new ArrayList<>();
	 private static Session session;
	 
	 
	 private static void getAlumnosDDBB() throws FileNotFoundException, IOException, ParseException {
		if(session == null) {
			SessionFactory factory = UtilesHibernate.getSessionFactory();
			session = factory.getCurrentSession();
		}
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		
		Query q = session.createQuery("SELECT e FROM Alumno e");
		listaAlumnos = q.getResultList();
		session.getTransaction().commit();
	}
		
		
		
	public static List<Alumno> getAlumnos() {
		try {
			getAlumnosDDBB();
			return listaAlumnos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}

}
