package org.nl.magiamerlini.controllers.implementations;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.mixer.api.Mixer;
import org.nl.magiamerlini.components.sampler.api.Sampler;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.components.video.api.Video;
import org.nl.magiamerlini.controllers.api.ItemsSelector;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.tools.Item;

public class MainControllerImpl implements MainController {
	private final static Mode DEFAULT_MODE = Mode.Sampler;

	private ItemsSelector itemsSelector;
	private ProjectsManager projectsManager;
	private Sampler sampler;
	private Mixer mixer;
	private Display display;
	private Padboard padboard;
	private Sequencer sequencer;
	private Video video;
	private FileExplorer fileExplorer;

	private boolean selecting;
	private boolean loading;
	private boolean loadingProject;

	private Mode selectedMode;
	// TODO: confusion between index and entities
	private int selectedBankIndex;
	private int selectedPatternIndex;
	private int selectedTrackIndex;
	private int selectedSequenceIndex;
	private int selectedPageIndex;

	public MainControllerImpl(ProjectsManager projectsManager, Display display, FileExplorer fileExplorer,
			Padboard padboard, Sampler sampler, Mixer mixer, Sequencer sequencer, Video video) {
		this.itemsSelector = new ItemsSelectorImpl(this);
		this.sequencer = sequencer;
		this.sampler = sampler;
		this.projectsManager = projectsManager;
		this.display = display;
		this.padboard = padboard;
		this.mixer = mixer;
		this.video = video;
		this.fileExplorer = fileExplorer;

		selecting = false;
		loading = false;
		loadingProject = false;

		selectedMode = DEFAULT_MODE;
		selectedBankIndex = 0;
		selectedTrackIndex = 0;
		selectedPatternIndex = 0;
		selectedSequenceIndex = 0;
		selectedPageIndex = 0;
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
	public Item getItemCorrespondingToPad(int padNum) {
		Item item = null;

		switch (selectedMode) {
		case Sampler:
			item = projectsManager.getSamplerTrack(selectedBankIndex, padNum);
			break;
		default:
			item = null;
			break;
		}

		return item;
	}

	@Override
	public boolean inMode(Mode mode) {
		return selectedMode.equals(mode);
	}

	@Override
	public Mode getMode() {
		return selectedMode;
	}

	@Override
	public boolean isSelecting() {
		return selecting;
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
	public void changeMode(int value) {
		Mode newMode = Mode.getCorrespondingMode(value);

		if (newMode != null) {
			selectedMode = Mode.getCorrespondingMode(value);
			display.displayMode();
		}
	}

	@Override
	public Sampler getSampler() {
		return sampler;
	}

	@Override
	public Display getDisplay() {
		return display;
	}

	@Override
	public Mixer getMixer() {
		return mixer;
	}

	@Override
	public Sequencer getSequencer() {
		return sequencer;
	}

	@Override
	public ItemsSelector getItemsSelector() {
		return itemsSelector;
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
	public void setSelecting(boolean selecting) {
		this.selecting = selecting;
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
		if (!selectedMode.equals(mode)) {
			this.itemsSelector.emptySelectedItems();
		}

		this.selectedMode = mode;
	}

	@Override
	public Mode getSelectedMode() {
		return selectedMode;
	}

	@Override
	public int getSelectedBankIndex() {
		return selectedBankIndex;
	}

	@Override
	public int getSelectedPatternIndex() {
		return selectedPatternIndex;
	}

	@Override
	public void changeSelectedPattern(int selectedPattern) {
		this.selectedPatternIndex = selectedPattern;
	}

	@Override
	public int getSelectedTrackIndex() {
		return selectedTrackIndex;
	}

	@Override
	public void changeSelectedTrack(int selectedTrack) {
		this.selectedTrackIndex = selectedTrack;
	}

	@Override
	public int getSelectedSequenceIndex() {
		return selectedSequenceIndex;
	}

	@Override
	public void changeSelectedSequence(int selectedSequence) {
		this.selectedSequenceIndex = selectedSequence;
		display.displaySelection();
	}

	@Override
	public int getSelectedPageIndex() {
		return selectedPageIndex;
	}

	@Override
	public void changeSelectedPage(int selectedPage) {
		this.selectedPageIndex = selectedPage;
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
			if (value != this.selectedBankIndex) {
				this.itemsSelector.emptySelectedItems();
			}

			this.selectedBankIndex = value;
		}
	}
}
