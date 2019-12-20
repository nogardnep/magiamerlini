package org.nl.magiamerlini.controllers;

import java.util.logging.Level;

import org.nl.magiamerlini.components.sampler.items.AudioSamplerTrack;
import org.nl.magiamerlini.components.sampler.items.VideoSamplerTrack;
import org.nl.magiamerlini.controllers.tools.BaseController;

// TODO: delete?
public class PlayerController extends BaseController {
	public PlayerController(MainController mainController) {
		super(mainController);
	}

	public void moveForward() {
		// TODO
		logger.log(Level.INFO, "moveForward");
	}

	public void moveBackward() {
		// TODO
		logger.log(Level.INFO, "moveBackward");
	}

	public void play() {
		logger.log(Level.INFO, "play");
	}

	public void pause() {
		// TODO
		logger.log(Level.INFO, "pause");

	}

	public void stop() {
		// TODO
		logger.log(Level.INFO, "stop");
	}

	public void playTrack(int bank, int number, float velocity) {
		AudioSamplerTrack audioSamplerTrack = mainController.getProjectManager().getAudioSamplerTrack(bank, number);
		VideoSamplerTrack videoSamplerTrack = mainController.getProjectManager().getVideoSamplerTrack(bank, number);
		mainController.getAudioSampler().playTrack(audioSamplerTrack, velocity);
		mainController.getVideoSampler().playTrack(videoSamplerTrack, velocity);
	}
}
