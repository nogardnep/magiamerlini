package org.nl.magiamerlini.components.video.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.video.api.VideoSampler;

public class CommunicatingVideoSampler extends CommunicatingComponent implements VideoSampler {

	public CommunicatingVideoSampler(Communication communication) {
		super(communication);
	}

}
