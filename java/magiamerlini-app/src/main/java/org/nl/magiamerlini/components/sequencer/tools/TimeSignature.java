package org.nl.magiamerlini.components.sequencer.tools;

public class TimeSignature {
	private int step;
	private int beat;
	private int bar;

	public TimeSignature(int bar, int beat, int step) {
		set(bar, beat, step);
	}

	public void set(int bar, int beat, int step) {
		this.bar = bar;
		this.beat = beat;
		this.step = step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public void setBeat(int beat) {
		this.beat = beat;
	}

	public void setBar(int bar) {
		this.bar = bar;
	}

	public int getStep() {
		return step;
	}

	public int getBeat() {
		return beat;
	}

	public int getBar() {
		return bar;
	}

	public String toString() {
		return bar + "-" + beat + "-" + step;
	}

}
