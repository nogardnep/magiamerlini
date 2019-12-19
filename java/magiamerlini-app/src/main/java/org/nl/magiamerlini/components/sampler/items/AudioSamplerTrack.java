package org.nl.magiamerlini.components.sampler.items;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.nl.magiamerlini.data.tools.Alias;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "audio_sampler_track")
public class AudioSamplerTrack extends SamplerTrack implements Serializable {
	public final static float CHOKE_NO_CHOKE = 0;
	public final static float PLAYING_MODE_TRIGGER = 0;
	public final static float PLAYING_MODE_HOLD = 1;

	private final static String VOLUME = "volume";
	private final static String CHOKE = "choke";
	private final static String OUTPUT_CHANNEL = "output_channel";
	private final static String VOICES = "voices";
	private final static String PLAYING_MODE = "playing_mode";
	private final static String START = "start";
	private final static String LENGTH = "length";
	private final static String TRANSPOSE = "transpose";
	private final static String DETUNE = "detune";
	private final static String ATTACK = "attack";
	private final static String DECAY = "decay";
	private final static String SUSTAIN = "sustain";
	private final static String RELEASE = "release";
	private final static String RANDOM_VELOCITY = "random_velocity";
	private final static String RANDOM_DETUNE = "random_detune";
	private final static String RANDOM_TRANSPOSE = "random_transpose";


	@Column(name = VOLUME)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float volume;

	@Column(name = OUTPUT_CHANNEL)
	@Parameter(min = 1, max = 16, step = 1, defaultValue = 1)
	private float outputChannel;

	@Column(name = CHOKE)
	@Parameter(min = 0, max = 16, step = 1, defaultValue = 0, aliases = {
			@Alias(name = "no_choke", value = CHOKE_NO_CHOKE) })
	private float choke;

	@Column(name = VOICES)
	@Parameter(min = 1, max = 4, step = 1, defaultValue = 1)
	private float voices;

	@Column(name = PLAYING_MODE)
	@Parameter(min = 0, max = 1, step = 1, defaultValue = 0, aliases = {
			@Alias(name = "trigger", value = PLAYING_MODE_TRIGGER), @Alias(name = "hold", value = PLAYING_MODE_HOLD) })
	private float playingMode;

	@Column(name = START)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float start;

	@Column(name = LENGTH)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float length;

	@Column(name = ATTACK)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float attack;

	@Column(name = DECAY)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float decay;

	@Column(name = SUSTAIN)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float sustain;

	@Column(name = RELEASE)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float release;

	@Column(name = TRANSPOSE)
	@Parameter(min = 0, max = 100, step = 0.01f, defaultValue = 0)
	private float transpose;

	@Column(name = DETUNE)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float detune;

	@Column(name = RANDOM_TRANSPOSE)
	@Parameter(min = -100, max = 100, step = 0.01f, defaultValue = 0)
	private float randomTranspose;

	@Column(name = RANDOM_DETUNE)
	@Parameter(min = -1, max = 1, step = 0.01f, defaultValue = 0)
	private float randomDetune;

	@Column(name = RANDOM_VELOCITY)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float randomVelocity;

	@Transient
	private boolean armed;

	public AudioSamplerTrack() {
		super();
		armed = false;
	}

	public AudioSamplerTrack(int bank, int number) {
		super(bank, number);
	}

	public float getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(float outputChannel) {
		this.outputChannel = outputChannel;
	}

	public float getChoke() {
		return choke;
	}

	public void setChoke(float choke) {
		this.choke = choke;
	}

	public float getVoices() {
		return voices;
	}

	public void setVoices(float voicesNumber) {
		this.voices = voicesNumber;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getTranspose() {
		return transpose;
	}

	public void setTranspose(float transpose) {
		this.transpose = transpose;
	}

	public float getDetune() {
		return detune;
	}

	public void setDetune(float detune) {
		this.detune = detune;
	}

	public float getAttack() {
		return attack;
	}

	public void setAttack(float attack) {
		this.attack = attack;
	}

	public float getDecay() {
		return decay;
	}

	public void setDecay(float decay) {
		this.decay = decay;
	}

	public float getSustain() {
		return sustain;
	}

	public void setSustain(float sustain) {
		this.sustain = sustain;
	}

	public float getRelease() {
		return release;
	}

	public void setRelease(float release) {
		this.release = release;
	}

	public float getRandomTranspose() {
		return randomTranspose;
	}

	public void setRandomTranspose(float randomTranspose) {
		this.randomTranspose = randomTranspose;
	}

	public float getRandomDetune() {
		return randomDetune;
	}

	public void setRandomDetune(float randomDetune) {
		this.randomDetune = randomDetune;
	}

	public float getRandomVelocity() {
		return randomVelocity;
	}

	public void setRandomVelocity(float randomVelocity) {
		this.randomVelocity = randomVelocity;
	}

	public float getStart() {
		return start;
	}

	public void setStart(float start) {
		this.start = start;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getPlayingMode() {
		return playingMode;
	}

	public void setPlayingMode(float playingMode) {
		this.playingMode = playingMode;
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

}
