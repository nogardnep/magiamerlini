package org.nl.magiamerlini.communication.tools;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.BaseComponent;

public abstract class CommunicatingComponent extends BaseComponent {

	protected Communication communication;

	public CommunicatingComponent(Communication communication) {
		this.communication = communication;
	}
}
