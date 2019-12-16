package org.nl.magiamerlini;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.implementations.CommunicationImpl;
import org.nl.magiamerlini.components.mixer.api.Mixer;
import org.nl.magiamerlini.components.mixer.implementations.MixerImpl;
import org.nl.magiamerlini.components.sampler.api.Sampler;
import org.nl.magiamerlini.components.sampler.implementations.SamplerImpl;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;
import org.nl.magiamerlini.components.sequencer.implementations.SequencerImpl;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.components.ui.api.FileExplorer;
import org.nl.magiamerlini.components.ui.api.Inputs;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.components.ui.implementations.DisplayImpl;
import org.nl.magiamerlini.components.ui.implementations.FileExplorerImpl;
import org.nl.magiamerlini.components.ui.implementations.InputsImpl;
import org.nl.magiamerlini.components.ui.implementations.PadboardImpl;
import org.nl.magiamerlini.components.ui.tools.Button;
import org.nl.magiamerlini.components.ui.tools.Selector;
import org.nl.magiamerlini.components.video.api.Video;
import org.nl.magiamerlini.components.video.implementations.VideoImpl;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.implementations.MainControllerImpl;
import org.nl.magiamerlini.data.api.DatabaseManager;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.implementations.DatabaseManagerImpl;
import org.nl.magiamerlini.data.implementations.ProjectsManagerImpl;

public class App {
	public final static String CURRENT_PROJECT_NAME = "B"; // TODO: temporary
	public final static boolean TESTING = true;
	public static final String ROOT_DIR_FOR_FILES = "C:/Users/Nicolas/magiamerlini-data"; // TODO: change
	public static final String SOUNDS_FOLDER = "sounds";
	public static final String PROJECTS_FOLDER = "projects";
	public static final String VIDEOS_FOLDER = "videos";
	public static final String BANKS_FOLDER = "banks";

	public static Inputs inputs;
	public static MainController mainController;

	public static void main(String[] args) {
		DatabaseManager databaseManager = null;

		try {
			Communication communication = new CommunicationImpl();
			databaseManager = new DatabaseManagerImpl();

			ProjectsManager projectsManager = new ProjectsManagerImpl(databaseManager);
			Sampler sampler = new SamplerImpl(communication);
			Sequencer sequencer = new SequencerImpl(communication);
			Mixer mixer = new MixerImpl(communication);
			Display display = new DisplayImpl(communication);
			Padboard padboard = new PadboardImpl(communication);
			Video video = new VideoImpl(communication);
			FileExplorer fileExplorer = new FileExplorerImpl(communication);
			inputs = new InputsImpl();

			mainController = new MainControllerImpl(projectsManager, display, fileExplorer, padboard, sampler, mixer,
					sequencer, video);

			padboard.setMainController(mainController);
			inputs.setMainController(mainController);
			display.setMainController(mainController);
			sampler.setMainController(mainController);
			communication.setInputs(inputs);

//			System.out.println("=======");
//			System.out.println(Mode.Mixer.getIndex());
//			System.out.println(Mode.Project.getIndex());

			// projectManager.createProject("A1");
			//projectsManager.createProject(CURRENT_PROJECT_NAME);
			communication.connect(3000, false); // After everything is ready, can connect with puredata
		} finally {
			databaseManager.shutdown();
		}
	}

	public static void test() {
		System.out.println("==== TESTS ====");
		mainController.updateDisplay();
		inputs.selectorChanged(Selector.Track, 0);
		inputs.bankButtonPressed(0);
		inputs.buttonPressed(Button.New);
		//inputs.padPressed(0, 1);
		System.out.println("=======");
	}
}
