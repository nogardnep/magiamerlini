package org.nl.magiamerlini.components.ui.implementations;

import java.util.ArrayList;
import java.util.List;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.ui.api.Padboard;
import org.nl.magiamerlini.components.ui.tools.Color;
import org.nl.magiamerlini.components.ui.tools.Pad;
import org.nl.magiamerlini.data.tools.Item;

public class CommunicatingPadboard extends CommunicatingComponent implements Padboard {
	private final static String COMPONENT_NAME = "pad";
	
	private List<Pad> pads;

	public CommunicatingPadboard(Communication communication) {
		super(communication);
		pads = new ArrayList<Pad>();

		for (int i = 0; i < Configuration.Pads.getValue(); i++) {
			pads.add(new Pad(i));
		}
	}

	@Override
	public void updateDisplay() {
		for (Pad pad : pads ) {
			updatePad(pad);
		}
	}

	@Override
	public void updatePadDisplay(int padNum) {
		updatePad(pads.get(padNum));
	}
	
	private void updatePad(Pad pad) {
		Item item = mainController.getItemCorrespondingTo(pad.getNumber());

		pad.setLightIntensity(1);
		pad.setBlinkingSpeed(Pad.SPEED_FOR_NO_BLINKING);

		if (mainController.getSelectionController().isFirstSelected(item)) {
			pad.setLightColor(Color.Orange);
		} else if (mainController.getSelectionController().isSelected(item)) {
			pad.setLightColor(Color.Yellow);
		} else {
			pad.setLightColor(Color.Green);
			//pad.setIntensity(0);
		}

		displayPad(pad);
		
	}

	private void displayPad(Pad pad) {
		communication.sendMessage(COMPONENT_NAME + " " + pad.getNumber() + " intensity " + pad.getLightIntensity());
		communication.sendMessage(COMPONENT_NAME + " " + pad.getNumber() + " color " + pad.getLightColor().getCorrespondingString());
		communication.sendMessage(COMPONENT_NAME + " " + pad.getNumber() + " speed " + pad.getBlinkingSpeed());
	}
}
