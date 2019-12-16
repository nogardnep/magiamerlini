package org.nl.magiamerlini.controllers.api;

import org.nl.magiamerlini.components.mixer.api.Mixer;
import org.nl.magiamerlini.components.sampler.api.Sampler;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.entities.SamplerTrack;
import org.nl.magiamerlini.data.tools.Item;

public interface MainController {
	public Mode getSelectedMode();
	
	public Item getItemCorrespondingToPad(int padNum);

	public void changeMode(Mode mode);

	public int getSelectedBankIndex();

	public void changeBank(int value);

	public boolean isSelecting();

	public void setSelecting(boolean selecting);
	
	public void setLoading(boolean loading);

	public void updateDisplay();

	public void loadProject(String path);

	public void changeMode(int value);

	public boolean inMode(Mode mode);

	public Sampler getSampler();

	public ItemsSelector getItemsSelector();

	public ProjectsManager getProjectManager();
	
	public Padboard getPadboard();

	public int getSelectedPatternIndex();

	public void changeSelectedPattern(int selectedPattern);

	public int getSelectedTrackIndex();

	public void changeSelectedTrack(int selectedTrack);

	public int getSelectedSequenceIndex();

	public void changeSelectedSequence(int selectedSequence);

	public int getSelectedPageIndex();

	public void changeSelectedPage(int selectedPage);

	public boolean isReady();

	boolean isLoadingProject();

	Mixer getMixer();

	Sequencer getSequencer();

	Display getDisplay();

	boolean isLoading();

	public FileExplorer getFileExplorer();

	public Mode getMode();

	public void createProject(String name, String path);
}
