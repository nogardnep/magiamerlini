package org.nl.magiamerlini.components.mixer.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.mixer.items.MixerTrack;
import org.nl.magiamerlini.components.mixer.items.effects.Effect;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public interface MixerComponent extends Component {
	void editTrackParameter(MixerTrack mixerTrack, ParameterSnapshot parameter);

	void setEffectActivated(Effect effect, boolean state);
}
