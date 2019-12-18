package org.nl.magiamerlini;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.implementations.CommunicationImpl;
import org.nl.magiamerlini.components.BaseComponent;
import org.nl.magiamerlini.components.audio.api.AudioMixer;
import org.nl.magiamerlini.components.audio.api.AudioSampler;
import org.nl.magiamerlini.components.audio.implementations.CommunicatingAudioMixer;
import org.nl.magiamerlini.components.audio.implementations.CommunicatingAudioSampler;
import org.nl.magiamerlini.components.audio.items.effects.ReverbAudioEffect;
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
import org.nl.magiamerlini.components.video.api.VideoMixer;
import org.nl.magiamerlini.components.video.api.VideoSampler;
import org.nl.magiamerlini.components.video.implementations.CommunicatingVideoSampler;
import org.nl.magiamerlini.components.video.implementations.VideoMixerImpl;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.implementations.BaseMainController;
import org.nl.magiamerlini.data.api.DatabaseManager;
import org.nl.magiamerlini.data.api.ProjectsManager;
import org.nl.magiamerlini.data.implementations.BaseDatabaseManager;
import org.nl.magiamerlini.data.implementations.BaseProjectsManager;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;
import org.nl.magiamerlini.utils.Logger;

public class App {
	public final static String CURRENT_PROJECT_NAME = "B"; // TODO: temporary
	public final static boolean TESTING = true;
	public static final String ROOT_DIR_FOR_FILES = "C:/Users/Nicolas/magiamerlini-data"; // TODO: change

	public static Inputs inputs;
	public static MainController mainController;
public static Logger logger;
	
	public static void main(String[] args) {
		logger = new Logger(App.class.getSimpleName(), true);
		DatabaseManager databaseManager = null;

		try {
			Communication communication = new CommunicationImpl();
			databaseManager = new BaseDatabaseManager();

			ProjectsManager projectsManager = new BaseProjectsManager(databaseManager);
			AudioSampler audioSampler = new CommunicatingAudioSampler(communication);
			AudioMixer audioMixer = new CommunicatingAudioMixer(communication);
			VideoSampler videoSampler = new CommunicatingVideoSampler(communication);
			VideoMixer videoMixer = new VideoMixerImpl(communication);
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
			communication.setInputs(inputs);

//			System.out.println("=======");
//			System.out.println(Mode.Mixer.getIndex());
//			System.out.println(Mode.Project.getIndex());

			// projectManager.createProject("A1");
			// projectsManager.createProject(CURRENT_PROJECT_NAME);

			// communication.connect(3000); // After everything is ready, can connect
			// with puredata

			ReverbAudioEffect reverb = new ReverbAudioEffect();
			System.out.println(reverb.getLevel());
			
			System.out.println(reverb.getParameters());
			reverb.applyParameter(new ParameterSnapshot("level", 8, 0, 0, 1));
			
//			try {
//				reverb.getClass().getMethod("setLevel", float.class).invoke(reverb, 5);
//			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
//					| NoSuchMethodException | SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
//			for (Field field : ReverbAudioEffect.class.getDeclaredFields()) {
//				//System.out.println(field.getAnnotations()..getClass().getSimpleName());
//				if (field.isAnnotationPresent(Parameter.class)) {
//					System.out.println(field.getName());
//					try {
//						reverb.getClass().getMethod("setLevel", Float.class).invoke(reverb, 5);
//					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
//							| NoSuchMethodException | SecurityException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
////					obj.getClass().getMethod("setParentName", String.class).invoke(obj, "Parent name");
//				}
//			}
			
			System.out.println(reverb.getLevel());

		} finally {
			databaseManager.shutdown();
		}
	}

	public static void test() {
		System.out.println("==== TESTS ====");
		mainController.updateDisplay();
		inputs.selectorChanged(InputSection.Track, 0);
		inputs.buttonPressed("0", InputSection.Bank);
		inputs.buttonPressed(ButtonName.New, InputSection.Action);
		// inputs.padPressed(0, 1);
		System.out.println("=======");
	}
}
