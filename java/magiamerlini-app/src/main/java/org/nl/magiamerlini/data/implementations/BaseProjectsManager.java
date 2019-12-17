package org.nl.magiamerlini.data.implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.nl.magiamerlini.data.api.DatabaseManager;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.items.Project;
import org.nl.magiamerlini.data.items.SamplerTrack;
import org.nl.magiamerlini.utils.Utils;

public class BaseProjectsManager implements ProjectsManager {
	private Project currentProject;
	private DatabaseManager databaseManager;
	private final static String DB_FILE_NAME = "data";
	private final static String DB_FILE_EXTENSION = "mv.db";

	public BaseProjectsManager(DatabaseManager databaseManager) {
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
			System.err.println("No Project entity found in the current database");
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
				System.err.println("Project already exists: \"" + name + "\""
						+ ((path != null && path != "") ? ("(in " + path + ")") : ""));
			} else {
				String location = name;

				if (path != null && path != "") {
					location = path + "/" + location;
				}

				currentProject = new Project(name, location);

				System.out.println("-- create project --");
				System.out.printf("- %s%n", currentProject);

				Session session = databaseManager.getSession();
				session.beginTransaction();

				session.persist(currentProject);
				currentProject.getSamplerTracks().forEach((SamplerTrack samplerTrack) -> {
					session.persist(samplerTrack);
				});

				session.flush();
				session.getTransaction().commit();
				session.close();
			}
		} else {
			System.err.println("Invalid name: \"" + name + "\"");
		}
	}

	@Override
	public ArrayList<Project> getProjects() {
		// TODO: explore data folder to find all db files

		try (Stream<Path> walk = Files.walk(Paths.get(databaseManager.getRootDirectory()))) {
			List<String> result = walk.map(x -> x.toString())
					.filter(f -> f.contains(DB_FILE_NAME + "." + DB_FILE_EXTENSION))
//					.filter(f -> f.endsWith(fileEnd))
					.collect(Collectors.toList());

			result.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void deleteCurrentProject() {
		System.out.println("-- delete project --");
		Project project = currentProject;
		Path pathToProjectFolder = Paths.get(databaseManager.getRootDirectory() + "/" + project.getLocation());
		databaseManager.shutdown();
		Utils.deleteDirectory(pathToProjectFolder.toFile());
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

		System.out.println("makeDbPath" + dbPath);
		return dbPath;
	}

	@Override
	public SamplerTrack getSamplerTrack(int bank, int number) {
		SamplerTrack samplerTrack = null;

		for (Iterator<SamplerTrack> iterator = currentProject.getSamplerTracks().iterator(); iterator.hasNext();) {
			SamplerTrack element = (SamplerTrack) iterator.next();

			if (element.getBank() == bank && element.getNumber() == number) {
				samplerTrack = element;
				break;
			}
		}

		if (samplerTrack == null) {
			samplerTrack = new SamplerTrack(bank, number);
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
		System.out.println("-- update entity --");
		System.out.printf("- %s%n", entity);

		Session session = databaseManager.getSession();
		session.beginTransaction();

		session.update(entity);

		session.flush();
		session.getTransaction().commit();
		session.close();
	}

}
