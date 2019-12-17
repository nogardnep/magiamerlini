package org.nl.magiamerlini.data.items;

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
@Table(name = "sampler_track")
public class SamplerTrack implements Item {
	public final static int NO_CHOKE = -1;
	public final static int TRIGGER_PLAYING_MODE = 0;
	public final static int HOLD_PLAYING_MODE = 1;

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

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "bank")
	private int bank;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = OUTPUT_CHANNEL)
	private int outputChannel;

	@Column(name = VOLUME)
	private float volume;

	@Column(name = CHOKE)
	private int choke;

	@Column(name = VOICES)
	private int voices;

	@Column(name = TRANSPOSE)
	private float transpose;

	@Column(name = DETUNE)
	private float detune;

	@Column(name = ATTACK)
	private float attack;

	@Column(name = DECAY)
	private float decay;

	@Column(name = SUSTAIN)
	private float sustain;

	@Column(name = RELEASE)
	private float release;

	@Column(name = RANDOM_TRANSPOSE)
	private float randomTranspose;

	@Column(name = RANDOM_DETUNE)
	private float randomDetune;

	@Column(name = RANDOM_VELOCITY)
	private float randomVelocity;

	@Column(name = START)
	private float start;

	@Column(name = LENGTH)
	private float length;

	@Column(name = PLAYING_MODE)
	private int playingMode;

	public SamplerTrack() {
	}

	public SamplerTrack(int bank, int number) {
		this.number = number;
		this.bank = bank;

		volume = 1;
		outputChannel = 0;
		choke = NO_CHOKE;
		voices = 1;
		start = 0;
		length = 1;
		attack = 0;
		decay = 0;
		sustain = 1;
		release = 0;
		transpose = 0;
		detune = 0;
		randomTranspose = 0;
		randomDetune = 0;
		randomVelocity = 0;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", bank=" + bank + ", number=" + number + ", filePath="
				+ filePath + "]";
	}

	@Override
	public List<Parameter> getParameters() {
		List<Parameter> parameters = new ArrayList<Parameter>();

		parameters.add(new Parameter(VOLUME, volume, 0, 1, 0.01f));
		parameters.add(
				new Parameter(OUTPUT_CHANNEL, outputChannel, 0, (Configuration.AudioSamplerOutputs.getValue() - 1), 1));
		parameters.add(new Parameter(CHOKE, choke, -1, (Configuration.Tracks.getValue() - 1), 1));
		parameters.add(new Parameter(VOICES, voices, 1, 4, 1));
		parameters.add(new Parameter(PLAYING_MODE, playingMode, TRIGGER_PLAYING_MODE, 1, 1));
		parameters.add(new Parameter(START, start, 0, 1, 0.01f));
		parameters.add(new Parameter(LENGTH, length, 0, 1, 0.01f));
		parameters.add(new Parameter(ATTACK, attack, 0, 5000, 1));
		parameters.add(new Parameter(DECAY, decay, 0, 5000, 1));
		parameters.add(new Parameter(SUSTAIN, sustain, 0, 1, 0.01f));
		parameters.add(new Parameter(RELEASE, release, 0, 5000, 1));
		parameters.add(new Parameter(TRANSPOSE, transpose, -100, 100, 1));
		parameters.add(new Parameter(DETUNE, detune, -1, 1, 0.01f));
		parameters.add(new Parameter(RANDOM_TRANSPOSE, randomTranspose, -100, 100, 1));
		parameters.add(new Parameter(RANDOM_DETUNE, randomDetune, -1, 1, 0.01f));
		parameters.add(new Parameter(RANDOM_VELOCITY, randomVelocity, 0, 1, 0.01f));

		return parameters;
	}

	@Override
	public void applyParameter(Parameter parameter) {
		switch (parameter.getName()) {
		case OUTPUT_CHANNEL:
			setOutputChannel((int) parameter.getValue());
			break;
		case CHOKE:
			setChoke((int) parameter.getValue());
			break;
		case VOICES:
			setVoices((int) parameter.getValue());
			break;
		case VOLUME:
			setVolume(parameter.getValue());
			break;
		case PLAYING_MODE:
			setPlayingMode((int) parameter.getValue());
			break;
		case START:
			setStart(parameter.getValue());
			break;
		case LENGTH:
			setLength(parameter.getValue());
			break;
		case TRANSPOSE:
			setTranspose(parameter.getValue());
			break;
		case DETUNE:
			setDetune(parameter.getValue());
			break;
		case ATTACK:
			setAttack(parameter.getValue());
			break;
		case DECAY:
			setDecay(parameter.getValue());
			break;
		case SUSTAIN:
			setSustain(parameter.getValue());
			break;
		case RELEASE:
			setRelease(parameter.getValue());
			break;
		case RANDOM_TRANSPOSE:
			setRandomTranspose(parameter.getValue());
			break;
		case RANDOM_DETUNE:
			setRandomDetune(parameter.getValue());
			break;
		case RANDOM_VELOCITY:
			setRandomVelocity(parameter.getValue());
			break;
		default:
			System.err.println("can't find parameter: \"" + parameter.getName() + "\"");
		}
	}

	@Override
	public String toDisplay() {
		return "sampler-track-" + bank + "-" + number;
	}

	public int getOutputChannel() {
		return outputChannel;
	}

	public void setOutputChannel(int outputChannel) {
		this.outputChannel = outputChannel;
	}

	public int getChoke() {
		return choke;
	}

	public void setChoke(int choke) {
		this.choke = choke;
	}

	public int getVoices() {
		return voices;
	}

	public void setVoices(int voicesNumber) {
		this.voices = voicesNumber;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getBank() {
		return bank;
	}

	public void setBank(int bank) {
		this.bank = bank;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPlayingMode() {
		return playingMode;
	}

	public void setPlayingMode(int playingMode) {
		this.playingMode = playingMode;
	}
	
}
