package org.nl.magiamerlini.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.Configuration;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "mixer_track")
public class MixerTrack implements Item {
	private final static String OUTPUT_CHANNEL = "output_channel";
	private final static String VOLUME = "volume";
	private final static String REVERB = "reverb";
	private final static String ECHO = "echo";
	private final static String CHORUS = "chorus";

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = OUTPUT_CHANNEL)
	private int outputChannel;

	@Column(name = VOLUME)
	private float volume;

	@Column(name = REVERB)
	private float reverb;

	@Column(name = ECHO)
	private float echo;

	@Column(name = CHORUS)
	private float chorus;

	public MixerTrack() {
	}

	public MixerTrack(int number) {
		this.number = number;

		volume= 1;
		outputChannel = 0;
		reverb = 0;
		echo = 0;
		chorus = 0;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", number=" + number + "]";
	}

	@Override
	public List<Parameter> getParameters() {
		List<Parameter> parameters = new ArrayList<Parameter>();

		parameters.add(new Parameter(VOLUME, volume, 0, 1, 0.01f));
		parameters.add(
				new Parameter(OUTPUT_CHANNEL, outputChannel, 0, (Configuration.AudioMixerOutputs.getValue() - 1), 1));
		parameters.add(new Parameter(REVERB, reverb, 0, 1, 0.01f));
		parameters.add(new Parameter(ECHO, echo, 0, 1, 0.01f));
		parameters.add(new Parameter(CHORUS, chorus, 0, 1, 0.01f));
		
		return parameters;
	}

	@Override
	public void applyParameter(Parameter parameter) {
		switch (parameter.getName()) {
		case VOLUME:
			setVolume(parameter.getValue());
			break;
		case OUTPUT_CHANNEL:
			setOutputChannel((int) parameter.getValue());
			break;
		case REVERB:
			setReverb(parameter.getValue());
			break;
		case ECHO:
			setEcho(parameter.getValue());
			break;
		case CHORUS:
			setChorus(parameter.getValue());
			break;
		default:
			System.err.println("can't find parameter: \"" + parameter.getName() + "\"");
		}
	}

	@Override
	public String toDisplay() {
		return "mixer-track-" + number;
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

	public int getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(int outputChannel) {
		this.outputChannel = outputChannel;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getReverb() {
		return reverb;
	}

	public void setReverb(float reverb) {
		this.reverb = reverb;
	}

	public float getEcho() {
		return echo;
	}

	public void setEcho(float echo) {
		this.echo = echo;
	}

	public float getChorus() {
		return chorus;
	}

	public void setChorus(float chorus) {
		this.chorus = chorus;
	}

}
