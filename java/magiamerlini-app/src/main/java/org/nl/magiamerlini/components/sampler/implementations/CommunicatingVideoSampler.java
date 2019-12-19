package org.nl.magiamerlini.components.sampler.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.sampler.api.VideoSampler;

public class CommunicatingVideoSampler extends CommunicatingSampler implements VideoSampler {

	public CommunicatingVideoSampler(Communication communication) {
		super(communication, "video_sampler");
	}

}
