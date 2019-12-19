package org.nl.magiamerlini.controllers.tools;

import org.nl.magiamerlini.components.ui.tools.ButtonName;
import org.nl.magiamerlini.utils.EnumUtils;

import com.google.common.base.CaseFormat;

public enum FileType {

	Project("projects"), Preset("presets"), Sound("sounds"), Image("images");

	private String path;

	FileType(String path) {
		this.path = path;
	}

	public static FileType getCorrespondingToString(String string) {
		FileType found = null;

		for (FileType type : values()) {
			if (EnumUtils.correspondingToString(type.name(), string)) {
				found = type;
			}
		}

		return found;
	}

	public String getPath() {
		return path;
	}
}
