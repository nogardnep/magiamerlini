package org.nl.magiamerlini.controllers.api;

import org.nl.magiamerlini.components.audio.api.AudioMixer;
import org.nl.magiamerlini.components.audio.api.AudioSampler;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.components.video.api.VideoMixer;
import org.nl.magiamerlini.components.video.api.VideoSampler;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.tools.Item;

public interface MainController {
	public Mode getCurrentMode();

	public Item getItemCorrespondingTo(int number);

	public void changeMode(Mode mode);

	public void changeMode(int index);

	public int getCurrentBankIndex();

	public void changeBank(int value);

	public void setLoading(boolean loading);

	public void updateDisplay();

	public void loadProject(String path);

	public boolean inMode(Mode mode);

	public ProjectsManager getProjectManager();

	public int getCurrentPatternIndex();

	public void changePattern(int index);

	public int getCurrentTrackIndex();

	public void changeTrack(int index);

	public int getCurrentSequenceIndex();

	public void changeSequence(int index);

	public int getCurrentPageIndex();

	public void changePage(int index);

	public Sequencer getSequencer();

	public Display getDisplay();

	public Padboard getPadboard();

	public SelectionController getSelectionController();

	public AudioSampler getAudioSampler();

	public AudioMixer getAudioMixer();

	public VideoSampler getVideoSampler();

	public VideoMixer getVideoMixer();

	boolean isLoading();

	public FileExplorer getFileExplorer();

	public void createProject(String name, String path);

	public boolean isReady();

	boolean isLoadingProject();

	int getCurrentModeIndex();

	public PresetController getPresetController();
	
	public PlayerController getPlayerController();

}
