package org.nl.magiamerlini.components.sampler.api;

import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;

public interface AudioSampler extends Sampler {
	public void setTrackArmed(AudioSamplerTrack samplerTrack, boolean state);
}
