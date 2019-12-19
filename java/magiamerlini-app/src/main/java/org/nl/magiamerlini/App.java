package org.nl.magiamerlini;

import java.util.logging.Level;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.implementations.CommunicationImpl;
import org.nl.magiamerlini.components.mixer.api.AudioMixer;
import org.nl.magiamerlini.components.mixer.api.VideoMixer;
import org.nl.magiamerlini.components.mixer.implementations.CommunicatingAudioMixer;
import org.nl.magiamerlini.components.mixer.implementations.CommunicatingVideoMixer;
import org.nl.magiamerlini.components.sampler.api.AudioSampler;
import org.nl.magiamerlini.components.sampler.api.VideoSampler;
import org.nl.magiamerlini.components.sampler.implementations.CommunicatingAudioSampler;
import org.nl.magiamerlini.components.sampler.implementations.CommunicatingVideoSampler;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;
import org.nl.magiamerlini.components.sequencer.implementations.CommunicatingSequencer;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.components.ui.api.Inputs;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.components.ui.implementations.BaseInputs;
import org.nl.magiamerlini.components.ui.implementations.CommunicatingDisplay;
import org.nl.magiamerlini.components.ui.implementations.CommunicatingFileExplorer;
import org.nl.magiamerlini.components.ui.implementations.CommunicatingPadboard;
import org.nl.magiamerlini.components.ui.tools.ButtonName;
import org.nl.magiamerlini.components.ui.tools.InputSection;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.implementations.BaseMainController;
import org.nl.magiamerlini.controllers.tools.Mode;
import org.nl.magiamerlini.data.api.DatabaseManager;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.implementations.BaseDatabaseManager;
import org.nl.magiamerlini.data.implementations.BaseProjectsManager;
import org.nl.magiamerlini.utils.Logger;

public class App {
	public final static String CURRENT_PROJECT_NAME = "B"; // TODO: temporary
	public final static boolean TESTING = true;
	public static final String ROOT_DIR_FOR_FILES = "C:/Users/Nicolas/magiamerlini-data"; // TODO: change

	public static Inputs inputs;
	public static MainController mainController;
	public static Logger logger;
	public static Communication communication;

	public static void main(String[] args) {
		logger = new Logger(App.class.getSimpleName(), true);
		DatabaseManager databaseManager = null;

		try {
			communication = new CommunicationImpl();
			databaseManager = new BaseDatabaseManager();

			ProjectsManager projectsManager = new BaseProjectsManager(databaseManager);
			AudioSampler audioSampler = new CommunicatingAudioSampler(communication);
			AudioMixer audioMixer = new CommunicatingAudioMixer(communication);
			VideoSampler videoSampler = new CommunicatingVideoSampler(communication);
			VideoMixer videoMixer = new CommunicatingVideoMixer(communication);
			Sequencer sequencer = new CommunicatingSequencer(communication);
			Display display = new CommunicatingDisplay(communication);
			Padboard padboard = new CommunicatingPadboard(communication);
			FileExplorer fileExplorer = new CommunicatingFileExplorer(communication);
			inputs = new BaseInputs();

			mainController = new BaseMainController(projectsManager, display, fileExplorer, padboard, sequencer,
					audioSampler, audioMixer, videoSampler, videoMixer);

			padboard.setMainController(mainController);
			inputs.setMainController(mainController);
			display.setMainController(mainController);
			audioSampler.setMainController(mainController);
			videoSampler.setMainController(mainController);
			communication.setInputs(inputs);

			logger.log(Level.INFO, "=======");
//			System.out.println(Mode.Mixer.getIndex());
//			System.out.println(Mode.Project.getIndex());

			// projectManager.createProject("A1");
			// projectsManager.createProject(CURRENT_PROJECT_NAME);

			// ---- After everything is ready, can connect with puredata
			communication.connect(3000, false);

		} finally {
			databaseManager.shutdown();
		}
	}

	public static void test() {
		logger.log(Level.INFO, "==== TESTS ====");
//		communication.testMessage("file_explorer action=load type=project path=C:/Users/Nicolas/magiamerlini-data/projects/B/project.mv.db");
//		inputs.selectorChanged(InputSection.Mode, Mode.AudioSampler.ordinal());
//		inputs.buttonPressed(InputSection.Action, ButtonName.Load);
//		inputs.padPressed(0, 1);
		logger.log(Level.INFO, "=======");
	}
}
