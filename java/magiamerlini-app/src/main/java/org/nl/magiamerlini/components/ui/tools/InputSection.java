package org.nl.magiamerlini.components.ui.tools;

import org.nl.magiamerlini.utils.EnumUtils;

public enum InputSection {
	Navigation, Player, Action, Padboard, Bank, Mode, Pattern, Track, Page, Sequence, Metronome, Precount, Record;

	public static InputSection getCorrespondingToString(String string) {
		InputSection found = null;

		for (InputSection section : values()) {
			if (EnumUtils.correspondingToString(section.name(), string)) {
				found = section;
			}
		}

		return found;
	}
}
