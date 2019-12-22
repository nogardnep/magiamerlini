package org.nl.magiamerlini.components.sequencer.items;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Item;

@Entity
@Table(name = "sequence_event")
public class SequenceEvent extends Item implements Serializable {
	public final static int INACTIVE_STATE = 0;
	public final static int PLAY_STATE = 1;
	public final static int STOP_STATE = 2;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "pattern_number")
	private int patternNumber;

	@Column(name = "bar")
	private int bar;

	@Column(name = "beat")
	private int beat;

	@Column(name = "state")
	private int state;

	public SequenceEvent() {
		super();
	}

	public SequenceEvent(int patternNumber, int bar, int beat, int state) {
		this();
		this.patternNumber = patternNumber;
		this.bar = bar;
		this.beat = beat;
		this.state = state;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", pattern=" + patternNumber + ", bar=" + bar
				+ ", beat=" + beat + "]";
	}

	@Override
	public String toDisplay() {
		return "sequence-event_" + patternNumber + "-" + bar + "-" + beat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatternNumber() {
		return patternNumber;
	}

	public void setPatternNumber(int patternNumber) {
		this.patternNumber = patternNumber;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
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
}
