package org.nl.magiamerlini.controllers.tools;

public enum Mode {
	Project, Song, Sequence, Pattern, Sampler, Mixer, Video;

	public int getIndex() {
		return ordinal();
	}

	public String getName() {
		return name();
	}

	public static Mode getCorrespondingMode(int value) {
		Mode found = null;

		for (Mode mode : Mode.values()) {
			if (mode.getIndex() == value) {
				found = mode;
				break;
			}
		}

		return found;
	}
}
