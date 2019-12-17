package org.nl.magiamerlini.controllers.tools;

import org.nl.magiamerlini.components.ui.tools.ButtonName;

import com.google.common.base.CaseFormat;

public enum FileType {

	Project("projects"), Preset("presets"), Sound("sounds"), Image("images");

	private String path;

	FileType(String path) {
this.path = path;
	}

	public boolean correspondingToString(String string) {
		return getCorrespondingString().equals(string);
	}

	public static FileType getCorrespondingToString(String string) {
		FileType found = null;

		for (FileType type : values()) {
			if (type.correspondingToString(string)) {
				found = type;
			}
		}

		return found;
	}

	public String getCorrespondingString() {
		String snakeCaseName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name());

		return snakeCaseName;
	}

	public String getPath() {
		return path;
	}
}
