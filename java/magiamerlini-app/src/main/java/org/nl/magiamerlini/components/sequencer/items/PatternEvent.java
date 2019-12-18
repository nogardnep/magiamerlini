package org.nl.magiamerlini.components.sequencer.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "pattern_event")
public class PatternEvent extends Item {
	public final static int INACTIVE_STATE = 0;
	public final static int PLAY_STATE = 1;
	public final static int STOP_STATE = 1;
	public static final String VELOCITY = "velocity";
	public static final String DETUNE = "detune";
	public static final String TRANSPOSE = "transpose";
	public static final String STEPS_LENGTH = "steps_length";
	public static final String BEATS_LENGTH = "beats_length";
	public static final String BARS_LENGTH = "bars_length";

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "bank")
	private int bank;

	@Column(name = "state")
	private int state;

	public PatternEvent() {
		super();
	}

	@Column(name = VELOCITY)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0.5f)
	private float velocity;

	@Column(name = DETUNE)
	@Parameter(min = -1, max = 1, step = 0.01f, defaultValue = 0)
	private float detune;

	@Column(name = TRANSPOSE)
	@Parameter(min = -100, max = 100, step = 1, defaultValue =0)
	private float transpose;
	
	@Column(name = STEPS_LENGTH)
	@Parameter(min = 0, max = 100, step = 1, defaultValue = 0)
	private float stepsLength;
	
	@Column(name = BEATS_LENGTH)
	@Parameter(min = 0, max = 100, step = 1, defaultValue = 1)
	private float beatsLength;
	
	@Column(name = BARS_LENGTH)
	@Parameter(min = 0, max = 100, step = 1, defaultValue = 0)
	private float barLength;

	public PatternEvent(int bank, int number, int state) {
		this();
		this.bank = bank;
		this.number = number;
		this.state = state;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", bank=" + bank + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return "pattern-event_" + bank + "-" + number;
	}
}
