package org.nl.magiamerlini.communication.implementations;

import java.util.HashMap;
import java.util.logging.Level;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.communication.Server;
import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.ui.api.Inputs;
import org.nl.magiamerlini.components.ui.tools.Button;
import org.nl.magiamerlini.components.ui.tools.Selector;
import org.nl.magiamerlini.utils.Logger;

public class CommunicationImpl implements Communication {
	private Server server;
	private Inputs inputs;
	private final static String PRESSED = "pressed";
	private final static String LEAVED = "leaved";
	private final static String ACTION = "action";
	private final static String BANK = "bank";
	private final static String NAME = "name";
	private final static String PATH = "path";
	private final static String VALUE = "value";
	private final static String VELOCITY = "velocity";
	private final static String NUMBER = "number";
	private final static String TICK = "tick";
	private final static String LOAD = "load";
	private final static String NEW = "new";
	private final static String TEST = "test";
	private final static String BUTTON = "button";
	private final static String PAD = "pad";
	private final static String CLOCK = "clock";
	private final static String SELECTOR = "selector";
	private final static String FILE_EXPLORER = "file_explorer";
	private final static String SAMPLER = "sampler";

	private Logger logger;

	public CommunicationImpl() {
		this.logger = new Logger(CommunicationImpl.class.getSimpleName(), true);
		this.server = new Server();
	}

	@Override
	public void connect(int port, boolean printRequest) {
		server.connect(this, port, printRequest);
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

		switch (component) {
		case TEST:
			App.test();
			break;
		case BUTTON:
			if (args.get(NAME) == BANK) {
				int number = Integer.parseInt(args.get(NUMBER));
				
				switch (args.get(ACTION)) {
				case PRESSED:
					inputs.bankButtonPressed(number);
					break;
				case LEAVED:
					inputs.bankButtonLeaved(number);
					break;
				}
			} else {
				Button button = Button.getButtonCorrespondingToString(args.get(NAME));

				if (button != null) {
					switch (args.get(ACTION)) {
					case PRESSED:
						inputs.buttonPressed(button);
						break;
					case LEAVED:
						inputs.buttonLeaved(button);
						break;
					}
				} else {
					logger.log(Level.WARNING, "Unknown button: \"" + args.get(NAME) + "\"");
				}
			}
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
			inputs.selectorChanged(Selector.getSelectorCorrespondingToString(args.get(NAME)),
					Integer.parseInt(args.get(VALUE)));
			break;
		case CLOCK:
			switch (args.get(ACTION)) {
			case TICK:
				break;
			}
			break;
		case FILE_EXPLORER:
			switch (args.get(ACTION)) {
			case LOAD:
				String path = args.get(PATH).replace(App.ROOT_DIR_FOR_FILES + "/", ""); // TODO: ugly
				inputs.fileLoaded(path); // TODO: temporary?
				break;
			case NEW:
				logger.log(Level.INFO, "new");
				inputs.fileCreated((args.get(NAME) != null ? args.get(NAME) : ""),
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
