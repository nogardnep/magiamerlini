package org.nl.magiamerlini.components.ui.tools;

import com.google.common.base.CaseFormat;

public enum SwitchType {
	Metronome, Precount, Record;
	
	public boolean correspondingToString(String string) {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());
	
		return snakeCaseName.equals(string);
	}

	public static SwitchType getSwitchCorrespondingToString(String string) {
		SwitchType found = null;

		for (SwitchType oneSwitch : values()) {
			if (oneSwitch.correspondingToString(string)) {
				found = oneSwitch;
			}
		}

		return found;
	}
}
