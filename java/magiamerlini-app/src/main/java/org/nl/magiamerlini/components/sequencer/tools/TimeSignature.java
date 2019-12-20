package org.nl.magiamerlini.components.sequencer.tools;

import java.io.Serializable;

public class TimeSignature {
	private int step;
	private int beat;
	private int bar;

	TimeSignature(int bar, int beat, int step) {
		set(bar, beat, step);
	}

	void set(int bar, int beat, int step) {
		this.bar = bar;
		this.beat = beat;
		this.step = step;
	}

	void setStep(int step) {
		this.step = step;
	}

	void setBeat(int beat) {
		this.beat = beat;
	}

	void setBar(int bar) {
		this.bar = bar;
	}

	int getStep() {
		return step;
	}

	int getBeat() {
		return beat;
	}

	int getBar() {
		return bar;
	}

	public String toString() {
		return bar + "-" + beat + "-" + step;
	}

}
