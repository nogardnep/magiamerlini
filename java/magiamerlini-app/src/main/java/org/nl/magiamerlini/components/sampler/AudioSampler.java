package org.nl.magiamerlini.components.sampler;

import org.nl.magiamerlini.communication.Communication;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;

public class AudioSampler extends Sampler  {
	
	public AudioSampler(Communication communication) {
		super(communication, "audio_sampler");
	}

	public void setTrackArmed(AudioSamplerTrack samplerTrack, boolean state) {
		samplerTrack.setArmed(state);
		updateDisplay();
	}
	
}
