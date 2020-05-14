package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import pojo.Historial;
import pojo.Curso;
import utiles.hibernate.UtilesHibernate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Historial.
 * @see pojo.Historial
 * @author Hibernate Tools
 */
public class HistorialDAO {

	private static final Logger logger = Logger.getLogger(HistorialDAO.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return UtilesHibernate.getSessionFactory();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Historial> getAll() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "HistorialDAO getAll()...");
		try {
			List<Historial> lst = new ArrayList<>();

			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Historial e");
	        lst = q.getResultList();
	        sessionFactory.getCurrentSession().getTransaction().commit();
	        
			logger.log(Level.INFO, "HistorialDAO getAll() successful");
			
			return lst;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "HistorialDAO getAll() failed", re);
			throw re;
		}
	}
	
	public void persist(Historial transientInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "persisting Historial instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Historial instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching dirty Historial instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Historial instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching clean Historial instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Historial persistentInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "deleting Historial instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Historial merge(Historial detachedInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "merging Historial instance");
		try {
			Historial result = (Historial) sessionFactory.getCurrentSession().merge(detachedInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Historial findById(java.lang.String id) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "getting Historial instance with id: " + id);
		try {
			Historial instance = (Historial) sessionFactory.getCurrentSession().get("pojo.Historial", id);
			if (instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
	}

	public List findByExample(Historial instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "finding Historial instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("pojo.Historial")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Historial> getAllByAlumno(String id) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "HistorialDAO getAllByAlumno()...");
		try {
			List<Historial> lst = new ArrayList<>();

			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Historial e WHERE e.alumno.id = "+id);
	        lst = q.getResultList();
	        for(Historial historial:lst) {
	        	Hibernate.initialize(historial.getEjemplare().getLibro());
	        }
	        sessionFactory.getCurrentSession().getTransaction().commit();
	        
			logger.log(Level.INFO, "HistorialDAO getAllByAlumno() successful");
			
			return lst;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "HistorialDAO getAllByAlumno() failed", re);
			throw re;
		}
	}
	
	public Integer getLastId() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "Historial getLastId");
		try {
			Integer lastId = (Integer)sessionFactory.getCurrentSession().createQuery("SELECT MAX(e.id) FROM Historial e").uniqueResult();

			
			
			return lastId;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "getLastId failed", re);
			throw re;
		}
	}
	
}
