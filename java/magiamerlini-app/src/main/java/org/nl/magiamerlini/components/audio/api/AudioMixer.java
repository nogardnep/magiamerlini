package org.nl.magiamerlini.components.audio.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.audio.items.AudioMixerTrack;
import org.nl.magiamerlini.data.tools.ParameterSnapshot;

public interface AudioMixer extends Component {

	void editTrackParameter(AudioMixerTrack item, ParameterSnapshot parameter);

}
