package org.nl.magiamerlini.components.ui.tools;

public enum Selector {
	Sequence, Pattern, Page, Track, Mode;

	public boolean correspondingToString(String string) {
		return name().toLowerCase().equals(string);
	}

	public static Selector getSelectorCorrespondingToString(String string) {
		Selector found = null;

		for (Selector selector : values()) {
			if (selector.correspondingToString(string)) {
				found = selector;
			}
		}

		return found;
	}
}
