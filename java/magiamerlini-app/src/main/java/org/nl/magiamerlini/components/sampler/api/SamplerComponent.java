package org.nl.magiamerlini.components.sampler.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.sampler.items.SamplerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public interface SamplerComponent extends Component {
	public void playTrack(SamplerTrack samplerTrack, float velocity);

	public void stopTrack(SamplerTrack samplerTrack);

	public void editTrackParameter(SamplerTrack samplerTrack, ParameterSnapshot parameter);

	public void loadTrackSample(SamplerTrack samplerTrack, String path);

	public void trackStopped(SamplerTrack samplerTrack);
}
