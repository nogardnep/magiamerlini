package org.nl.magiamerlini.controllers.tools;

public enum Mode {
	Project, Song, SequenceEdit, PatternEdit, PatternLaunch, AudioSampler, AudioMixer, AudioEffects, VideoSampler, VideoMixer, VideoEffects;

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
