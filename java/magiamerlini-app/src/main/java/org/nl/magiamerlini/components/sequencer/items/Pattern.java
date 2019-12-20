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
import javax.persistence.Transient;

import org.nl.magiamerlini.data.items.Item;
import org.nl.magiamerlini.data.tools.Alias;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "pattern")
public class Pattern extends Item implements Serializable {
	public final static String STEPS_PARAMETER = "steps";
	public final static String BEATS_PARAMETER = "beats";
	public final static String BARS_PARAMETER = "bars";
	public final static String TRIGGER_MODE_PARAMETER = "trigger_mode";
	public final static String REPETITIONS_PARAMETER = "repetitions";
	public final static float TRIGGER_MODE_ON_STEP = 0;
	public final static float TRIGGER_MODE_ON_BEAT = 1;
	public final static float TRIGGER_MODE_ON_BAR = 2;
	public final static float REPETITIONS_LOOP = 0;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "bank")
	private int bank;

	@OneToMany(targetEntity = PatternEvent.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PatternEvent> patternEvents;

	@Column(name = STEPS_PARAMETER)
	@Parameter(min = 3, max = 8, step = 1, defaultValue = 4)
	private float steps;

	@Column(name = BEATS_PARAMETER)
	@Parameter(min = 1, max = 8, step = 1, defaultValue = 4)
	private float beats;

	@Column(name = BARS_PARAMETER)
	@Parameter(min = 1, max = 16, step = 1, defaultValue = 4)
	private float bars;

	@Column(name = TRIGGER_MODE_PARAMETER)
	@Parameter(min = 0, max = 3, step = 1, defaultValue = 0, aliases = {
			@Alias(name = "on_step", value = TRIGGER_MODE_ON_BAR),
			@Alias(name = "on_beat", value = TRIGGER_MODE_ON_BAR),
			@Alias(name = "on_bar", value = TRIGGER_MODE_ON_BAR) })
	private float triggerMode;

	@Column(name = REPETITIONS_PARAMETER)
	@Parameter(min = 0, max = 1, step = 1, defaultValue = 0, aliases = {
			@Alias(name = "loop", value = REPETITIONS_LOOP) })
	private float repetitions;
	
	@Transient
	boolean playing;

	public Pattern() {
		super();
		this.playing = false;
	}

	public Pattern(int bank, int number) {
		this();
		this.number = number;
		this.bank = bank;
		patternEvents = new HashSet<PatternEvent>();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", bank=" + bank + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return "pattern_" + bank + "-" + number;
	}
	
	public void addPatternEvent(PatternEvent patternEvent) {
		patternEvents.add(patternEvent);
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

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public float getSteps() {
		return steps;
	}

	public void setSteps(float steps) {
		this.steps = steps;
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

	public float getTriggerMode() {
		return triggerMode;
	}

	public void setTriggerMode(float triggerMode) {
		this.triggerMode = triggerMode;
	}

	public float getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(float repetitions) {
		this.repetitions = repetitions;
	}

	public Set<PatternEvent> getPatternEvents() {
		return patternEvents;
	}

	public void setPatternEvents(Set<PatternEvent> patternEvents) {
		this.patternEvents = patternEvents;
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	
	public boolean isPlaying() {
		return playing;
	}

}
