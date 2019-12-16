package org.nl.magiamerlini.components.video.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.video.api.Video;

public class VideoImpl extends CommunicatingComponent implements Video {

	public VideoImpl(Communication communication) {
		super(communication);
	}

}
