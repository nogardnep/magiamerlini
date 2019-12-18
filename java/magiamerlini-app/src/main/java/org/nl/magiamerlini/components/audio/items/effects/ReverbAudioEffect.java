package org.nl.magiamerlini.components.audio.items.effects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "reverb_audio_effect")
public class ReverbAudioEffect extends Item {
	public final static String LEVEL_PARAMETER = "level";
	public final static String TIME_PARAMETER = "time";
	public final static String TONE_PARAMETER = "tone";

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = LEVEL_PARAMETER)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float level;

	@Column(name = TIME_PARAMETER)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float time;

	@Column(name = TONE_PARAMETER)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float tone;

	public ReverbAudioEffect() {
		super();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String toDisplay() {
		return "reverb";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLevel() {
		return level;
	}

	public void setLevel(float level) {
		this.level = level;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public float getTone() {
		return tone;
	}

	public void setTone(float tone) {
		this.tone = tone;
	}

}
