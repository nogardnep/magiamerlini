package org.nl.magiamerlini.components.sequencer.items;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "pattern_event")
public class PatternEvent extends Item implements Serializable {
	public final static int INACTIVE_STATE = 0;
	public final static int PLAY_STATE = 1;
	public final static int STOP_STATE = 2;
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

	@Column(name = "state")
	private int state;
	
	@Column(name = "track_number")
	private int trackNumber;
	
	@Column(name = "bar")
	private int bar;
	
	@Column(name = "beat")
	private int beat;
	
	@Column(name = "tick")
	private int tick;

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
	private float barsLength;

	public PatternEvent(int trackNumber, int bar, int beat, int tick, int state) {
		this();
		this.trackNumber = trackNumber;
		this.bar = bar;
		this.beat= beat;
		this.tick= tick;
		this.state = state;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", track=" + trackNumber + ", bar=" + bar + ", beat=" + beat + ", tick=" + tick + "]";
	}

	@Override
	public String toDisplay() {
		return "pattern-event_" + trackNumber + "-" + bar + "-" + beat + "-" + tick;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public int getBar() {
		return bar;
	}

	public void setBar(int bar) {
		this.bar = bar;
	}

	public int getBeat() {
		return beat;
	}

	public void setBeat(int beat) {
		this.beat = beat;
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public float getDetune() {
		return detune;
	}

	public void setDetune(float detune) {
		this.detune = detune;
	}

	public float getTranspose() {
		return transpose;
	}

	public void setTranspose(float transpose) {
		this.transpose = transpose;
	}

	public float getStepsLength() {
		return stepsLength;
	}

	public void setStepsLength(float stepsLength) {
		this.stepsLength = stepsLength;
	}

	public float getBeatsLength() {
		return beatsLength;
	}

	public void setBeatsLength(float beatsLength) {
		this.beatsLength = beatsLength;
	}

	public float getBarsLength() {
		return barsLength;
	}

	public void setBarsLength(float barsLength) {
		this.barsLength = barsLength;
	}
	
	
}
