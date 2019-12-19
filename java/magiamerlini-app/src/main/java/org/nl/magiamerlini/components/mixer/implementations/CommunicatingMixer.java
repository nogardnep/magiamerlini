package org.nl.magiamerlini.components.mixer.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.mixer.api.MixerComponent;
import org.nl.magiamerlini.components.mixer.items.MixerTrack;
import org.nl.magiamerlini.components.mixer.items.effects.Effect;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public abstract class CommunicatingMixer extends CommunicatingComponent implements MixerComponent {
	private final static String EDIT_PARAMETER = "edit_parameter";

	public CommunicatingMixer(Communication communication, String name) {
		super(communication, name);
	}

	@Override
	public void editTrackParameter(MixerTrack mixerTrack, ParameterSnapshot parameter) {
		String[] message= {getIdentifierFor(mixerTrack),EDIT_PARAMETER,parameter.getName() , parameter.getDisplayedValue()};
		sendMessage(message);
	}

	@Override
	public void setEffectActivated(Effect effect, boolean state) {
		effect.setActivated(state);
		mainController.getProjectManager().updateEntity(effect);
		updateDisplay();
	}

	protected void updateDisplay() {
		mainController.updateDisplay();
	}

	protected String getIdentifierFor(MixerTrack mixerTrack) {
		return String.valueOf(mixerTrack.getNumber());
	}

}
