package org.nl.magiamerlini.communication;

import org.nl.magiamerlini.components.ui.Inputs;
import org.nl.magiamerlini.utils.Logger;

public class OutputCommunication {
	private ClientHandler client;
	
	@SuppressWarnings("unused")
	private Logger logger;
	
	public OutputCommunication() {
		this.logger = new Logger(this.getClass().getSimpleName(), true);
		this.client = new ClientHandler();
	}
	
	public void connect(OutputCommunication communication, String host, int port, boolean displayMessages) {
		client.connect(this, host, port);
	}
}
