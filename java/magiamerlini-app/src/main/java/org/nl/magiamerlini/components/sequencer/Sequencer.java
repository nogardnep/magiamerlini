package org.nl.magiamerlini.components.sequencer;

import java.util.logging.Level;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.communication.InputCommunication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.components.sequencer.items.PatternEvent;
import org.nl.magiamerlini.components.sequencer.items.SequenceEvent;
import org.nl.magiamerlini.components.sequencer.tools.Position;
import org.nl.magiamerlini.components.sequencer.tools.TimeSignature;

public class Sequencer extends CommunicatingComponent  {

	public static final int INITIAL_BPM = 120;

	private static final String POSITION = "position";
	private static final String TICK = "tick";
	private static final String BEAT = "beat";
	private static final String BAR = "bar";
	private static final String TURN = "turn";
	private static final String BPM = "bpm";
	private static final String TICKS_BY_BEAT = "ticks_by_beat";
	private static final String PLAY = "play";
	private static final String PAUSE = "pause";
	private static final String CLOCK = "clock";
	private static final String METRONOME = "metronome";
	private static final String ON_BAR = "on_bar";
	private static final String ON_BEAT = "on_beat";

	private boolean playing;
	private Position masterPosition;
	private TimeSignature masterTimeSignature;
	private int masterBpm;

	public Sequencer(InputCommunication communication) {
		super(communication, "sequencer");
		this.masterBpm = INITIAL_BPM;
		this.playing = false;
		this.masterTimeSignature = new TimeSignature(4, 4, 4);
		this.masterPosition = new Position(0, 0, 0, masterTimeSignature);
	}

	public void play() {
		sendBpm(); // TODO: remove
		sendMessage(CLOCK + " " + PLAY);
	}

	public void pause() {
		playing = false;
		sendMessage(CLOCK + " " + PAUSE);
	}

	public void stop() {
		pause();
		masterPosition.restore();
		updateDisplay();
	}

	public void moveForward() {
		masterPosition.move(0, 1, 0);
		updateDisplay();
	}

	public void moveBackward() {
		masterPosition.move(0, -1, 0);
		updateDisplay();
	}

	public void clockTicked() {
		if (masterPosition.onBar()) {
			sendMessage(METRONOME + " " + PLAY + " " + ON_BAR);
		} else if (masterPosition.onBeat()) {
			sendMessage(METRONOME + " " + PLAY + " " + ON_BEAT);
		}

		masterPosition.move(0, 0, 1);
		updateDisplay();
	}

	public void sendBpm() {
		String[] messageParts = { BPM, String.valueOf(masterBpm), TICKS_BY_BEAT,
				String.valueOf(Configuration.TicksByBeat.getValue()) };
		sendMessage(messageParts);
	}

	private void updateDisplay() {
		sendMessage(POSITION + " " + TURN + " " + masterPosition.getTurn());
		sendMessage(POSITION + " " + BEAT + " " + masterPosition.getBeat());
		sendMessage(POSITION + " " + BAR + " " + masterPosition.getBar());
		sendMessage(POSITION + " " + TICK + " " + masterPosition.getTick());
		mainController.getPadboard().updateDisplay();
	}

	public void playTrack(int bank, int number, float velocity) {
		AudioSamplerTrack audioSamplerTrack = mainController.getProjectManager().getAudioSamplerTrack(bank, number);
		VideoSamplerTrack videoSamplerTrack = mainController.getProjectManager().getVideoSamplerTrack(bank, number);
		mainController.getAudioSampler().playTrack(audioSamplerTrack, velocity);
		mainController.getVideoSampler().playTrack(videoSamplerTrack, velocity);
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setSequenceEventState(SequenceEvent sequenceEvent, int state) {
		sequenceEvent.setState(state);
		mainController.getProjectManager().update(sequenceEvent);
	}

	public void setPatternEventState(PatternEvent patternEvent, int state) {
		logger.log(Level.ALL ,"NEW STATE " + state);
		patternEvent.setState(state);
		mainController.getProjectManager().update(patternEvent);
		updateDisplay();
	}

	public void setPatternEventVelocity(PatternEvent patternEvent, float velocity) {
		patternEvent.setVelocity(velocity);
		mainController.getProjectManager().update(patternEvent);
		updateDisplay();
	}

}
