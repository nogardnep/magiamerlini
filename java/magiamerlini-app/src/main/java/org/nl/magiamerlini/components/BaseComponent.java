package org.nl.magiamerlini.components;

import org.nl.magiamerlini.controllers.MainController;
import org.nl.magiamerlini.utils.Logger;

public abstract class BaseComponent  {
	protected MainController mainController;
	protected Logger logger;

	public BaseComponent() {
		this.logger = new Logger(this.getClass().getSimpleName(), true);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
