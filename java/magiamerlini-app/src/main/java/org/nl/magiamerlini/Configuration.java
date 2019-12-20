package org.nl.magiamerlini;

public enum Configuration {
	TicksByBeat(24), Pads(16), Banks(4), Tracks(16), Patterns(16), Sequences(16), Parameters(30), AudioSamplerOutputs(16), AudioMixerOutputs(4), VideoSamplerOutputs(16), VideoMixerOutputs(4);

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
