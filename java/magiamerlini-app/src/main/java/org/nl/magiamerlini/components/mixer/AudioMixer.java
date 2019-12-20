package org.nl.magiamerlini.components.mixer;

import org.nl.magiamerlini.communication.Communication;

public class AudioMixer extends Mixer {
	public AudioMixer(Communication communication) {
		super(communication, "audio_mixer");
	}

}
