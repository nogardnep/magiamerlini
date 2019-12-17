package org.nl.magiamerlini.controllers.tools;

import org.nl.magiamerlini.controllers.api.MainController;
import org.nl.magiamerlini.utils.Logger;

public class BaseController {
	protected MainController mainController;
	protected Logger logger;

	public BaseController(MainController mainController) {
		this.mainController = mainController;
		this.logger = new Logger(BaseController.class.getSimpleName(), true);
	}
}
