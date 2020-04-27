package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Query;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import pojo.Ejemplare;
import utiles.hibernate.UtilesHibernate;


public class EjemplarDAO {

	private static final Logger logger = Logger.getLogger(EjemplarDAO.class.getName());

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
	public List<Ejemplare> getAll() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "EjemplarDAO getAll()...");
		try {
			List<Ejemplare> listaEjemplare = new ArrayList<>();

			
			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Ejemplare e");
	        listaEjemplare = q.getResultList();
	        sessionFactory.getCurrentSession().getTransaction().commit();
	        
			logger.log(Level.INFO, "EjemplarDAO getAll() successful");
			
			return listaEjemplare;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "EjemplarDAO getAll() failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Ejemplare> getAllByIdLibro(String IdLibro) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "EjemplarDAO getAllByIdLibro()... - IdLibro:"+IdLibro);
		try {
			List<Ejemplare> listaEjemplare = new ArrayList<>();

			
			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Ejemplare e WHERE e.libro.id LIKE '"+IdLibro+"'");
	        listaEjemplare = q.getResultList();
	        sessionFactory.getCurrentSession().getTransaction().commit();
	        
			logger.log(Level.INFO, "EjemplarDAO getAllByIdLibro() successful");
			
			return listaEjemplare;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "EjemplarDAO getAllByIdLibro() failed", re);
			throw re;
		}
	}
	
	public void persist(Ejemplare transientInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "persisting Ejemplares instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Ejemplare instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching dirty Ejemplares instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ejemplare instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching clean Ejemplares instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Ejemplare persistentInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "deleting Ejemplares instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Ejemplare merge(Ejemplare detachedInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "merging Ejemplares instance");
		try {
			Ejemplare result = (Ejemplare) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Ejemplare findById(java.lang.String id) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "getting Ejemplares instance with id: " + id);
		try {
			Ejemplare instance = (Ejemplare) sessionFactory.getCurrentSession().get("pojo.Ejemplare", id);
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

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public List findByExample(Ejemplare instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "finding Ejemplares instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("pojo.Ejemplare")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
