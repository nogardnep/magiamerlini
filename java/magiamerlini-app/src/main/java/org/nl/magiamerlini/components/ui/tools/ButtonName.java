package org.nl.magiamerlini.components.ui.tools;

import org.nl.magiamerlini.utils.EnumUtils;

public enum ButtonName {
	Load, New, Save, Edit, Copy, Move, Left, Right, Up, Down, Backward, Forward, Play, Pause;

	public static ButtonName getCorrespondingToString(String string) {
		ButtonName found = null;

		for (ButtonName name : values()) {
			if (EnumUtils.correspondingToString(name.name(), string)) {
				found = name;
			}
		}

		return found;
	}
}
