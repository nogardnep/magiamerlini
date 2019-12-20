package org.nl.magiamerlini.controllers;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.mixer.AudioMixer;
import org.nl.magiamerlini.components.mixer.VideoMixer;
import org.nl.magiamerlini.components.sampler.AudioSampler;
import org.nl.magiamerlini.components.sampler.VideoSampler;
import org.nl.magiamerlini.components.sequencer.Sequencer;
import org.nl.magiamerlini.components.sequencer.items.Pattern;
import org.nl.magiamerlini.components.sequencer.items.Sequence;
import org.nl.magiamerlini.components.sequencer.items.Song;
import org.nl.magiamerlini.components.sequencer.tools.TimeSignature;
import org.nl.magiamerlini.components.ui.Display;
import org.nl.magiamerlini.components.ui.FileExplorer;
import org.nl.magiamerlini.components.ui.Padboard;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.DatabaseManager;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.utils.Logger;

public class MainController {
	private SelectionController selectionController;
	private PresetController presetController;
	private ProjectsController projectsController;
	private PlayerController playerController;
	private AudioSampler audioSampler;
	private AudioMixer audioMixer;
	private VideoSampler videoSampler;
	private VideoMixer videoMixer;
	private Display display;
	private Padboard padboard;
	private Sequencer sequencer;
	private FileExplorer fileExplorer;
	private boolean loadingProject;

	@SuppressWarnings("unused")
	private Logger logger;

	private Mode currentMode;
	private int currentModeIndex;
	private int currentBankIndex;
	private int currentPatternIndex;
	private int currentTrackIndex;
	private int currentSequenceIndex;
	private int currentPageIndex;

	public MainController(DatabaseManager databaseManager, Display display, FileExplorer fileExplorer,
			Padboard padboard, Sequencer sequencer, AudioSampler audioSampler, AudioMixer audioMixer,
			VideoSampler videoSampler, VideoMixer videoMixer) {
		this.logger = new Logger(this.getClass().getSimpleName(), true);

		this.selectionController = new SelectionController(this);
		this.presetController = new PresetController(this);
		this.playerController = new PlayerController(this);
		this.projectsController = new ProjectsController(this, databaseManager);
		this.display = display;
		this.padboard = padboard;
		this.fileExplorer = fileExplorer;
		this.sequencer = sequencer;
		this.audioSampler = audioSampler;
		this.audioMixer = audioMixer;
		this.videoSampler = videoSampler;
		this.videoMixer = videoMixer;

		loadingProject = false;
		currentMode = Mode.Project;
		currentBankIndex = 0;
		currentPatternIndex = 0;
		currentTrackIndex = 0;
		currentSequenceIndex = 0;
		currentPageIndex = 0;
	}

	public Item getItemCorrespondingTo(int number) {
		Item item = null;
		Pattern pattern;
		Sequence sequence;
		Song song;
		int bar = 0, beat = 0, tick = 0;

		switch (currentMode) {
		case Project:
			break;
		case Song:
			song = projectsController.getSong(0);
			item = projectsController.getSongPart(song, number);
			break;
		case SequenceEdit:
			sequence = projectsController.getSequence(currentSequenceIndex);

			// TODO: ugly
			boolean visibleBeat = true;
			TimeSignature sequenceTimeSignature = sequence.getTimeSignature();

			bar = 0;
			beat = number % 4;
			tick = 0;

			if (number <= 3) {
				bar = 0;
			} else if (number >= 4 && number <= 7) {
				bar = 1;
			} else if (number >= 8 && number <= 11) {
				bar = 2;
			} else if (number >= 12 && number <= 15) {
				bar = 3;
			}

			if (beat >= sequenceTimeSignature.getBeat()) {
				visibleBeat = false;
			}

			if (bar >= sequenceTimeSignature.getBar()) {
				visibleBeat = false;
			}

			if (visibleBeat) {
				item = projectsController.getSequenceEvent(sequence, currentPatternIndex, bar, beat);
			} else {
				item = null;
			}
			break;
		case PatternEdit:
			// TODO: ugly
			bar = currentPageIndex;
			if (number < 4) {
				beat = 0;
			} else if (number < 8) {
				beat = 1;
			} else if (number < 12) {
				beat = 2;
			} else {
				beat = 3;
			}
			switch (number % 4) {
			case 0:
				tick = 0;
				break;
			case 1:
				tick = 6;
				break;
			case 2:
				tick = 12;
				break;
			case 3:
				tick = 18;
				break;
			}
			pattern = projectsController.getPattern(currentBankIndex, currentPatternIndex);
			item = projectsController.getPatternEvent(pattern, currentTrackIndex, bar, beat, tick);
			break;
		case PatternLaunch:
			item = projectsController.getPattern(currentBankIndex, number);
			break;
		case AudioMixer:
			item = projectsController.getAudioMixerTrack(number);
			break;
		case AudioSampler:
			item = projectsController.getAudioSamplerTrack(currentBankIndex, number);
			break;
		case AudioEffects:
			item = projectsController.getAudioEffect(currentTrackIndex, number);
			break;
		case VideoMixer:
			item = projectsController.getVideoMixerTrack(number);
			break;
		case VideoSampler:
			item = projectsController.getVideoSamplerTrack(currentTrackIndex, number);
			break;
		case VideoEffects:
			item = projectsController.getVideoEffect(currentTrackIndex, number);
			break;
		default:
			break;
		}

		return item;
	}

