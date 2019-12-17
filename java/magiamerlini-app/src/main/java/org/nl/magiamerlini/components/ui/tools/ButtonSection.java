package org.nl.magiamerlini.components.ui.tools;

import com.google.common.base.CaseFormat;

public enum ButtonSection {
	Navigation, Player, Action, Padboard, Bank, Mode, Pattern, Track, Page, Sequence;
	
	public boolean correspondingToString(String string) {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());

		return snakeCaseName.equals(string);
	}

	
	public static ButtonSection getCorrespondingToString(String string) {
		ButtonSection found = null;

		for (ButtonSection section : values()) {
			if (section.correspondingToString(string)) {
				found = section;
			}
		}

		return found;
	}
}
