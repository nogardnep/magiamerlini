package org.nl.magiamerlini.controllers.implementations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.nl.magiamerlini.components.mixer.items.AudioMixerTrack;
import org.nl.magiamerlini.components.mixer.items.Effect;
import org.nl.magiamerlini.components.mixer.items.MixerTrack;
import org.nl.magiamerlini.components.mixer.items.VideoMixerTrack;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.components.sequencer.items.Pattern;
import org.nl.magiamerlini.components.sequencer.items.PatternEvent;
import org.nl.magiamerlini.components.sequencer.items.Sequence;
import org.nl.magiamerlini.components.sequencer.items.SequenceEvent;
import org.nl.magiamerlini.components.sequencer.items.Song;
import org.nl.magiamerlini.components.sequencer.items.SongPart;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.api.ProjectsController;
import org.nl.magiamerlini.controllers.tools.BaseController;
import org.nl.magiamerlini.data.api.DatabaseManager;
import org.nl.magiamerlini.data.items.Project;
import org.nl.magiamerlini.utils.EffectsUtils;
import org.nl.magiamerlini.utils.FileSystemUtils;
import org.nl.magiamerlini.utils.Logger;

public class BaseProjectsController extends BaseController implements ProjectsController {
	private Project currentProject;
	private DatabaseManager databaseManager;
	public final static String DB_FILE_NAME = "project";
	public final static String DB_FILE_EXTENSION = "mv.db";
	private Logger logger;

	public BaseProjectsController(MainController mainController, DatabaseManager databaseManager) {
		super(mainController);
		logger = new Logger(this.getClass().getSimpleName(), true);
		this.databaseManager = databaseManager;
	}