	public void updateDisplay() {
		display.displayLoading();
		display.displayProject();
		display.displayMode();
		display.displaySelection();
		display.displaySelectedItem();
		display.displaySelectedParameter();
		padboard.updateDisplay();
	}

	public boolean inMode(Mode mode) {
		return currentMode.equals(mode);
	}

	public void loadProject(String path) {
		loadingProject = true;
		display.displayLoading();
		projectsController.loadProject(path);
		loadingProject = false;
		updateDisplay();
	}

	public void createProject(String name, String path) {
		loadingProject = true;
		display.displayLoading();
		projectsController.createProject(name, path);
		loadingProject = false;
		updateDisplay();
	}

	public AudioSampler getAudioSampler() {
		return audioSampler;
	}

	public Display getDisplay() {
		return display;
	}

	public AudioMixer getAudioMixer() {
		return audioMixer;
	}

	public Sequencer getSequencer() {
		return sequencer;
	}

	public SelectionController getSelectionController() {
		return selectionController;
	}

	public ProjectsController getProjectManager() {
		return projectsController;
	}

	public Padboard getPadboard() {
		return padboard;
	}

	public FileExplorer getFileExplorer() {
		return fileExplorer;
	}

	public boolean isLoadingProject() {
		return loadingProject;
	}

	public void changeMode(Mode mode) {
		this.currentModeIndex = mode.getIndex();
		this.currentMode = mode;

		if (!currentMode.equals(mode)) {
			this.selectionController.emptySelectedItems();
		}

		currentStateChanged();
		updateDisplay();
	}

	public void changeMode(int index) {
		Mode mode = Mode.getCorrespondingMode(index);

		if (mode != null) {
			changeMode(mode);
		}
	}

	public Mode getCurrentMode() {
		return currentMode;
	}

	public int getCurrentModeIndex() {
		return currentModeIndex;
	}

	public int getCurrentBankIndex() {
		return currentBankIndex;
	}

	public void changeBank(int value) {
		if (value < Configuration.Banks.getValue()) {
			if (value != this.currentBankIndex) {
				this.selectionController.emptySelectedItems();
			}

			this.currentBankIndex = value;
		}

		currentStateChanged();
		updateDisplay();
	}

	public int getCurrentPatternIndex() {
		return currentPatternIndex;
	}

	public void changePattern(int selectedPattern) {
		this.currentPatternIndex = selectedPattern;
		currentStateChanged();
		updateDisplay();
	}

	public int getCurrentTrackIndex() {
		return currentTrackIndex;
	}

	public void changeTrack(int selectedTrack) {
		this.currentTrackIndex = selectedTrack;
		currentStateChanged();
		updateDisplay();
	}

	public int getCurrentSequenceIndex() {
		return currentSequenceIndex;
	}

	public void changeSequence(int selectedSequence) {
		this.currentSequenceIndex = selectedSequence;
		display.displaySelection();
		currentStateChanged();
		updateDisplay();
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void changePage(int selectedPage) {
		this.currentPageIndex = selectedPage;
		currentStateChanged();
		updateDisplay();
	}

	public boolean isReady() {
		return projectsController.getCurrentProject() != null && !loadingProject;
	}

	public VideoSampler getVideoSampler() {
		return videoSampler;
	}

	public VideoMixer getVideoMixer() {
		return videoMixer;
	}

	public PresetController getPresetController() {
		return presetController;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

	private void currentStateChanged() {
		selectionController.setSelecting(false);
		selectionController.emptySelectedItems();
	}

}
