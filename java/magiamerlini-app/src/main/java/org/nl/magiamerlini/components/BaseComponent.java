package org.nl.magiamerlini.components;

import org.nl.magiamerlini.controllers.api.MainController;

public abstract class BaseComponent implements Component {
	protected MainController mainController;
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
