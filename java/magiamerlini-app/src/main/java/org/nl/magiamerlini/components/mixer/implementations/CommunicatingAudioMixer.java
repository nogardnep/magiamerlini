package org.nl.magiamerlini.components.mixer.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.mixer.api.AudioMixer;

public class CommunicatingAudioMixer extends CommunicatingMixer implements AudioMixer {
	public CommunicatingAudioMixer(Communication communication) {
		super(communication, "audio_mixer");
	}

}
