package org.nl.magiamerlini.data.items;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

public class VideoTrack implements Item {
	public final static int NO_BLENDING = 0;

	private final static String START = "start";
	private final static String LENGTH = "length";
	private final static String FADEIN = "fadein";
	private final static String FADEOUT = "fadeout";
	private final static String ALPHA = "alpha";
	private final static String RED = "red";
	private final static String GREEN = "green";
	private final static String BLUE = "blue";
	private final static String BLENDING_MODE = "blending_mode";
	private final static String SIZE = "size";
	private final static String X_POSITION = "x position";
	private final static String Y_POSITION = "y position";
	private final static String Z_POSITION = "z position";

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

	@Column(name = ALPHA)
	private float alpha;

	@Column(name = FADEIN)
	private float fadein;

	@Column(name = FADEOUT)
	private float fadeout;

	@Column(name = START)
	private float start;

	@Column(name = LENGTH)
	private float length;

	@Column(name = RED)
	private float red;

	@Column(name = GREEN)
	private float green;

	@Column(name = BLUE)
	private float blue;

	@Column(name = BLENDING_MODE)
	private float blendingMode;

	@Column(name = SIZE)
	private float size;

	@Column(name = X_POSITION)
	private float xPosition;

	@Column(name = Y_POSITION)
	private float yPosition;

	@Column(name = Z_POSITION)
	private float zPosition;

	public VideoTrack() {
	}

	public VideoTrack(int bank, int number) {
		this.bank = bank;
		this.number = number;

		size = 1;
		xPosition = 0;
		yPosition = 0;
		zPosition = 0;
		start = 0;
		length = 1;
		fadein = 0;
		fadeout = 0;
		alpha = 1;
		red = 1;
		green = 1;
		blue = 1;
		blendingMode = NO_BLENDING;
	}

	@Override
	public List<Parameter> getParameters() {
		List<Parameter> parameters = new ArrayList<Parameter>();

		parameters.add(new Parameter(SIZE, size, 0, 1, 0.01f));
		parameters.add(new Parameter(X_POSITION, xPosition, -1, 1, 0.01f));
		parameters.add(new Parameter(Y_POSITION, yPosition, -1, 1, 0.01f));
		parameters.add(new Parameter(Z_POSITION, zPosition, -1, 1, 0.01f));
		parameters.add(new Parameter(START, start, 0, 1, 0.01f));
		parameters.add(new Parameter(LENGTH, length, 0, 1, 0.01f));
		parameters.add(new Parameter(FADEIN, fadein, 0, 1, 0.01f));
		parameters.add(new Parameter(FADEOUT, fadeout, 0, 1, 0.01f));
		parameters.add(new Parameter(ALPHA, alpha, 0, 1, 0.01f));
		parameters.add(new Parameter(RED, red, 0, 1, 0.01f));
		parameters.add(new Parameter(GREEN, green, 0, 1, 0.01f));
		parameters.add(new Parameter(BLUE, blue, 0, 1, 0.01f));
		parameters.add(new Parameter(BLENDING_MODE, blendingMode, 0, 1, 0.01f));

		return parameters;
	}

	@Override
	public void applyParameter(Parameter parameter) {
		switch (parameter.getName()) {
		case SIZE:
			setSize(parameter.getValue());
			break;
		case X_POSITION:
			setXPosition(parameter.getValue());
			break;
		case Y_POSITION:
			setYPosition(parameter.getValue());
			break;
		case Z_POSITION:
			setZPosition(parameter.getValue());
			break;
		case START:
			setStart(parameter.getValue());
			break;
		case LENGTH:
			setLength(parameter.getValue());
			break;
		case FADEIN:
			setFadein(parameter.getValue());
			break;
		case FADEOUT:
			setFadeout(parameter.getValue());
			break;
		case ALPHA:
			setAlpha(parameter.getValue());
			break;
		case RED:
			setRed(parameter.getValue());
			break;
		case GREEN:
			setGreen(parameter.getValue());
			break;
		case BLUE:
			setBlue(parameter.getValue());
			break;
		case BLENDING_MODE:
			setBlendingMode(parameter.getValue());
			break;
		default:
			System.err.println("can't find parameter: \"" + parameter.getName() + "\"");
		}
	}

	@Override
	public String toDisplay() {
		return "video-track-" + bank + "-" + number;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

	public float getFadein() {
		return fadein;
	}

	public void setFadein(float fadein) {
		this.fadein = fadein;
	}

	public float getFadeout() {
		return fadeout;
	}

	public void setFadeout(float fadeout) {
		this.fadeout = fadeout;
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getXPosition() {
		return xPosition;
	}

	public void setXPosition(float x) {
		this.xPosition = x;
	}

	public float getYPosition() {
		return yPosition;
	}

	public void setYPosition(float y) {
		this.yPosition = y;
	}

	public float getZPosition() {
		return zPosition;
	}

	public void setZPosition(float z) {
		this.zPosition = z;
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

	public float getBlendingMode() {
		return blendingMode;
	}

	public void setBlendingMode(float blendingMode) {
		this.blendingMode = blendingMode;
	}

}
