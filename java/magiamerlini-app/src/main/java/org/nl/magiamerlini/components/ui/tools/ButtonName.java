package org.nl.magiamerlini.components.ui.tools;

import com.google.common.base.CaseFormat;

public enum ButtonName {
	Load, New, Save, Edit, Copy, Move, Left, Right, Up, Down, Backward, Forward, Play, Pause;

	public boolean correspondingToString(String string) {
		return getCorrespondingString().equals(string);
	}

	public static ButtonName getCorrespondingToString(String string) {
		ButtonName found = null;

		for (ButtonName name : values()) {
			if (name.correspondingToString(string)) {
				found = name;
			}
		}

		return found;
	}

	public String getCorrespondingString() {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());
		
		return snakeCaseName;
	}
}
