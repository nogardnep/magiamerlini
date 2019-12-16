package org.nl.magiamerlini.components.mixer.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.mixer.api.Mixer;
import org.nl.magiamerlini.data.entities.MixerTrack;
import org.nl.magiamerlini.data.tools.Parameter;

public class MixerImpl extends CommunicatingComponent implements Mixer {
	private final static String COMPONENT_NAME = "mixer";
	private final static String EDIT_PARAMETER = "edit_parameter";

	public MixerImpl(Communication communication) {
		super(communication);
	}

	@Override
	public void editTrackParameter(MixerTrack mixerTrack, Parameter parameter) {
		sendMessage(mixerTrack, EDIT_PARAMETER, parameter.getName(), parameter.getDisplayedValue());
	}

	private void sendMessage(MixerTrack mixerTrack, String action, String parameter1, String parameter2) {
		communication.sendMessage(
				COMPONENT_NAME + " " + mixerTrack.getNumber() + " " + action + " " + parameter1 + " " + parameter2);
	}

}
