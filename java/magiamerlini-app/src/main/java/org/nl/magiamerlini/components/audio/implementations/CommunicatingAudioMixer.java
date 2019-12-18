package org.nl.magiamerlini.components.audio.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.audio.api.AudioMixer;
import org.nl.magiamerlini.components.audio.items.AudioMixerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public class CommunicatingAudioMixer extends CommunicatingComponent implements AudioMixer {
	private final static String COMPONENT_NAME = "mixer";
	private final static String EDIT_PARAMETER = "edit_parameter";

	public CommunicatingAudioMixer(Communication communication) {
		super(communication);
	}

	@Override
	public void editTrackParameter(AudioMixerTrack mixerTrack, ParameterSnapshot parameter) {
		sendMessage(mixerTrack, EDIT_PARAMETER, parameter.getName(), parameter.getDisplayedValue());
	}

	private void sendMessage(AudioMixerTrack mixerTrack, String action, String parameter1, String parameter2) {
		communication.sendMessage(
				COMPONENT_NAME + " " + mixerTrack.getNumber() + " " + action + " " + parameter1 + " " + parameter2);
	}

}
