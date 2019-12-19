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

	public void log(Level level, Object anything) {
		if (show) {
			if (level == Level.SEVERE) {
				System.err.println(formatMessage("ERROR", anything));
			} else if (level == Level.WARNING) {
				System.err.println(formatMessage("WARN", anything));
			} else {
				System.out.println(formatMessage("LOG", anything));
			}
		}
	}

	private String formatMessage(String type, Object anything) {
		return type + " (" + name + ")" + ": " + anything;
	}
}
