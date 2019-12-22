package org.nl.magiamerlini;

import java.io.IOException;
import java.util.logging.Level;

import org.nl.magiamerlini.communication.ClientHandler;
import org.nl.magiamerlini.communication.ClientHandlerOLD;
import org.nl.magiamerlini.communication.InputCommunication;
import org.nl.magiamerlini.components.mixer.AudioMixer;
import org.nl.magiamerlini.components.mixer.VideoMixer;
import org.nl.magiamerlini.components.sampler.AudioSampler;
import org.nl.magiamerlini.components.sampler.VideoSampler;
import org.nl.magiamerlini.components.sequencer.Sequencer;
import org.nl.magiamerlini.components.ui.Display;
import org.nl.magiamerlini.components.ui.FileExplorer;
import org.nl.magiamerlini.components.ui.Inputs;
import org.nl.magiamerlini.components.ui.Padboard;
import org.nl.magiamerlini.components.ui.tools.ButtonName;
import org.nl.magiamerlini.components.ui.tools.InputSection;
import org.nl.magiamerlini.controllers.MainController;
import org.nl.magiamerlini.data.DatabaseManager;
import org.nl.magiamerlini.utils.Logger;

public class App {
	public final static String CURRENT_PROJECT_NAME = "B"; // TODO: temporary
	public final static boolean TESTING = true;
	public static final String ROOT_DIR_FOR_FILES = "C:/Users/Nicolas/magiamerlini-data"; // TODO: change

	public static Inputs inputs;
	public static MainController mainController;
	public static Logger logger;
	public static InputCommunication communication;

	public static void main(String[] args) {
		logger = new Logger(App.class.getSimpleName(), true);
		DatabaseManager databaseManager = null;

		try {
			communication = new InputCommunication();
			databaseManager = new DatabaseManager();

			AudioSampler audioSampler = new AudioSampler(communication);
			AudioMixer audioMixer = new AudioMixer(communication);
			VideoSampler videoSampler = new VideoSampler(communication);
			VideoMixer videoMixer = new VideoMixer(communication);
			Sequencer sequencer = new Sequencer(communication);
			Display display = new Display(communication);
			Padboard padboard = new Padboard(communication);
			FileExplorer fileExplorer = new FileExplorer(communication);
			inputs = new Inputs();

			mainController = new MainController(databaseManager, display, fileExplorer, padboard, sequencer,
					audioSampler, audioMixer, videoSampler, videoMixer);

			padboard.setMainController(mainController);
			inputs.setMainController(mainController);
			display.setMainController(mainController);
			audioSampler.setMainController(mainController);
			videoSampler.setMainController(mainController);
			audioMixer.setMainController(mainController);
			videoMixer.setMainController(mainController);
			sequencer.setMainController(mainController);
			communication.setInputs(inputs);

			String host = "127.0.0.1";
			int clientPort = 3001;
			int serverPort = 3000;

			ClientHandler clientHandler = new ClientHandler(host, clientPort);
			Thread thread = new Thread(clientHandler);
			thread.start();
			clientHandler.sendMessage("test");

//			String pdExePath = "C:\\Program Files (x86)\\Pd 0.48-1\\bin\\pd.exe";
//			//String pdPatchFolderPath = "D:\\document\\art\\magiamerlini\\dev\\puredata\\magiamerlini-app";
//			String pdPatchFolderPath = "D:/document/art/magiamerlini/dev/puredata/magiamerlini-app";
//			String pdPatchName = "_main.pd";
//
//			try {
//				Runtime runTime = Runtime.getRuntime();
//				// Process process = runTime.exec(pdExePath);
//				Process process = new ProcessBuilder(pdExePath, "-path C:\\Program_Files_(x86)\\pd\\extra\\readanysf~\\")
//						.start();
//				InputStream inputStream = process.getInputStream();
//				InputStreamReader isr = new InputStreamReader(inputStream);
//				InputStream errorStream = process.getErrorStream();
//				InputStreamReader esr = new InputStreamReader(errorStream);
//
//				int n1;
//				char[] c1 = new char[1024];
//				StringBuffer standardOutput = new StringBuffer();
//				while ((n1 = isr.read(c1)) > 0) {
//					standardOutput.append(c1, 0, n1);
//				}
//				System.out.println("Standard Output: " + standardOutput.toString());
//
//				int n2;
//				char[] c2 = new char[1024];
//				StringBuffer standardError = new StringBuffer();
//				while ((n2 = esr.read(c2)) > 0) {
//					standardError.append(c2, 0, n2);
//				}
//				System.out.println("Standard Error: " + standardError.toString());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			logger.log(Level.INFO, "=======");
//			System.out.println(Mode.Mixer.getIndex());
//			System.out.println(Mode.Project.getIndex());

			// mainController.getProjectManager().loadProject("A1");
			// projectsManager.createProject(CURRENT_PROJECT_NAME);

			// ---- After everything is ready, can connect with puredata
			// communication.connect(serverPort, false);

		} finally {
			databaseManager.shutdown();
		}
	}

	public static void test() {
		logger.log(Level.INFO, "==== TESTS ====");
		communication.testMessage(
				"file_explorer action=load type=project path=C:/Users/Nicolas/magiamerlini-data/projects/W/project.mv.db");
		// inputs.selectorChanged(InputSection.Mode, Mode.AudioEffects.ordinal());
		inputs.buttonPressed(InputSection.Action, ButtonName.Edit);
		inputs.padPressed(2, 1);
		inputs.padLeaved(2);
		inputs.buttonLeaved(InputSection.Action, ButtonName.Edit);
//		inputs.padPressed(0, 1);
//		inputs.padLeaved(0);
////		for (int i = 0; i < 5; i++) {
////			inputs.buttonPressed(InputSection.Navigation, ButtonName.Down);
////			inputs.buttonLeaved(InputSection.Navigation, ButtonName.Down);
////		}
////		inputs.padPressed(0, 1);
////		inputs.padLeaved(0);
//		inputs.buttonLeaved(InputSection.Action, ButtonName.Special);
		// inputs.buttonPressed(InputSection.Player, ButtonName.Play);
		// inputs.buttonLeaved(InputSection.Player, ButtonName.Play);
		logger.log(Level.INFO, "=======");
	}
}
