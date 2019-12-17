package org.nl.magiamerlini.components.ui.tools;

import com.google.common.base.CaseFormat;

public enum Color {
	Red, Blue, Yellow, Orange, Grey, Green;
	
	public boolean correspondingToString(String string) {
		return getCorrespondingString().equals(string);
	}

	public static Color getCorrespondingToString(String string) {
		Color found = null;

		for (Color color : values()) {
			if (color.correspondingToString(string)) {
				found = color;
			}
		}

		return found;
	}

	public String getCorrespondingString() {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());
		
		return snakeCaseName;
	}
}
