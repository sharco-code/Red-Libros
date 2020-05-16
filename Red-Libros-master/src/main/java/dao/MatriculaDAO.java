package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;

import pojo.Matricula;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.LockMode;
import org.hibernate.criterion.Example;

public class MatriculaDAO {

	private static final Logger logger = Logger.getLogger(MatriculaDAO.class.getName());

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
	public List<Matricula> getAll() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "MatriculaDAO getAll()...");
		try {
			List<Matricula> lst = new ArrayList<>();

			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Matricula e");
	        lst = q.getResultList();
	        sessionFactory.getCurrentSession().getTransaction().commit();
	        
			logger.log(Level.INFO, "MatriculaDAO getAll() successful");
			
			return lst;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "MatriculaDAO getAll() failed", re);
			throw re;
		}
	}
	public void persist(Matricula transientInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "persisting Matricula instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Matricula instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching dirty Matricula instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Matricula instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching clean Matricula instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Matricula persistentInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "deleting Matricula instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Matricula merge(Matricula detachedInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "merging Matricula instance");
		try {
			Matricula result = (Matricula) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Matricula findById(java.lang.String id) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "getting Matricula instance with id: " + id);
		try {
			Matricula instance = (Matricula) sessionFactory.getCurrentSession().get("pojo.Matricula", id);
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

	public List findByExample(Matricula instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "finding Matricula instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("pojo.Matricula")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean exist(String alumno,String curso,String contenido) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "MatriculaDAO exist()...");
		try {
			List<Matricula> lst = new ArrayList<>();

			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Matricula e WHERE alumno = '"+alumno+"' AND curso = '"+curso+"' AND contenido = '"+contenido+"'");
	        lst = q.getResultList();
	        sessionFactory.getCurrentSession().getTransaction().commit();
	        if(!lst.isEmpty())return true;
	        
			logger.log(Level.INFO, "MatriculaDAO exist() successful");
			
			return false;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "MatriculaDAO exist() failed", re);
			throw re;
		}
	}
}

