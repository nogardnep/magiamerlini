package org.nl.magiamerlini.communication.tools;

import java.util.logging.Level;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.components.BaseComponent;

public abstract class CommunicatingComponent extends BaseComponent {

	private Communication communication;
	private String name;

	public CommunicatingComponent(Communication communication, String name) {
		super();
		this.communication = communication;
		this.name = name;
	}

	protected void sendMessage(String message) {
		communication.sendMessage(name + " " + message);
	}

	protected void sendMessage(String[] messageParts) {
		String message = "";

		for (String part : messageParts) {
			message += part + " ";
		}

		logger.log(Level.ALL, message);
		
		communication.sendMessage(name + " " + message);
	}
}
