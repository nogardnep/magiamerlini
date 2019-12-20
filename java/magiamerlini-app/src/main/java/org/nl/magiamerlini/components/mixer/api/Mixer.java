package org.nl.magiamerlini.components.mixer.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.mixer.items.Effect;
import org.nl.magiamerlini.components.mixer.items.MixerTrack;
import org.nl.magiamerlini.components.sampler.items.SamplerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public interface Mixer extends Component {
	public void trackParameterEdited(MixerTrack mixerTrack, ParameterSnapshot parameter);

	public void effectParameterEdited(Effect effect, ParameterSnapshot parameter);

	public void setEffectActivated(Effect effect, boolean state);

	public void setTrackMuted(MixerTrack mixerTrack, boolean state);

	public void updateTrack(MixerTrack mixerTrack);

	public void updateEffect(Effect effect);
}