	@Override
	public void loadProject(String path) {
		databaseManager.connectTo(path);
		ArrayList<Object> projectsInDatabase = databaseManager.getEntities("FROM " + Project.class.getSimpleName());

		if (projectsInDatabase.size() > 0) {
			currentProject = (Project) projectsInDatabase.get(0);
			sendAllItemsState();
		} else {
			logger.log(Level.SEVERE, "No Project entity found in the loaded database (" + path + ")");
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
						+ ((path != null && path != "") ? ("(in " + path + ")") : "") + " (loaded instead)");
			} else {
				String fullPath = name;

				if (path != null && path != "") {
					fullPath = path + "/" + fullPath;
				}

				currentProject = new Project(name, fullPath);
				persist(currentProject);
				sendAllItemsState();
			}
		} else {
			logger.log(Level.SEVERE, "Invalid name: \"" + name + "\"");
		}
	}

	@Override
	public void update(Object entity) {
		logger.log(Level.INFO, "-- update entity --");
		logger.log(Level.INFO, "- " + entity);

		Session session = databaseManager.getSession();
		session.beginTransaction();

		session.update(entity);

		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void persist(Object entity) {
		logger.log(Level.INFO, "-- persist entity --");
		logger.log(Level.INFO, "- " + entity);

		Session session = databaseManager.getSession();
		session.beginTransaction();

		session.persist(entity);

		session.flush();
		session.getTransaction().commit();
		session.close();
	}

	private void sendAllItemsState() {
		for (AudioSamplerTrack audioSamplerTrack : currentProject.getAudioSamplerTracks()) {
			mainController.getAudioSampler().trackEdited(audioSamplerTrack);
		}

		for (VideoSamplerTrack videoSamplerTrack : currentProject.getVideoSamplerTracks()) {
			mainController.getVideoSampler().trackEdited(videoSamplerTrack);
		}

		for (AudioMixerTrack audioMixerTrack : currentProject.getAudioMixerTracks()) {
			mainController.getAudioMixer().trackEdited(audioMixerTrack);

			for (Effect effect : audioMixerTrack.getEffects()) {
				mainController.getAudioMixer().effectEdited(effect);
			}
		}

		for (VideoMixerTrack videoMixerTrack : currentProject.getVideoMixerTracks()) {
			mainController.getVideoMixer().trackEdited(videoMixerTrack);

			for (Effect effect : videoMixerTrack.getEffects()) {
				mainController.getVideoMixer().effectEdited(effect);
			}
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
		Path pathToProjectFolder = Paths.get(databaseManager.getRootDirectory() + "/" + project.getPath());
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

		return dbPath;
	}

	@Override
	public AudioSamplerTrack getAudioSamplerTrack(int bank, int number) {
		AudioSamplerTrack audioSamplerTrack = null;

		for (AudioSamplerTrack element : currentProject.getAudioSamplerTracks()) {
			if (element.getNumber() == number && element.getBank() == bank) {
				audioSamplerTrack = element;
				break;
			}
		}

		if (audioSamplerTrack == null) {
			audioSamplerTrack = new AudioSamplerTrack(bank, number);
			currentProject.addAudioSamplerTrack(audioSamplerTrack);
			persist(audioSamplerTrack);
			update(currentProject);
			mainController.getAudioSampler().trackEdited(audioSamplerTrack);
		}

		return audioSamplerTrack;
	}

	@Override
	public VideoSamplerTrack getVideoSamplerTrack(int bank, int number) {
		VideoSamplerTrack videoSamplerTrack = null;

		for (VideoSamplerTrack element : currentProject.getVideoSamplerTracks()) {
			if (element.getNumber() == number && element.getBank() == bank) {
				videoSamplerTrack = element;
				break;
			}
		}

		if (videoSamplerTrack == null) {
			videoSamplerTrack = new VideoSamplerTrack(bank, number);
			currentProject.addVideoSamplerTrack(videoSamplerTrack);
			persist(videoSamplerTrack);
			update(currentProject);
			mainController.getVideoSampler().trackEdited(videoSamplerTrack);
		}

		return videoSamplerTrack;
	}

	@Override
	public AudioMixerTrack getAudioMixerTrack(int number) {
		AudioMixerTrack audioMixerTrack = null;

		for (AudioMixerTrack element : currentProject.getAudioMixerTracks()) {
			if (element.getNumber() == number) {
				audioMixerTrack = element;
				break;
			}
		}

		if (audioMixerTrack == null) {
			audioMixerTrack = new AudioMixerTrack(number);
			currentProject.addAudioMixerTrack(audioMixerTrack);
			persist(audioMixerTrack);
			update(currentProject);
			mainController.getAudioMixer().trackEdited(audioMixerTrack);
		}

		return audioMixerTrack;
	}

	@Override
	public VideoMixerTrack getVideoMixerTrack(int number) {
		VideoMixerTrack videoMixerTrack = null;

		for (VideoMixerTrack element : currentProject.getVideoMixerTracks()) {
			if (element.getNumber() == number) {
				videoMixerTrack = element;
				break;
			}
		}

		if (videoMixerTrack == null) {
			videoMixerTrack = new VideoMixerTrack(number);
			currentProject.addVideoMixerTrack(videoMixerTrack);
			persist(videoMixerTrack);
			update(currentProject);
			mainController.getVideoMixer().trackEdited(videoMixerTrack);
		}

		return videoMixerTrack;
	}

	@Override
	public Effect getAudioEffect(int track, int number) {
		Class<?> type = EffectsUtils.getAudioEffectTypeCorrespondingTo(number);
		return getEffect(getAudioMixerTrack(track), number, type);
	}

	@Override
	public Effect getVideoEffect(int track, int number) {
		Class<?> type = EffectsUtils.getVideoEffectTypeCorrespondingTo(number);
		return getEffect(getVideoMixerTrack(track), number, type);
	}

	private Effect getEffect(MixerTrack mixerTrack, int number, Class<?> type) {
		Effect effect = null;

		if (type != null) {
			effect = mixerTrack.getEffectOfType(type);

			if (effect == null) {
				try {
					effect = (Effect) type.getConstructor().newInstance();
					mixerTrack.getEffects().add(effect);
					effect.setMixerTrack(mixerTrack);
					persist(effect);
					update(mixerTrack);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}

			mainController.getVideoMixer().effectEdited(effect);
		}

		return effect;
	}

	@Override
	public Pattern getPattern(int bank, int number) {
		Pattern pattern = null;

		for (Pattern element : currentProject.getPatterns()) {
			if (element.getBank() == bank && element.getNumber() == number) {
				pattern = element;
				break;
			}
		}

		if (pattern == null) {
			pattern = new Pattern(bank, number);
			currentProject.addPattern(pattern);
			persist(pattern);
			update(currentProject);
		}

		return pattern;
	}

	@Override
	public Sequence getSequence(int number) {
		Sequence sequence = null;

		for (Sequence element : currentProject.getSequences()) {
			if (element.getNumber() == number) {
				sequence = element;
				break;
			}
		}

		if (sequence == null) {
			sequence = new Sequence(number);
			currentProject.addSequence(sequence);
			persist(sequence);
			update(currentProject);
		}

		return sequence;
	}

	@Override
	public PatternEvent getPatternEvent(Pattern pattern, int trackNumber, int bar, int beat, int tick) {
		PatternEvent patternEvent = null;

		for (PatternEvent element : pattern.getPatternEvents()) {
			if (element.getTrackNumber() == trackNumber && element.getBar() == bar && element.getBeat() == beat
					&& element.getTick() == tick) {
				patternEvent = element;
				break;
			}
		}

		if (patternEvent == null) {
			patternEvent = new PatternEvent(trackNumber, bar, beat, tick, PatternEvent.INACTIVE_STATE);
			pattern.addPatternEvent(patternEvent);
			persist(patternEvent);
			update(pattern);
		}

		return patternEvent;
	}

	@Override
	public SequenceEvent getSequenceEvent(Sequence sequence, int patternNumber, int bar, int beat) {
		SequenceEvent sequenceEvent = null;

		for (SequenceEvent element : sequence.getSequenceEvents()) {
			if (element.getPatternNumber() == patternNumber && element.getBar() == bar && element.getBeat() == beat) {
				sequenceEvent = element;
				break;
			}
		}

		if (sequenceEvent == null) {
			sequenceEvent = new SequenceEvent(patternNumber, bar, beat, PatternEvent.INACTIVE_STATE);
			sequence.addSequenceEvent(sequenceEvent);
			persist(sequenceEvent);
			update(sequence);
		}

		return sequenceEvent;
	}

	@Override
	public SongPart getSongPart(Song song, int number) {
		SongPart songPart = null;

		for (SongPart element : song.getSongParts()) {
			if (element.getNumber() == number) {
				songPart = element;
				break;
			}
		}

		if (songPart == null) {
			songPart = new SongPart(number);
			song.addSongPart(songPart);
			persist(songPart);
			update(currentProject);
		}

		return songPart;
	}

	@Override
	public Song getSong(int number) {
		Song song = null;

		for (Song element : currentProject.getSongs()) {
			if (element.getNumber() == number) {
				song = element;
				break;
			}
		}

		if (song == null) {
			song = new Song(number);
			currentProject.addSong(song);
			persist(song);
			update(currentProject);
		}

		return song;
	}

}
