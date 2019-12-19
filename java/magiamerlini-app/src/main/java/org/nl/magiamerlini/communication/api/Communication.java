package org.nl.magiamerlini.communication.api;

import org.nl.magiamerlini.components.ui.api.Inputs;

public interface Communication {
	public void connect(int port, boolean displayMessages);
	
	public void receiveMessage(String message);
	
	public void sendMessage(String message);
	
	public void testMessage(String message);
	
	public void setInputs(Inputs inputs);
}
