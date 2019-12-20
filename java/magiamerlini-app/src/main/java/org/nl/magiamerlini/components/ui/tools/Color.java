package org.nl.magiamerlini.components.ui.tools;

import org.nl.magiamerlini.utils.EnumUtils;

public enum Color {
	Red, Green, Blue, Orange, Yellow, Grey, Pink, Emerald;

	public Color getCorrespondingToString(String string) {
		Color found = null;

		for (Color color : values()) {
			if (EnumUtils.correspondingToString(color.name(), string)) {
				found = color;
			}
		}

		return found;
	}
}
