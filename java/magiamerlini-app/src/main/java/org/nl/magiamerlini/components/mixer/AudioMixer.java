package org.nl.magiamerlini.components.mixer;

import org.nl.magiamerlini.communication.InputCommunication;

public class AudioMixer extends Mixer {
	public AudioMixer(InputCommunication communication) {
		super(communication, "audio_mixer");
	}

}
