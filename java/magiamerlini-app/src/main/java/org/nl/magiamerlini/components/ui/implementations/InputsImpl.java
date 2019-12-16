package org.nl.magiamerlini.components.ui.implementations;

import java.util.HashMap;
import java.util.Map;

import org.nl.magiamerlini.App;
import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.BaseComponent;
import org.nl.magiamerlini.components.ui.api.Inputs;
import org.nl.magiamerlini.components.ui.tools.Button;
import org.nl.magiamerlini.components.ui.tools.Selector;
import org.nl.magiamerlini.components.ui.tools.Switch;
import org.nl.magiamerlini.data.entities.SamplerTrack;

public class InputsImpl extends BaseComponent implements Inputs {
	private final static boolean EMPTY_SELECTED_ITEMS_ON_LEAVING_EDIT_BUTTON = false;
	private final static boolean PRESSED_STATE = true;
	public Map<Button, Boolean> buttonStates;
	public Map<Integer, Boolean> padStates;
	public Map<Integer, Boolean> bankButtonStates;
	public Map<Switch, Boolean> switchStates;

	public InputsImpl() {
		buttonStates = new HashMap<Button, Boolean>();
		padStates = new HashMap<Integer, Boolean>();
		bankButtonStates = new HashMap<Integer, Boolean>();
		switchStates = new HashMap<Switch, Boolean>();

		for (Button button : Button.values()) {
			buttonStates.put(button, !PRESSED_STATE);
		}

		for (int i = 0; i < Configuration.Pads.getValue(); i++) {
			padStates.put(i, !PRESSED_STATE);
		}

		for (int i = 0; i < Configuration.Banks.getValue(); i++) {
			bankButtonStates.put(i, !PRESSED_STATE);
		}

		for (Switch oneSwitch : Switch.values()) {
			switchStates.put(oneSwitch, !PRESSED_STATE);
		}
	}

	@Override
	public void switchPressed(Switch oneSwitch) {
		switchStates.put(oneSwitch, PRESSED_STATE);
	}

	@Override
	public void switchLeaved(Switch oneSwitch) {
		switchStates.put(oneSwitch, !PRESSED_STATE);
	}

	@Override
	public void bankButtonPressed(int number) {
		bankButtonStates.put(number, PRESSED_STATE);
	}

	@Override
	public void bankButtonLeaved(int number) {
		bankButtonStates.put(number, !PRESSED_STATE);
	}

	@Override
	public void padPressed(int padNum, float velocity) {
		padStates.put(padNum, PRESSED_STATE);

		if (mainController.isReady()) {
			switch (mainController.getMode()) {
			case Sampler:
				SamplerTrack samplerTrack = mainController.getProjectManager()
						.getSamplerTrack(mainController.getSelectedBankIndex(), padNum);

				if (mainController.isSelecting()) {
					mainController.getItemsSelector().toggleSelected(samplerTrack);
				} else {
					mainController.getSampler().playTrack(samplerTrack, velocity);
				}
				break;
			default:
				break;
			}

			mainController.getPadboard().update();
		}
	}

	@Override
	public void padLeaved(int padNum) {
		padStates.put(padNum, !PRESSED_STATE);
	}

	@Override
	public void fileCreated(String name, String path) {
		if (mainController.isReady()) {
			switch (mainController.getMode()) {
			case Project:
				mainController.createProject(name, path);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void fileLoaded(String path) {
		switch (mainController.getMode()) {
		case Project:
			mainController.loadProject(path);
			break;
		case Sampler:
			if (mainController.isReady()) {
				SamplerTrack samplerTrack = mainController.getProjectManager()
						.getSamplerTrack(mainController.getSelectedBankIndex(), mainController.getSelectedTrackIndex());
				mainController.getSampler().loadTrackSample(samplerTrack, path);
				break;
			}
		default:
			break;
		}
	}

	@Override
	public void buttonPressed(Button button) {
		buttonStates.put(button, PRESSED_STATE);

		switch (button) {
		case Load:
			switch (mainController.getMode()) {
			case Project:
				mainController.getFileExplorer().open(App.ROOT_DIR_FOR_FILES + "/" + App.PROJECTS_FOLDER);
				break;
			default:
				break;
			}
			break;
		case New:
			switch (mainController.getMode()) {
			case Project:
				mainController.getFileExplorer().create();
				break;
			default:
				break;
			}
			break;
		default:
			break;

		}

		if (mainController.isReady()) {
			switch (button) {
			case Select:
				mainController.setSelecting(true);
				break;
			case New:
				switch (mainController.getMode()) {
				case Sampler:
					break;
				default:
					break;
				}
				break;
			case Load:
				switch (mainController.getMode()) {
				case Sampler:
					mainController.getFileExplorer().open(App.ROOT_DIR_FOR_FILES + "/" + App.SOUNDS_FOLDER);
					break;
				default:
					break;
				}
				break;
			case LeftArrow:
				mainController.getItemsSelector().modifyEditingParameterIndex(-1);
				break;
			case RightArrow:
				mainController.getItemsSelector().modifyEditingParameterIndex(1);
				break;
			case UpArrow:
				mainController.getItemsSelector().modifyEditingParameterValue(1);
				break;
			case DownArrow:
				mainController.getItemsSelector().modifyEditingParameterValue(-1);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void buttonLeaved(Button button) {
		buttonStates.put(button, !PRESSED_STATE);

		if (mainController.isReady()) {
			switch (button) {
			case Select:
				mainController.setSelecting(false);

				if (EMPTY_SELECTED_ITEMS_ON_LEAVING_EDIT_BUTTON) {
					mainController.getItemsSelector().emptySelectedItems();
					mainController.getPadboard().update();
				}
				break;
			case Load:
				mainController.setLoading(false);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void selectorChanged(Selector selector, int value) {
		switch (selector) {
		case Mode:
			mainController.changeMode(value);
			break;
		default:
			if (mainController.isReady()) {
				switch (selector) {
				case Sequence:
					mainController.changeSelectedSequence(value);
					break;
				case Pattern:
					mainController.changeSelectedPattern(value);
					break;
				case Page:
					mainController.changeSelectedPage(value);
					break;
				case Track:
					mainController.changeSelectedTrack(value);
					break;
				default:
					break;
				}
			}
			break;
		}
	}
}
