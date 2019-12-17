package org.nl.magiamerlini.data.items;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.components.sequencer.tools.PatternPlayingMode;
import org.nl.magiamerlini.components.sequencer.tools.ScoreTriggerMode;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "TODO")
public class Pattern implements Item {
	// TODO
	public final static String STEPS_PARAMETER = "steps";
	public final static String BEATS_PARAMETER = "beats";
	public final static String BARS_PARAMETER = "bars";
	public final static String TRIGGER_MODE_PARAMETER = "trigger_mode";
	public final static String PLAYING_MODE_PARAMETER = "playing_mode";

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	// TODO
	@Column(name = "number")
	private int number;

	@Column(name = "bank")
	private int bank;

	@Column(name = STEPS_PARAMETER)
	private int steps;

	@Column(name = BEATS_PARAMETER)
	private int beats;

	@Column(name = BARS_PARAMETER)
	private int bars;

	@Column(name = TRIGGER_MODE_PARAMETER)
	private int triggerMode;

	@Column(name = PLAYING_MODE_PARAMETER)
	private int playingMode;

	public Pattern() {
	}

	public Pattern(int bank, int number) {
		this.number = number;
		this.bank = bank;

		// TODO: default values
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", bank=" + bank + ", number=" + number + "]";
	}

	@Override
	public List<Parameter> getParameters() {
		List<Parameter> parameters = new ArrayList<Parameter>();

		parameters.add(new Parameter(STEPS_PARAMETER, steps, Configuration.MinSteps.getValue(),
				Configuration.MaxSteps.getValue(), 1));
		parameters.add(new Parameter(BEATS_PARAMETER, beats, Configuration.MinBeats.getValue(),
				Configuration.MaxBeats.getValue(), 1));
		parameters.add(new Parameter(BARS_PARAMETER, bars, Configuration.MinBars.getValue(),
				Configuration.MaxBars.getValue(), 1));
		parameters.add(new Parameter(TRIGGER_MODE_PARAMETER, triggerMode, 0, ScoreTriggerMode.values().length - 1, 1));
		parameters
				.add(new Parameter(PLAYING_MODE_PARAMETER, playingMode, 0, PatternPlayingMode.values().length - 1, 1));

		return parameters;
	}

	@Override
	public void applyParameter(Parameter parameter) {
		switch (parameter.getName()) {
		// TODO
		default:
			System.err.println("can't find parameter: \"" + parameter.getName() + "\"");
		}
	}

	@Override
	public String toDisplay() {
		return "pattern-" + bank + "-" + number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
