package org.nl.magiamerlini.components.sequencer.tools;

import java.io.Serializable;

class Position {
	private TimeSignature timeSignature;
	private int tick;
	private int bar;
	private int beat;
	private int currentTurn;
	private int repetitions; // TODO: keep?

	public Position(int bar, int beat, int tick) {
		set(bar, beat, tick);
		currentTurn = 0;
	}

	private void set(int bar, int beat, int tick) {
		this.bar = bar;
		this.beat = beat;
		this.tick = tick;
	}

	public static Position copy(Position position) {
		return new Position(position.getBar(), position.getBeat(), position.getTick());
	}

	public void move(int barProgression, int beatProgression, int tickProgression) {
		// TODO: do not work moving backward

//		tickProgression += tick;
//		double beatToAdd = Math.ceil((((double) tickProgression) - (Player.TICK_BY_BEAT - 1)) / Player.TICK_BY_BEAT);
//
//		beatProgression += beat + ((int) beatToAdd);
//		double barToAdd = Math
//				.ceil((((double) beatProgression) - (timeSignature.getBeat() - 1)) / timeSignature.getBeat());
//
//		barProgression += bar + ((int) barToAdd);
//		double turnToAdd = Math
//				.ceil((((double) barProgression) - (timeSignature.getBar() - 1)) / timeSignature.getBar());
//
//		if (tickProgression >= 0) {
//			tick = tickProgression % Player.TICK_BY_BEAT;
//		} else {
//			tick = (Player.TICK_BY_BEAT + tickProgression) % Player.TICK_BY_BEAT;
//		}
//
//		if (beatProgression >= 0) {
//			beat = beatProgression % timeSignature.getBeat();
//		} else {
//			beat = (timeSignature.getBeat() + beatProgression) % timeSignature.getBeat();
//		}
//
//		if (barProgression >= 0) {
//			bar = barProgression % timeSignature.getBar();
//		} else {
//			bar = (timeSignature.getBar() + barProgression) % timeSignature.getBar();
//		}
//
//		currentTurn += turnToAdd;
	}

	public void moveOnBeat(int numberToTravel) {
		// TODO: do not work
//		System.out.println("----------");
//		int beatTraveledNumber = 0;
//
//		for (int i = 0; i < (Player.TICK_BY_BEAT * numberToTravel); i++) {
//			move(0, 0, (numberToTravel > 0 ? 1 : -1));
//
//			if (onBeat()) {
//				beatTraveledNumber++;
//
//				if (beatTraveledNumber == numberToTravel) {
//					return;
//				}
//			}
//		}
	}

	public void setTimeSignature(TimeSignature timeSignature) {
		this.timeSignature = timeSignature;
	}

	public boolean equals(Position otherPosition) {
		return bar == otherPosition.getBar() && beat == otherPosition.getBeat() && tick == otherPosition.getTick();
	}

	public boolean onStep() {
		// TODO: ugly
		boolean onStep = false;

		if (timeSignature.getStep() == 3) {
			switch (tick) {
			case 0:
			case 4:
			case 8:
				onStep = true;
				break;
			}
		} else if (timeSignature.getStep() == 4) {
			switch (tick) {
			case 0:
			case 3:
			case 6:
			case 9:
				onStep = true;
				break;
			}
		}

		return onStep;
	}

	public boolean onBeat() {
		if (onStep() && tick == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean onBar() {
		if (onStep() && onBeat() && beat == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int getTick() {
		return tick;
	}

	public int getBeat() {
		return beat;
	}

	public int getBar() {
		return bar;
	}

	public TimeSignature getTimeSignature() {
		return timeSignature;
	}

	public String toString() {
		return bar + "-" + beat + "-" + tick;
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void restore() {
		set(0, 0, 0);
		currentTurn = 0;
	}
}
