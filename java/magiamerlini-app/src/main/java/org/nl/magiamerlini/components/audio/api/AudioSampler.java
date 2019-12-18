package org.nl.magiamerlini.components.audio.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.audio.items.AudioSamplerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public interface AudioSampler extends Component {
	public void playTrack(AudioSamplerTrack samplerTrack, float velocity);

	public void stopTrack(AudioSamplerTrack samplerTrack);

	public void editTrackParameter(AudioSamplerTrack samplerTrack, ParameterSnapshot parameter);

	public void loadTrackSample(AudioSamplerTrack samplerTrack, String path);

	public void armTrackForRecording();
}
