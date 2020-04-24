package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import pojo.Contenido;
import utiles.hibernate.UtilesHibernate;

public class ContenidoDAO {

	private static final Logger logger = Logger.getLogger(ContenidoDAO.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {	
			SessionFactory s =  UtilesHibernate.getSessionFactory();
			s.getCurrentSession().beginTransaction();
			return s;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Contenido transientInstance) {
		logger.log(Level.INFO, "persisting Contenido instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Contenido instance) {
		logger.log(Level.INFO, "attaching dirty Contenido instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Contenido instance) {
		logger.log(Level.INFO, "attaching clean Contenido instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Contenido persistentInstance) {
		logger.log(Level.INFO, "deleting Contenido instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Contenido merge(Contenido detachedInstance) {
		logger.log(Level.INFO, "merging Contenido instance");
		try {
			Contenido result = (Contenido) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Contenido findById(java.lang.Integer id) {
		logger.log(Level.INFO, "getting Contenido instance with id: " + id);
		try {
			
			Contenido instance = (Contenido) sessionFactory.getCurrentSession().get("output.Contenido", id);
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

	public List findByExample(Contenido instance) {
		logger.log(Level.INFO, "finding Contenido instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("output.Contenido")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
