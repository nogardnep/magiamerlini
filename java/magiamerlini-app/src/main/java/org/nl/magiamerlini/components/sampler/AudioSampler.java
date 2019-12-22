package org.nl.magiamerlini.components.sampler;

import org.nl.magiamerlini.communication.InputCommunication;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;

public class AudioSampler extends Sampler  {
	
	public AudioSampler(InputCommunication communication) {
		super(communication, "audio_sampler");
	}

	public void setTrackArmed(AudioSamplerTrack samplerTrack, boolean state) {
		samplerTrack.setArmed(state);
		updateDisplay();
	}
	
}
