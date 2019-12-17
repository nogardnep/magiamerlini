package org.nl.magiamerlini.components.video.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.video.api.VideoMixer;

public class VideoMixerImpl extends CommunicatingComponent implements VideoMixer {

	public VideoMixerImpl(Communication communication) {
		super(communication);
	}

}
