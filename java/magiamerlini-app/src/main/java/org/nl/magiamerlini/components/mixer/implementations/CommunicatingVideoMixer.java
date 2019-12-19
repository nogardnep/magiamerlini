package org.nl.magiamerlini.components.mixer.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.mixer.api.VideoMixer;

public class CommunicatingVideoMixer extends CommunicatingMixer implements VideoMixer {

	public CommunicatingVideoMixer(Communication communication) {
		super(communication, "video_mixer");
	}


}
