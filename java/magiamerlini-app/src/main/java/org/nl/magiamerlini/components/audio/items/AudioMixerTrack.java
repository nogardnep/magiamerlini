package org.nl.magiamerlini.components.audio.items;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.nl.magiamerlini.components.audio.items.effects.ReverbAudioEffect;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "audio_mixer_track")
public class AudioMixerTrack extends Item {
	private final static String OUTPUT_CHANNEL = "output_channel";
	private final static String VOLUME = "volume";
	private final static String EFFECTS_SEND = "effects_send";

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "mute")
	private boolean mute;

	@OneToOne(targetEntity = ReverbAudioEffect.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<ReverbAudioEffect> reverbAudioEffect;

	@Column(name = VOLUME)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float volume;

	@Column(name = OUTPUT_CHANNEL)
	@Parameter(min = 1, max = 16, step = 1, defaultValue = 1)
	private int outputChannel;

	@Column(name = EFFECTS_SEND)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float effectsSend;

	public AudioMixerTrack() {
		super();
		mute = false;
	}

	public AudioMixerTrack(int number) {
		this();
		this.number = number;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return "audio-mixer-track_" + number;
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

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public float getEffectsSend() {
		return effectsSend;
	}

	public void setEffectsSend(float effectsSend) {
		this.effectsSend = effectsSend;
	}

	public Collection<ReverbAudioEffect> getReverbAudioEffect() {
		return reverbAudioEffect;
	}

	public void setReverbAudioEffect(Collection<ReverbAudioEffect> reverbAudioEffect) {
		this.reverbAudioEffect = reverbAudioEffect;
	}

}
