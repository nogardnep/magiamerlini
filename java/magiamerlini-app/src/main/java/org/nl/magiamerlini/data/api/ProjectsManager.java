package org.nl.magiamerlini.data.api;

import java.util.ArrayList;

import org.nl.magiamerlini.data.entities.Project;
import org.nl.magiamerlini.data.entities.SamplerTrack;

/**
 * Every project stored in separated H2 databases, accessible through specific
 * path Every database contains only one Project entity, with other entities
 * related to that project
 */
public interface ProjectsManager {
	public ArrayList<Project> getProjects();

	public Project getCurrentProject();

	public void createProject(String name);

	public void createProject(String name, String path);

	public void deleteCurrentProject();

	public void loadProject(String projectName);

	public void loadProject(String projectName, String rootFolder);

	public SamplerTrack getSamplerTrack(int bank, int number);
	
	public void updateEntity(Object entity);
}
