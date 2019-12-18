package org.nl.magiamerlini.controllers.implementations;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.audio.api.AudioMixer;
import org.nl.magiamerlini.components.audio.api.AudioSampler;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.components.video.api.VideoMixer;
import org.nl.magiamerlini.components.video.api.VideoSampler;
import org.nl.magiamerlini.controllers.api.SelectionController;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.api.PlayerController;
import org.nl.magiamerlini.controllers.api.PresetController;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.tools.Item;

public class BaseMainController implements MainController {
	private final static Mode DEFAULT_MODE = Mode.AudioSampler;

	private SelectionController selectionController;
	private PresetController presetController;
	private ProjectsManager projectsManager;
	private PlayerController playerController;
	private AudioSampler audioSampler;
	private AudioMixer audioMixer;
	private VideoSampler videoSampler;
	private VideoMixer videoMixer;
	private Display display;
	private Padboard padboard;
	private Sequencer sequencer;
	private FileExplorer fileExplorer;
	private boolean loading;
	private boolean loadingProject;

	private Mode currentMode;
	private int currentModeIndex;
	private int currentBankIndex;
	private int currentPatternIndex;
	private int currentTrackIndex;
	private int currentSequenceIndex;
	private int currentPageIndex;

	public BaseMainController(ProjectsManager projectsManager, Display display, FileExplorer fileExplorer,
			Padboard padboard, Sequencer sequencer, AudioSampler audioSampler, AudioMixer audioMixer,
			VideoSampler videoSampler, VideoMixer videoMixer) {
		this.selectionController = new BaseSelectionController(this);
		this.presetController = new BasePresetController(this);
		this.playerController = new BasePlayerController(this);
		this.projectsManager = projectsManager;
		this.display = display;
		this.padboard = padboard;
		this.fileExplorer = fileExplorer;
		this.sequencer = sequencer;
		this.audioSampler = audioSampler;
		this.audioMixer = audioMixer;
		this.videoSampler = videoSampler;
		this.videoMixer = videoMixer;
		loading = false;
		loadingProject = false;

		currentMode = DEFAULT_MODE;
	}

	@Override
	public void updateDisplay() {
		display.displayProject();
		display.displayMode();
		display.displaySelection();
		display.displaySelectedItem();
		display.displaySelectedParameter();
	}

	@Override
	public Item getItemCorrespondingTo(int number) {
		Item item = null;

		switch (currentMode) {
		case AudioSampler:
			item = projectsManager.getAudioSamplerTrack(currentBankIndex, number);
			break;
		default:
			item = null;
			break;
		}

		return item;
	}

	@Override
	public boolean inMode(Mode mode) {
		return currentMode.equals(mode);
	}

	@Override
	public void loadProject(String path) {
		loadingProject = true;
		display.displayLoading();
		projectsManager.loadProject(path);
		loadingProject = false;
		display.displayLoading();
		updateDisplay();
	}

	@Override
	public void createProject(String name, String path) {
		projectsManager.createProject(name, path);
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
	public ProjectsManager getProjectManager() {
		return projectsManager;
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
	public void setLoading(boolean loading) {
		this.loading = loading;
	}

	@Override
	public boolean isLoading() {
		return loading;
	}

	@Override
	public void changeMode(Mode mode) {
		this.currentModeIndex = mode.getIndex();
		this.currentMode = mode;
		display.displayMode();
		
		if (!currentMode.equals(mode)) {
			this.selectionController.emptySelectedItems();
		}
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
	public int getCurrentPatternIndex() {
		return currentPatternIndex;
	}

	@Override
	public void changePattern(int selectedPattern) {
		this.currentPatternIndex = selectedPattern;
	}

	@Override
	public int getCurrentTrackIndex() {
		return currentTrackIndex;
	}

	@Override
	public void changeTrack(int selectedTrack) {
		this.currentTrackIndex = selectedTrack;
	}

	@Override
	public int getCurrentSequenceIndex() {
		return currentSequenceIndex;
	}

	@Override
	public void changeSequence(int selectedSequence) {
		this.currentSequenceIndex = selectedSequence;
		display.displaySelection();
	}

	@Override
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	@Override
	public void changePage(int selectedPage) {
		this.currentPageIndex = selectedPage;
	}

	@Override
	public boolean isReady() {
		return projectsManager.getCurrentProject() != null && !loadingProject;
	}

	@Override
	public boolean isLoadingProject() {
		return loadingProject;
	}

	@Override
	public void changeBank(int value) {
		if (value < Configuration.Banks.getValue()) {
			if (value != this.currentBankIndex) {
				this.selectionController.emptySelectedItems();
			}

			this.currentBankIndex = value;
		}
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

}
