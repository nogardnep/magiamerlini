package org.nl.magiamerlini.controllers.implementations;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.mixer.api.AudioMixer;
import org.nl.magiamerlini.components.mixer.api.VideoMixer;
import org.nl.magiamerlini.components.sampler.api.AudioSampler;
import org.nl.magiamerlini.components.sampler.api.VideoSampler;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;
import org.nl.magiamerlini.components.sequencer.items.Pattern;
import org.nl.magiamerlini.components.sequencer.items.Sequence;
import org.nl.magiamerlini.components.sequencer.items.Song;
import org.nl.magiamerlini.components.sequencer.tools.TimeSignature;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.api.PlayerController;
import org.nl.magiamerlini.controllers.api.PresetController;
import org.nl.magiamerlini.controllers.api.ProjectsController;
import org.nl.magiamerlini.controllers.api.SelectionController;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.api.DatabaseManager;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.utils.Logger;

public class BaseMainController implements MainController {
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

	public BaseMainController(DatabaseManager databaseManager, Display display, FileExplorer fileExplorer,
			Padboard padboard, Sequencer sequencer, AudioSampler audioSampler, AudioMixer audioMixer,
			VideoSampler videoSampler, VideoMixer videoMixer) {
		this.logger = new Logger(this.getClass().getSimpleName(), true);

		this.selectionController = new BaseSelectionController(this);
		this.presetController = new BasePresetController(this);
		this.playerController = new BasePlayerController(this);
		this.projectsController = new BaseProjectsController(this, databaseManager);
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

	@Override
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

	@Override
	public void updateDisplay() {
		display.displayLoading();
		display.displayProject();
		display.displayMode();
		display.displaySelection();
		display.displaySelectedItem();
		display.displaySelectedParameter();
		padboard.updateDisplay();
	}

	@Override
	public boolean inMode(Mode mode) {
		return currentMode.equals(mode);
	}

	@Override
	public void loadProject(String path) {
		loadingProject = true;
		display.displayLoading();
		projectsController.loadProject(path);
		loadingProject = false;
		updateDisplay();
	}

	@Override
	public void createProject(String name, String path) {
		loadingProject = true;
		display.displayLoading();
		projectsController.createProject(name, path);
		loadingProject = false;
		updateDisplay();
	}

	@Override
	public AudioSampler getAudioSampler() {
		return audioSampler;
	}

	@Override
	public Display getDisplay() {
		return display;
	}

	@Override
	public AudioMixer getAudioMixer() {
		return audioMixer;
	}

	@Override
	public Sequencer getSequencer() {
		return sequencer;
	}

	@Override
	public SelectionController getSelectionController() {
		return selectionController;
	}

	@Override
	public ProjectsController getProjectManager() {
		return projectsController;
	}

	@Override
	public Padboard getPadboard() {
		return padboard;
	}

	@Override
	public FileExplorer getFileExplorer() {
		return fileExplorer;
	}

	@Override
	public boolean isLoadingProject() {
		return loadingProject;
	}

	@Override
	public void changeMode(Mode mode) {
		this.currentModeIndex = mode.getIndex();
		this.currentMode = mode;

		if (!currentMode.equals(mode)) {
			this.selectionController.emptySelectedItems();
		}

		currentStateChanged();
		updateDisplay();
	}

	@Override
	public void changeMode(int index) {
		Mode mode = Mode.getCorrespondingMode(index);

		if (mode != null) {
			changeMode(mode);
		}
	}

	@Override
	public Mode getCurrentMode() {
		return currentMode;
	}

	@Override
	public int getCurrentModeIndex() {
		return currentModeIndex;
	}

	@Override
	public int getCurrentBankIndex() {
		return currentBankIndex;
	}

	@Override
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

	@Override
	public int getCurrentPatternIndex() {
		return currentPatternIndex;
	}

	@Override
	public void changePattern(int selectedPattern) {
		this.currentPatternIndex = selectedPattern;
		currentStateChanged();
		updateDisplay();
	}

	@Override
	public int getCurrentTrackIndex() {
		return currentTrackIndex;
	}

	@Override
	public void changeTrack(int selectedTrack) {
		this.currentTrackIndex = selectedTrack;
		currentStateChanged();
		updateDisplay();
	}

	@Override
	public int getCurrentSequenceIndex() {
		return currentSequenceIndex;
	}

	@Override
	public void changeSequence(int selectedSequence) {
		this.currentSequenceIndex = selectedSequence;
		display.displaySelection();
		currentStateChanged();
		updateDisplay();
	}

	@Override
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	@Override
	public void changePage(int selectedPage) {
		this.currentPageIndex = selectedPage;
		currentStateChanged();
		updateDisplay();
	}

	@Override
	public boolean isReady() {
		return projectsController.getCurrentProject() != null && !loadingProject;
	}

	@Override
	public VideoSampler getVideoSampler() {
		return videoSampler;
	}

	@Override
	public VideoMixer getVideoMixer() {
		return videoMixer;
	}

	@Override
	public PresetController getPresetController() {
		return presetController;
	}

	@Override
	public PlayerController getPlayerController() {
		return playerController;
	}

	private void currentStateChanged() {
		selectionController.setSelecting(false);
		selectionController.emptySelectedItems();
	}

}
