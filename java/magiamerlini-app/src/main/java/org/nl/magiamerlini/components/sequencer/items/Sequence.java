package org.nl.magiamerlini.components.sequencer.items;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nl.magiamerlini.components.sequencer.tools.TimeSignature;
import org.nl.magiamerlini.data.tools.Alias;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "sequence")
public class Sequence extends Item implements Serializable {
	public final static String BPM_PARAMETER = "bpm";
	public final static String BEATS_PARAMETER = "beats";
	public final static String BARS_PARAMETER = "bars";
	public final static String STEPS_PARAMETER = "steps";
	public final static String REPETITIONS_PARAMETER = "repetitions";
	public final static float REPETITIONS_LOOP = 0;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@OneToMany(targetEntity = SequenceEvent.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<SequenceEvent> sequenceEvents;

	@Column(name = BPM_PARAMETER)
	@Parameter(min = 20, max = 400, step = 1, defaultValue = 120)
	private float bpm;

	@Column(name = BEATS_PARAMETER)
	@Parameter(min = 1, max = 8, step = 1, defaultValue = 4)
	private float beats;

	@Column(name = BARS_PARAMETER)
	@Parameter(min = 1, max = 16, step = 1, defaultValue = 4)
	private float bars;

	@Column(name = REPETITIONS_PARAMETER)
	@Parameter(min = 0, max = 1, step = 1, defaultValue = 0, aliases = {
			@Alias(name = "loop", value = REPETITIONS_LOOP) })
	private float repetitions;

	public Sequence() {
		super();
	}

	public Sequence(int number) {
		this();
		this.number = number;
		sequenceEvents = new HashSet<SequenceEvent>();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return "sequence_" + number;
	}

	public void addSequenceEvent(SequenceEvent sequenceEvent) {
		sequenceEvents.add(sequenceEvent);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Set<SequenceEvent> getSequenceEvents() {
		return sequenceEvents;
	}

	public void setSequenceEvents(Set<SequenceEvent> sequenceEvents) {
		this.sequenceEvents = sequenceEvents;
	}

	public float getBpm() {
		return bpm;
	}

	public void setBpm(float bpm) {
		this.bpm = bpm;
	}

	public float getBeats() {
		return beats;
	}

	public void setBeats(float beats) {
		this.beats = beats;
	}

	public float getBars() {
		return bars;
	}

	public void setBars(float bars) {
		this.bars = bars;
	}

	public float getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(float repetitions) {
		this.repetitions = repetitions;
	}

	public TimeSignature getTimeSignature() {
		return new TimeSignature((int) bars, (int) beats, 0);
	}

}
