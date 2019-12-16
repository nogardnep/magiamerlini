package org.nl.magiamerlini.utils;

import java.util.logging.Level;

public class Logger {
	private boolean show;
	private String name;

	public Logger(String name, boolean show) {
		this.show = show;
		this.name = name;
	}

	public void show() {
		show = true;
	}

	public void hide() {
		show = false;
	}

	public void log(Level level, String message) {
		if (show) {
			if (level == Level.SEVERE) {
				System.err.println(formatMessage("ERROR", message));
			} else if (level == Level.WARNING) {
				System.err.println(formatMessage("WARN", message));
			} else {
				System.out.println(formatMessage("LOG", message));
			}
		}
	}

	private String formatMessage(String type, String message) {
		return type + " " + name + " " + message;
	}
}
