package org.nl.magiamerlini.components.sequencer.implementations;

import java.util.logging.Level;

import org.nl.magiamerlini.communication.api.Communication;
import org.nl.magiamerlini.communication.tools.CommunicatingComponent;
import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.components.sequencer.api.Sequencer;

public class CommunicatingSequencer extends CommunicatingComponent implements Sequencer {

	public static final int NUMBER_STEP_BY_BEAT = 24;
	public static final int INITIAL_BPM = 120;

	private static final String POSITION = "position";
	private static final String REPETITION = "repetition";
	private static final String TICK = "tyck";
	private static final String BEAT = "beat";
	private static final String BAR = "bar";
	private static final String BPM = "bpm";
	private static final String STEP_BY_BEAT = "step_by_beat";
	private static final String STEP = "step";
	private static final String PLAY = "play";
	private static final String PAUSE = "pause";
	private static final String CLOCK = "clock";

	private boolean playing;
	private int currentRepetition;
	private int currentStep;
	private int currentTick;
	private int currentBeat;
	private int currentBar;
	private int currentBpm;

	public CommunicatingSequencer(Communication communication) {
		super(communication, "sequencer");
		currentBpm = INITIAL_BPM;
		playing = false;
	}

	@Override
	public void play() {
		sendBpm(); // TODO: remove
		sendMessage(CLOCK + " " + PLAY);
	}

	@Override
	public void pause() {
		playing = false;
		sendMessage(CLOCK + " " +PAUSE);
	}

	@Override
	public void stop() {
		pause();
		restoreToStart();
	}

	@Override
	public void moveForward() {
		currentBeat++; // TODO: change
		updateDisplay();
	}

	@Override
	public void moveBackward() {
		currentBeat--; // TODO: change
		updateDisplay();
	}

	@Override
	public void clockTicked() {
		moveOn();
	}

	@Override
	public void sendBpm() {
		String[] messageParts = { BPM, String.valueOf(currentBpm), STEP_BY_BEAT, String.valueOf(NUMBER_STEP_BY_BEAT) };
		sendMessage(messageParts);
	}

	private void updateDisplay() {
		logger.log(Level.ALL, REPETITION + " " + currentRepetition);
		sendMessage(POSITION + " " + REPETITION + " " + currentRepetition);
		sendMessage(POSITION + " " + BEAT + " " + currentBeat);
		sendMessage(POSITION + " " + BAR + " " + currentBar);
		sendMessage(POSITION + " " + STEP + " " + currentStep);
		sendMessage(POSITION + " " + TICK + " " + currentTick);
	}

	@Override
	public void playTrack(int bank, int number, float velocity) {
		AudioSamplerTrack audioSamplerTrack = mainController.getProjectManager().getAudioSamplerTrack(bank, number);
		VideoSamplerTrack videoSamplerTrack = mainController.getProjectManager().getVideoSamplerTrack(bank, number);
		mainController.getAudioSampler().playTrack(audioSamplerTrack, velocity);
		mainController.getVideoSampler().playTrack(videoSamplerTrack, velocity);
	}

	private void moveOn() {
		logger.log(Level.ALL, "MOVE ON");
//		currentTick++; // TODO: change
//		updateDisplay();
	}

	private void restoreToStart() {
		currentTick = 0;
		currentRepetition = 0;
		currentStep = 0;
		currentBeat = 0;
		currentBar = 0;
		updateDisplay();
	}

	public int getCurrentRepetition() {
		return currentRepetition;
	}

	public void setCurrentRepetition(int currentRepetition) {
		this.currentRepetition = currentRepetition;
	}

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

	public int getCurrentBeat() {
		return currentBeat;
	}

	public void setCurrentBeat(int currentBeat) {
		this.currentBeat = currentBeat;
	}

	public int getCurrentBar() {
		return currentBar;
	}

	public void setCurrentBar(int currentBar) {
		this.currentBar = currentBar;
	}

}
