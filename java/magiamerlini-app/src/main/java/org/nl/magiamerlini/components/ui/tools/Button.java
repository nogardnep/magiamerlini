package org.nl.magiamerlini.components.ui.tools;

import com.google.common.base.CaseFormat;

public enum Button {
	New, Load, Save, Select, Move, RightArrow, LeftArrow, UpArrow, DownArrow, Backward, Forward, Play, Pause;

	public boolean correspondingToString(String string) {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());
	
		return snakeCaseName.equals(string);
	}

	public static Button getButtonCorrespondingToString(String string) {
		Button found = null;

		for (Button button : values()) {
			if (button.correspondingToString(string)) {
				found = button;
			}
		}

		return found;
	}
}
