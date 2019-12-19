package org.nl.magiamerlini.components.sequencer.implementations;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;

public class CommunicatingSequencer extends CommunicatingComponent implements Sequencer {

	public CommunicatingSequencer(Communication communication) {
		super(communication, "sequencer");
	}

}
