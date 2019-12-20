package org.nl.magiamerlini.controllers.tools;

import org.nl.magiamerlini.controllers.MainController;
import org.nl.magiamerlini.utils.Logger;

public class BaseController {
	protected MainController mainController;
	protected Logger logger;

	public BaseController(MainController mainController) {
		this.mainController = mainController;
		this.logger = new Logger(this.getClass().getSimpleName(), true);
	}
}
