package org.nl.magiamerlini.components.audio.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.data.items.MixerTrack;
import org.nl.magiamerlini.data.tools.Parameter;

public interface AudioMixer extends Component {

	void editTrackParameter(MixerTrack item, Parameter parameter);

}
