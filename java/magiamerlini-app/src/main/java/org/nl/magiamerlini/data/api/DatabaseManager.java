package org.nl.magiamerlini.data.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

/**
 * API for accessing to any database getting and storing all entities inside
 */
public interface DatabaseManager {
	public void connectTo(String dbPath);

	public void shutdown();

	public void saveEntity(Object entity);

	public void saveEntities(List<Object> entities);

	public String getRootDirectory();

	public ArrayList<Object> getEntities(String hql);

	public String getConnectionURL();
	
	public Session getSession();
}
