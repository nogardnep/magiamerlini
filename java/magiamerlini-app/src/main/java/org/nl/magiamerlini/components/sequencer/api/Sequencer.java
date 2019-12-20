package org.nl.magiamerlini.components.sequencer.api;

import org.nl.magiamerlini.components.Component;
import org.nl.magiamerlini.components.sequencer.items.PatternEvent;
import org.nl.magiamerlini.components.sequencer.items.SequenceEvent;

public interface Sequencer extends Component {

	public void play();

	public void stop();

	public void pause();

	public void clockTicked();

	public void sendBpm();

	public void moveForward();

	public void moveBackward();

	public void playTrack(int bank, int number, float velocity);

	public boolean isPlaying();

	public void setSequenceEventState(SequenceEvent sequenceEvent, int state);

	public void setPatternEventState(PatternEvent patternEvent, int state);

	public void setPatternEventVelocity(PatternEvent patternEvent, float velocity);

}
