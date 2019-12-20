package org.nl.magiamerlini.components.sequencer.tools;

public enum TimeSignatureConfiguration {
	MinSteps(3), MaxSteps(8), MinBeats(1), MaxBeats(8), MinBars(1), MaxBars(16);
	
	private float value;

	TimeSignatureConfiguration(float value) {
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}

	public String toString() {
		return String.valueOf(value);
	}
}
