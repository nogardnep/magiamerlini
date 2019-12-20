package org.nl.magiamerlini.components.sampler.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.sampler.api.AudioSampler;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;

public class CommunicatingAudioSampler extends CommunicatingSampler implements AudioSampler {
	
	public CommunicatingAudioSampler(Communication communication) {
		super(communication, "audio_sampler");
	}

	@Override
	public void setTrackArmed(AudioSamplerTrack samplerTrack, boolean state) {
		samplerTrack.setArmed(state);
		updateDisplay();
	}
	
}
