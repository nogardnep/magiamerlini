package org.nl.magiamerlini.controllers;

import java.util.logging.Level;

import org.nl.magiamerlini.controllers.tools.BaseController;

public class PresetController extends BaseController  {

	public PresetController(MainController mainController) {
		super(mainController);
	}

	public void createBank(int number) {
		// TODO
		logger.log(Level.INFO, "createBank " + number);
	}

	public void loadBank(int number) {
		// TODO
		logger.log(Level.INFO, "loadBank " + number);
	}

	public void saveBank(int number) {
		// TODO
		logger.log(Level.INFO, "saveBank " + number);
	}
}
