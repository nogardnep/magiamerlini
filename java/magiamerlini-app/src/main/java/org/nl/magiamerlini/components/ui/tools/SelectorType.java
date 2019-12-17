package org.nl.magiamerlini.components.ui.tools;

public enum SelectorType {
	Sequence, Pattern, Page, Track, Mode;

	public boolean correspondingToString(String string) {
		return name().toLowerCase().equals(string);
	}

	public static SelectorType getSelectorCorrespondingToString(String string) {
		SelectorType found = null;

		for (SelectorType selector : values()) {
			if (selector.correspondingToString(string)) {
				found = selector;
			}
		}

		return found;
	}
}
