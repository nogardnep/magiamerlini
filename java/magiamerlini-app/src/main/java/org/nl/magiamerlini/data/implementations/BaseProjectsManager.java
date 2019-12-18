package org.nl.magiamerlini.data.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.nl.magiamerlini.components.audio.items.AudioSamplerTrack;
import org.nl.magiamerlini.data.api.DatabaseManager;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.items.Project;
import org.nl.magiamerlini.utils.FileSystemUtils;
import org.nl.magiamerlini.utils.Logger;

public class BaseProjectsManager implements ProjectsManager {
	private Project currentProject;
	private DatabaseManager databaseManager;
	private final static String DB_FILE_NAME = "data";
	private final static String DB_FILE_EXTENSION = "mv.db";
	private Logger logger;

	public BaseProjectsManager(DatabaseManager databaseManager) {
		logger = new Logger(this.getClass().getSimpleName(), true);
		this.databaseManager = databaseManager;
	}

	@Override
	public void loadProject(String projectName) {
		loadProject(projectName, "");
	}

	@Override
	public void loadProject(String name, String path) {
		databaseManager.connectTo(makeDbPath(name, path));
		ArrayList<Object> projectsInDatabase = databaseManager.getEntities("FROM " + Project.class.getSimpleName());

		if (projectsInDatabase.size() > 0) {
			currentProject = (Project) projectsInDatabase.get(0);
		} else {
			logger.log(Level.SEVERE, "No Project entity found in the current database");
		}
	}

	@Override
	public void createProject(String name) {
		createProject(name, "");
	}

	@Override
	public void createProject(String name, String path) {
		if (name != "") {
			databaseManager.connectTo(makeDbPath(name, path));
			ArrayList<Object> projectsInDatabase = databaseManager.getEntities("FROM " + Project.class.getSimpleName());

			if (projectsInDatabase.size() > 0) {
				logger.log(Level.WARNING, "Project already exists: \"" + name + "\""
						+ ((path != null && path != "") ? ("(in " + path + ")") : ""));
			} else {
				String location = name;

				if (path != null && path != "") {
					location = path + "/" + location;
				}

				currentProject = new Project(name, location);

				logger.log(Level.INFO, "-- create project --");
				logger.log(Level.INFO, "- " + currentProject);

				Session session = databaseManager.getSession();
				session.beginTransaction();

				session.persist(currentProject);
				currentProject.getSamplerTracks().forEach((AudioSamplerTrack samplerTrack) -> {
					session.persist(samplerTrack);
				});

				session.flush();
				session.getTransaction().commit();
				session.close();
			}
		} else {
			logger.log(Level.SEVERE, "Invalid name: \"" + name + "\"");
		}
	}

	@Override
	public ArrayList<Project> getProjects() {
		// TODO: explore data folder to find all db files

		logger.log(Level.INFO, "-- get projects --");

		try (Stream<Path> walk = Files.walk(Paths.get(databaseManager.getRootDirectory()))) {
			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.contains(DB_FILE_NAME + "." + DB_FILE_EXTENSION))
//					.filter(f -> f.endsWith(fileEnd))
					.collect(Collectors.toList());

			result.forEach((String string) -> {
				logger.log(Level.INFO, "- " + string);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void deleteCurrentProject() {
		logger.log(Level.INFO, "-- delete project --");
		Project project = currentProject;
		Path pathToProjectFolder = Paths.get(databaseManager.getRootDirectory() + "/" + project.getLocation());
		databaseManager.shutdown();
		FileSystemUtils.deleteDirectory(pathToProjectFolder.toFile());
	}

	@Override
	public Project getCurrentProject() {
		return currentProject;
	}

	public String makeDbPath(String name, String root) {
		String dbPath = name + "/" + DB_FILE_NAME;

		if (root != null && root != "") {
			dbPath = root + "/" + dbPath;
		}

		logger.log(Level.INFO, "makeDbPath" + dbPath);
		return dbPath;
	}

	@Override
	public AudioSamplerTrack getAudioSamplerTrack(int bank, int number) {
		AudioSamplerTrack samplerTrack = null;

		for (Iterator<AudioSamplerTrack> iterator = currentProject.getSamplerTracks().iterator(); iterator.hasNext();) {
			AudioSamplerTrack element = (AudioSamplerTrack) iterator.next();

			if (element.getBank() == bank && element.getNumber() == number) {
				samplerTrack = element;
				break;
			}
		}

		if (samplerTrack == null) {
			samplerTrack = new AudioSamplerTrack(bank, number);
			currentProject.addSamplerTrack(samplerTrack);

			Session session = databaseManager.getSession();
			session.beginTransaction();

			session.persist(samplerTrack);
			session.update(currentProject);

			session.flush();
			session.getTransaction().commit();
			session.close();
		}

		return samplerTrack;
	}

	@Override
	public void updateEntity(Object entity) {
		logger.log(Level.INFO, "-- update entity --");
		logger.log(Level.INFO, "- " + entity);

		Session session = databaseManager.getSession();
		session.beginTransaction();

		session.update(entity);

		session.flush();
		session.getTransaction().commit();
		session.close();
	}

}
