package org.nl.magiamerlini;

public enum Configuration {
	Pads(16), Banks(4), Tracks(16), Patterns(16), Sequences(16), Parameters(16), AudioSamplerOutputs(16), AudioMixerOutputs(4), VideoSamplerOutputs(16), VideoMixerOutputs(4), MinSteps(3), MaxSteps(8), MinBeats(1), MaxBeats(8), MinBars(1), MaxBars(16);

	private int value;

	Configuration(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public String toString() {
		return String.valueOf(value);
	}
}
