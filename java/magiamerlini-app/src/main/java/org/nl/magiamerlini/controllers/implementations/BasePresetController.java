package org.nl.magiamerlini.controllers.implementations;

import java.util.logging.Level;

import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.controllers.api.PresetController;
import org.nl.magiamerlini.controllers.tools.BaseController;

public class BasePresetController extends BaseController implements PresetController {

	public BasePresetController(MainController mainController) {
		super(mainController);
	}

	@Override
	public void createBank(int number) {
		// TODO
		logger.log(Level.INFO, "createBank " + number);
	}

	@Override
	public void loadBank(int number) {
		// TODO
		logger.log(Level.INFO, "loadBank " + number);
	}

	@Override
	public void saveBank(int number) {
		// TODO
		logger.log(Level.INFO, "saveBank " + number);
	}
}
