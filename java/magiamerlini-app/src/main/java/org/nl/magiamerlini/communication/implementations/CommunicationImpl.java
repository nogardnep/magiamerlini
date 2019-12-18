package org.nl.magiamerlini.communication.implementations;

import java.util.HashMap;
import java.util.logging.Level;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.communication.Server;
import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.ui.api.Inputs;
import org.nl.magiamerlini.components.ui.tools.InputSection;
import org.nl.magiamerlini.controllers.tools.FileType;
import org.nl.magiamerlini.utils.Logger;

public class CommunicationImpl implements Communication {
	private Server server;
	private Inputs inputs;
	private final static String PRESSED = "pressed";
	private final static String LEAVED = "leaved";
	private final static String ACTION = "action";
	private final static String NAME = "name";
	private final static String PATH = "path";
	private final static String VALUE = "value";
	private final static String VELOCITY = "velocity";
	private final static String NUMBER = "number";
	private final static String SECTION = "section";
	private final static String TICK = "tick";
	private final static String LOAD = "load";
	private final static String NEW = "new";
	private final static String NETWORK = "network";
	private final static String CONNECTED = "connected";
	private final static String BUTTON = "button";
	private final static String PAD = "pad";
	private final static String CLOCK = "clock";
	private final static String SELECTOR = "selector";
	private final static String FILE_EXPLORER = "file_explorer";
	private final static String SAMPLER = "sampler";
	private final static String TYPE = "type";
	private final static String WHEEL = "wheel";
	private final static String SWITCH = "switch";

	private Logger logger;

	public CommunicationImpl() {
		this.logger = new Logger(this.getClass().getSimpleName(), true);
		this.server = new Server();
	}

	@Override
	public void connect(int port) {
		server.connect(this, port);
	}

	@Override
	public void testMessage(String message) {
		receiveMessage(message.replace(";", "").split(" "));
	}

	private HashMap<String, String> transformMessage(String[] messageParts) {
		HashMap<String, String> args = new HashMap<String, String>();

		for (String string : messageParts) {
			String[] split = string.split("=");
			args.put(split[0], (split.length > 1 ? split[1] : null));
		}

		return args;
	}

	@Override
	public void receiveMessage(String[] messageParts) {
		String component = messageParts[0];
		HashMap<String, String> args = transformMessage(messageParts);

		InputSection section;
		String name;

		switch (component) {
		case NETWORK:
			switch (args.get(ACTION)) {
			case CONNECTED:
				App.test();
				break;
			}
			break;
		case BUTTON:
			name = args.get(NAME);
			section = InputSection.getCorrespondingToString(args.get(SECTION));

			switch (args.get(ACTION)) {
			case PRESSED:
				inputs.buttonPressed(name, section);
				break;
			case LEAVED:
				inputs.buttonLeaved(name, section);
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
			section = InputSection.getCorrespondingToString(args.get(NAME));
			inputs.selectorChanged(section, Integer.parseInt(args.get(VALUE)));
			break;
		case CLOCK:
			switch (args.get(ACTION)) {
			case TICK:
				break;
			}
			break;
		case FILE_EXPLORER:
			FileType type = FileType.getCorrespondingToString(args.get(TYPE));

			switch (args.get(ACTION)) {
			case LOAD:
				String path = args.get(PATH).replace(App.ROOT_DIR_FOR_FILES + "/", ""); // TODO: ugly
				inputs.fileLoaded(type, path); // TODO: temporary?
				break;
			case NEW:
				logger.log(Level.INFO, "new");
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

	@Override
	public void sendMessage(String message) {
		server.sendResponse(message);
	}

	@Override
	public void setInputs(Inputs inputs) {
		this.inputs = inputs;
	}

}
