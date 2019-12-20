package org.nl.magiamerlini.components.sequencer.tools;

import org.nl.magiamerlini.Configuration;

public class Position {
	private TimeSignature timeSignature;
	private int tick;
	private int bar;
	private int beat;
	private int turn; // TODO: keep?
	private int repetitions; // TODO: keep?

	public Position(int bar, int beat, int tick, TimeSignature timeSignature) {
		set(bar, beat, tick);
		turn = 0;
		setTimeSignature(timeSignature);
	}

	private void set(int bar, int beat, int tick) {
		this.bar = bar;
		this.beat = beat;
		this.tick = tick;
	}

	public static Position copy(Position position) {
		return new Position(position.getBar(), position.getBeat(), position.getTick(), position.getTimeSignature());
	}

	public void move(int barProgression, int beatProgression, int tickProgression) {
		// TODO: do not work moving backward
		
		int ticksByBeat  = Configuration.TicksByBeat.getValue();
		
		tickProgression += tick;
		double beatToAdd = Math.ceil((((double) tickProgression) - (ticksByBeat - 1)) / ticksByBeat);

		beatProgression += beat + ((int) beatToAdd);
		double barToAdd = Math
				.ceil((((double) beatProgression) - (timeSignature.getBeat() - 1)) / timeSignature.getBeat());

		barProgression += bar + ((int) barToAdd);
		double turnToAdd = Math
				.ceil((((double) barProgression) - (timeSignature.getBar() - 1)) / timeSignature.getBar());

		if (tickProgression >= 0) {
			tick = tickProgression % ticksByBeat;
		} else {
			tick = (ticksByBeat + tickProgression) % ticksByBeat;
		}

		if (beatProgression >= 0) {
			beat = beatProgression % timeSignature.getBeat();
		} else {
			beat = (timeSignature.getBeat() + beatProgression) % timeSignature.getBeat();
		}

		if (barProgression >= 0) {
			bar = barProgression % timeSignature.getBar();
		} else {
			bar = (timeSignature.getBar() + barProgression) % timeSignature.getBar();
		}

		turn += turnToAdd;
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
	
	public int getTurn() {
		return turn;
	}

	public TimeSignature getTimeSignature() {
		return timeSignature;
	}

	public String toString() {
		return bar + "-" + beat + "-" + tick;
	}

	public int getCurrentTurn() {
		return turn;
	}

	public void restore() {
		set(0, 0, 0);
		turn = 0;
	}
}
