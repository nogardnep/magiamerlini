package org.nl.magiamerlini.components.audio.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.data.items.SamplerTrack;
import org.nl.magiamerlini.data.tools.Parameter;

public interface AudioSampler extends Component {
	public void playTrack(SamplerTrack samplerTrack, float velocity);

	public void stopTrack(SamplerTrack samplerTrack);

	public void editTrackParameter(SamplerTrack samplerTrack, Parameter parameter);

	public void loadTrackSample(SamplerTrack samplerTrack, String path);

	public void armTrackForRecording();
}
