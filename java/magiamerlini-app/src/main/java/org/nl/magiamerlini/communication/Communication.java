package org.nl.magiamerlini.communication;

import java.util.HashMap;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.components.ui.Inputs;
import org.nl.magiamerlini.components.ui.tools.InputSection;
import org.nl.magiamerlini.controllers.ProjectsController;
import org.nl.magiamerlini.controllers.tools.FileType;
import org.nl.magiamerlini.utils.Logger;

public class Communication  {
	private final static String PRESSED = "pressed";
	private final static String LEAVED = "leaved";
	private final static String ACTION = "action";
	private final static String NAME = "name";
	private final static String PATH = "path";
	private final static String VALUE = "value";
	private final static String VELOCITY = "velocity";
	private final static String NUMBER = "number";
	private final static String SECTION = "section";
	private final static String TICKED = "ticked";
	private final static String LOAD = "load";
	private final static String NEW = "new";
	private final static String NETWORK = "network";
	private final static String CONNECTED = "connected";
	private final static String BUTTON = "button";
	private final static String PAD = "pad";
	private final static String SELECTOR = "selector";
	private final static String FILE_EXPLORER = "file_explorer";
	private final static String SAMPLER = "sampler";
	private final static String TYPE = "type";
	private final static String WHEEL = "wheel";
	private final static String SWITCH = "switch";
	private final static String SEQUENCER = "sequencer";
	
	private Server server;
	private Inputs inputs;

	private Logger logger;

	public Communication() {
		this.logger = new Logger(this.getClass().getSimpleName(), true);
		this.server = new Server();
	}

	public void connect(int port, boolean displayMessages) {
		server.connect(this, port, displayMessages);
	}

	public void testMessage(String message) {
		receiveMessage(message);
	}

	private HashMap<String, String> transformMessage(String[] messageParts) {
		HashMap<String, String> args = new HashMap<String, String>();

		for (String string : messageParts) {
			String[] split = string.split("=");

			if (split.length > 1) {
				// TODO: remove?
//				if (split[1].equals("null")) {
//					split[1] = null;
//				}

				args.put(split[0], split[1]);
			} else {
				args.put(split[0], null);
			}
		}

		return args;
	}

	public void receiveMessage(String message) {
		String[] messageParts = message.replace(";", "").split(" ");
		String component = messageParts[0];
		HashMap<String, String> args = transformMessage(messageParts);

		InputSection section;
		String name;

		switch (component) {
		case NETWORK:
			switch (args.get(ACTION)) {
			case CONNECTED:
				inputs.networkConnected();
				App.test();
				break;
			}
			break;
		case SEQUENCER:
			switch (args.get(ACTION)) {
			case TICKED:
				inputs.clockTicked();
				break;
			}
			break;
		case BUTTON:
			name = args.get(NAME);
			section = InputSection.getCorrespondingToString(args.get(SECTION));

			switch (args.get(ACTION)) {
			case PRESSED:
				inputs.buttonPressed(section, name);
				break;
			case LEAVED:
				inputs.buttonLeaved(section, name);
				break;
			}
			break;
		case WHEEL:
			section = InputSection.getCorrespondingToString(args.get(SECTION));
			inputs.wheelChanged(section, Integer.parseInt(args.get(VALUE)));
			break;
		case SWITCH:
			section = InputSection.getCorrespondingToString(args.get(SECTION));
			inputs.switchChanged(section, Integer.parseInt(args.get(VALUE)));
			break;
		case PAD:
			int number = Integer.parseInt(args.get(NUMBER));

			switch (args.get(ACTION)) {
			case PRESSED:
				inputs.padPressed(number, Float.parseFloat(args.get(VELOCITY)));
				break;
			case LEAVED:
				inputs.padLeaved(number);
				break;
			}
			break;
		case SELECTOR:
			section = InputSection.getCorrespondingToString(args.get(SECTION));
			inputs.selectorChanged(section, Integer.parseInt(args.get(VALUE)));
			break;
		case FILE_EXPLORER:
			FileType type = FileType.getCorrespondingToString(args.get(TYPE));

			switch (args.get(ACTION)) {
			case LOAD:
				String path = args.get(PATH);
				if (path != null && !path.equals("")) {
					// TODO: ugly
					String rootPath = App.ROOT_DIR_FOR_FILES + "/" + type.getPath() + "/";
					String fileExtension = "." + ProjectsController.DB_FILE_EXTENSION;
					System.out.println(fileExtension);
					System.out.println(path);
					path = path.replace(rootPath, "");
					path = path.replace(fileExtension, "");
				}
				inputs.fileLoaded(type, path); // TODO: temporary?
				break;
			case NEW:
				inputs.fileCreated(type, (args.get(NAME) != null ? args.get(NAME) : ""),
						(args.get(PATH) != null ? args.get(PATH) : "")); // TODO: temporary?
				break;
			}
			break;
		case SAMPLER:
			// TODO
//			int bank = Integer.parseInt(args.get("bank"));
//			int pad = Integer.parseInt(args.get("pad"));
//			SamplerTrack samplerTrack = projectsManager.getSamplerTrack(bank, pad);
//			System.out.println(samplerTrack);
//			
//			switch (action) {
//			case "load_sample":
//				samplerTrack.setSampleLocation(args.get("path"));
//				break;
//			}
//			projectsManager.updateEntity(samplerTrack);
			// TODO: save samplerTrack
			break;

		}
	}

	public void sendMessage(String message) {
		server.sendResponse(message);
	}

	public void setInputs(Inputs inputs) {
		this.inputs = inputs;
	}

}
