package org.nl.magiamerlini.components.ui.tools;

import com.google.common.base.CaseFormat;

public enum Switch {
	Metronome, Precount, Record;
	
	public boolean correspondingToString(String string) {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());
	
		return snakeCaseName.equals(string);
	}

	public static Switch getSwitchCorrespondingToString(String string) {
		Switch found = null;

		for (Switch oneSwitch : values()) {
			if (oneSwitch.correspondingToString(string)) {
				found = oneSwitch;
			}
		}

		return found;
	}
}
