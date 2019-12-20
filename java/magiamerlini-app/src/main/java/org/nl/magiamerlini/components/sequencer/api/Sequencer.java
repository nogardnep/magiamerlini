package org.nl.magiamerlini.components.sequencer.api;

import org.nl.magiamerlini.components.Component;

public interface Sequencer extends Component {

	public void play();

	public void stop();

	public void pause();

	public void clockTicked();

	public void sendBpm();

	public void moveForward();

	public void moveBackward();

	public void playTrack(int bank, int number, float velocity);

}
