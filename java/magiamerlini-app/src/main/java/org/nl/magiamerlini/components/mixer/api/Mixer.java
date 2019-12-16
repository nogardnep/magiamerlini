package org.nl.magiamerlini.components.mixer.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.data.entities.MixerTrack;
import org.nl.magiamerlini.data.tools.Parameter;

public interface Mixer extends Component {

	void editTrackParameter(MixerTrack item, Parameter parameter);

}
