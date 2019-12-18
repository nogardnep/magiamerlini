package org.nl.magiamerlini.components.ui.implementations;

import java.util.HashMap;
import java.util.Map;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.ui.api.Display;
import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public class CommunicatingDisplay extends CommunicatingComponent implements Display {

	private final static String COMPONENT_NAME = "display";
	private final static String EMPTY = "----";

	public CommunicatingDisplay(Communication communication) {
		super(communication);
	}

	@Override
	public void displayProject() {
		String name = EMPTY;

		if (mainController.getProjectManager().getCurrentProject() != null) {
			mainController.getProjectManager().getCurrentProject().getName();
		}

		communication.sendMessage(COMPONENT_NAME + " project_name " + name);
	}

	@Override
	public void displayProjects() {
		// TODO
	}

	@Override
	public void displaySelectedParameter() {
		ParameterSnapshot parameter = mainController.getSelectionController().getEditingParameter();

		communication.sendMessage(
				COMPONENT_NAME + " parameter_index " + mainController.getSelectionController().getEditingParameterIndex());
		communication
				.sendMessage(COMPONENT_NAME + " parameter_name " + (parameter != null ? parameter.getName() : EMPTY));
		communication
				.sendMessage(COMPONENT_NAME + " parameter_value " + (parameter != null ? parameter.getValue() : EMPTY));
	}

	@Override
	public void displaySelectedItem() {
		Item item = mainController.getSelectionController().getFirstSelectedItem();
		communication.sendMessage("display selected_item " + (item != null ? item.getClass().getSimpleName() : EMPTY));
	}

	@Override
	public void displaySelection() {
		String informationsString = EMPTY;

		if (mainController.isReady()) {
			Map<String, Integer> informationsMap = new HashMap<String, Integer>();

			informationsMap.put("trk", mainController.getCurrentTrackIndex());
			informationsMap.put("pg", mainController.getCurrentPageIndex());
			informationsMap.put("ptn", mainController.getCurrentPatternIndex());
			informationsMap.put("seq", mainController.getCurrentSequenceIndex());

			for (Map.Entry<String, Integer> entry : informationsMap.entrySet()) {
				informationsString += entry.getKey() + "=" + entry.getValue() + "_";
			}
		}

		communication.sendMessage(COMPONENT_NAME + " selection " + informationsString);
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
		communication.sendMessage(COMPONENT_NAME + " loading " + (mainController.isReady() ? "started" : "ended"));
	}

	@Override
	public void displayMode() {
		communication.sendMessage(COMPONENT_NAME + " mode_name " + mainController.getCurrentMode().getName());
	}
}
