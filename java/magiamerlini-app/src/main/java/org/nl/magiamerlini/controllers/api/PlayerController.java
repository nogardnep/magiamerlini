package org.nl.magiamerlini.controllers.api;

public interface PlayerController {

	public 	void moveForward();
	
	public 	void moveBackward();

	public void play();

	public void pause();
	
	public void stop();
	
	public void playTrack(int number, float velocity);

}
