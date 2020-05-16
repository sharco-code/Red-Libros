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
import pojo.Grupo;
import utiles.hibernate.UtilesHibernate;

public class GrupoDAO {

	private static final Logger logger = Logger.getLogger(GrupoDAO.class.getName());

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
	public List<Grupo> getAll() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "GrupoDAO getAll()...");
		try {
			List<Grupo> listaLibros = new ArrayList<>();

			
			Query q = sessionFactory.getCurrentSession().createQuery("SELECT e FROM Grupo e");
	        listaLibros = q.getResultList();
	        sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "GrupoDAO getAll() successful");
			
			return listaLibros;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "GrupoDAO getAll() failed", re);
			throw re;
		}
	}
	
	public void persist(Grupo transientInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "persisting Grupo instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed");
			throw re;
		}
	}

	public void attachDirty(Grupo instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching dirty Grupo instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Grupo instance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "attaching clean Grupo instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Grupo persistentInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "deleting Grupo instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Grupo merge(Grupo detachedInstance) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "merging Grupo instance");
		try {
			Grupo result = (Grupo) sessionFactory.getCurrentSession().merge(detachedInstance);
			sessionFactory.getCurrentSession().getTransaction().commit();
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Grupo findById(java.lang.String id) {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
		logger.log(Level.INFO, "getting Grupo instance with id: " + id);
		try {
			Grupo instance = (Grupo) sessionFactory.getCurrentSession().get("pojo.Grupo", id);
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

	public List findByExample(Grupo instance) {
		logger.log(Level.INFO, "finding Grupo instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("pojo.Grupo")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	
}
