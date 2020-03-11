package utiles.hibernate;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import pojo.Alumno;
import pojo.Contenido;
import pojo.Curso;
import pojo.Ejemplare;
import pojo.Grupo;
import pojo.Historial;
import pojo.Libro;
import pojo.Matricula;

import java.util.Properties;

import org.hibernate.SessionFactory;

public class UtilesHibernate {
	private static SessionFactory sessionFactory = null;
	static {
		try {
			/*
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.configure().buildSessionFactory();
			*/
			
			try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/instituto?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1234");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                //settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(Alumno.class);
                configuration.addAnnotatedClass(Contenido.class);
                configuration.addAnnotatedClass(Curso.class);
                configuration.addAnnotatedClass(Ejemplare.class);
                configuration.addAnnotatedClass(Grupo.class);
                configuration.addAnnotatedClass(Historial.class);
                configuration.addAnnotatedClass(Libro.class);
                configuration.addAnnotatedClass(Matricula.class);
                
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
			
		} catch (Throwable ex) {
			// TODO: handle exception
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
