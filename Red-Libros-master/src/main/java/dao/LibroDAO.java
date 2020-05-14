package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import java.util.ArrayList;


import javax.persistence.Query;

import pojo.Ejemplare;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;

public class LibroDAO {

	private static final Logger logger = Logger.getLogger(LibroDAO.class.getName());

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
	public List<Libro> getAll() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "LibroDAO getAll()...");
		try {
			List<Libro> listaLibros = new ArrayList<>();

			
			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Libro e");
	        listaLibros = q.getResultList();
	        sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "LibroDAO getAll() successful");
			
			return listaLibros;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "LibroDAO getAll() failed", re);
			throw re;
		}
	}
	
	public void persist(Libro transientInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "persisting Libro instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed");
			throw re;
		}
	}

	public void attachDirty(Libro instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching dirty Libro instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Libro instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching clean Libro instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Libro persistentInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "deleting Libro instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Libro merge(Libro detachedInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "merging Libro instance");
		try {
			Libro result = (Libro) sessionFactory.getCurrentSession().merge(detachedInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Libro findById(java.lang.String id) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "getting Libro instance with id: " + id);
		try {
			Libro instance = (Libro) sessionFactory.getCurrentSession().get("pojo.Libro", id);
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

	public List findByExample(Libro instance) {
		logger.log(Level.INFO, "finding Libro instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("pojo.Libro")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	
}
