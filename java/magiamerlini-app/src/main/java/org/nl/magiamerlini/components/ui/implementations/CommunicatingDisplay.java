package org.nl.magiamerlini.components.ui.implementations;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public class CommunicatingDisplay extends CommunicatingComponent implements Display {
	private final static String EMPTY = "----";

	public CommunicatingDisplay(Communication communication) {
		super(communication, "display");
	}

	@Override
	public void displayProject() {
		String name;

		if (mainController.getProjectManager().getCurrentProject() != null) {
			name = mainController.getProjectManager().getCurrentProject().getName();
		} else {
			name = EMPTY;
		}

		sendMessage("project_name " + name);
	}

	@Override
	public void displayProjects() {
		// TODO
	}

	@Override
	public void displaySelectedParameter() {
		ParameterSnapshot parameter = mainController.getSelectionController().getEditingParameter();
		String index = String.valueOf(mainController.getSelectionController().getEditingParameterIndex() + 1);
		String name = (parameter != null ? parameter.getName() : EMPTY);
		String value = (parameter != null ? String.valueOf(parameter.getValue()) : EMPTY);

		sendMessage("parameter " + index + "." + name + "=" + value);
	}

	@Override
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

	@Override
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

	@Override
	public void displayPlayingInformations() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayPosition() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dipslayMasterSignature() {
		// TODO
	}

	@Override
	public void displaySamples() {
		// TODO Auto-generated method stub
	}

	@Override
	public void displayLoading() {
		sendMessage("loading " + (mainController.isReady() ? "started" : "ended"));
	}

	@Override
	public void displayMode() {
		sendMessage("mode_name " + mainController.getCurrentMode().getName());
	}
}
