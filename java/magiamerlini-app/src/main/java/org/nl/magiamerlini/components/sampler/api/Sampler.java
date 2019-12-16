package org.nl.magiamerlini.components.sampler.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.data.entities.SamplerTrack;
import org.nl.magiamerlini.data.tools.Parameter;

public interface Sampler extends Component {
	public void playTrack(SamplerTrack samplerTrack, float velocity);

	public void stopTrack(SamplerTrack samplerTrack);

	public void editTrackParameter(SamplerTrack samplerTrack, Parameter parameter);

	public void loadTrackSample(SamplerTrack samplerTrack, String path);

	public void armTrackForRecording();
}
