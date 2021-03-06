package org.nl.magiamerlini.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.nl.magiamerlini.utils.Logger;

public class DatabaseManager {
	private static SessionFactory sessionFactory;
	private static StandardServiceRegistry registry;
	private static Configuration configuration;
	private static Connection connection;
	private Logger logger;

	public DatabaseManager() {
		logger = new Logger(this.getClass().getSimpleName(), true);
		initConfiguration();
	}

	public void connectTo(String dbPath) {
		String options = "";

		String url = "jdbc:h2:file:" + configuration.getProperty("root_directory") + "/" + dbPath + ";" + options;

		shutdown();
		initConfiguration();
		configuration.setProperty("hibernate.connection.url", url);

		try {
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shutdown() {
		logger.log(Level.INFO, "SHUTDOWN");

		if (sessionFactory != null) {
			sessionFactory.close();
		}

		// TODO: delete?
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// TODO: delete?
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public String getRootDirectory() {
		return configuration.getProperty("root_directory");
	}

	public String getConnectionURL() {
		return configuration.getProperty("hibernate.connection.url");
	}

	// TODO: delete?
	public void saveEntity(Object entity) {
		List<Object> list = new ArrayList<Object>();
		list.add(entity);
		saveEntities(list);
	}

	// TODO: delete?
	public void saveEntities(List<Object> entities) {
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			entities.forEach((entity) -> {
				logger.log(Level.INFO, "---- persist ----");
				logger.log(Level.INFO, " - " + entity);
				session.persist(entity);
			});

			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}

			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}

	// TODO: delete?
	public ArrayList<Object> getEntities(String hql) {
		List<Object> objects = new ArrayList<Object>();
		Session session = sessionFactory.openSession();
		try {
			Query query = session.createQuery(hql);
			objects = session.createQuery(hql).list();
		} finally {
			session.close();
		}

		return (ArrayList<Object>) objects;
	}

	private void initConfiguration() {
		configuration = new Configuration();
		configuration.configure();
	}

	public Session getSession() {
		return sessionFactory.openSession();
	}
}
