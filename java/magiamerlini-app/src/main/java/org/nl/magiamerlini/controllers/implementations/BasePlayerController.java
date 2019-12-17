package org.nl.magiamerlini.controllers.implementations;

import java.util.logging.Level;

import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.api.PlayerController;
import org.nl.magiamerlini.controllers.tools.BaseController;

public class BasePlayerController extends BaseController implements PlayerController {
	public BasePlayerController(MainController mainController) {
		super(mainController);
	}

	@Override
	public void moveForward() {
		// TODO
		logger.log(Level.INFO, "moveForward");
	}

	@Override
	public void moveBackward() {
		// TODO
		logger.log(Level.INFO, "moveBackward");
	}

	@Override
	public void play() {
		// TODO
		logger.log(Level.INFO, "play");
	}

	@Override
	public void pause() {
		// TODO
		logger.log(Level.INFO, "pause");
	}

	@Override
	public void stop() {
		// TODO
		logger.log(Level.INFO, "stop");
	}

	@Override
	public void playTrack(int number, float velocity) {
		// TODO
		logger.log(Level.INFO, "playTrack number=" + number + " velocity=" + velocity);
	}
}
