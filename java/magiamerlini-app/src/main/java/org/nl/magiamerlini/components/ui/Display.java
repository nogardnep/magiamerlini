package org.nl.magiamerlini.components.ui;

import org.nl.magiamerlini.communication.InputCommunication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public class Display extends CommunicatingComponent {
	private final static String EMPTY = "----";

	public Display(InputCommunication communication) {
		super(communication, "display");
	}

	public void displayProject() {
		String name;

		if (mainController.getProjectManager().getCurrentProject() != null) {
			name = mainController.getProjectManager().getCurrentProject().getName();
		} else {
			name = EMPTY;
		}

		sendMessage("project_name " + name);
	}

	public void displayProjects() {
		// TODO
	}

	public void displaySelectedParameter() {
		ParameterSnapshot parameter = mainController.getSelectionController().getEditingParameter();
		String index = String.valueOf(mainController.getSelectionController().getEditingParameterIndex() + 1);
		String name = (parameter != null ? parameter.getName() : EMPTY);
		String value = (parameter != null ? String.valueOf(parameter.getValue()) : EMPTY);

		sendMessage("parameter " + index + "." + name + "=" + value);
	}

	public void displaySelectedItem() {
		String name;
		int number = mainController.getSelectionController().getSelectedItems().size();
		
		if (number > 0) {
			name = mainController.getSelectionController().getFirstSelectedItem().toDisplay();

			if (number > 1) {
				name += "_(+" + (number - 1) + ")";
			}
		} else {
			name = EMPTY;
		}

		sendMessage("selected_item " + name);
	}

	public void displaySelection() {
		String informationsString;

		if (mainController.isReady()) {
			informationsString = "";
			informationsString += "seq=" + mainController.getCurrentSequenceIndex();
			informationsString += "_ptn=" + mainController.getCurrentPatternIndex();
			informationsString += "_trk=" + mainController.getCurrentTrackIndex();
			informationsString += "_pg=" + mainController.getCurrentPageIndex();
		} else {
			informationsString = EMPTY;
		}

		sendMessage("selection " + informationsString);
	}

	public void displayPlayingInformations() {
		// TODO Auto-generated method stub

	}

	public void displayPosition() {
		// TODO Auto-generated method stub
	}

	public void dipslayMasterSignature() {
		// TODO
	}

	public void displaySamples() {
		// TODO Auto-generated method stub
	}

	public void displayLoading() {
		sendMessage("loading " + (mainController.isReady() ? "started" : "ended"));
	}

	public void displayMode() {
		sendMessage("mode_name " + mainController.getCurrentMode().getName());
	}
}
