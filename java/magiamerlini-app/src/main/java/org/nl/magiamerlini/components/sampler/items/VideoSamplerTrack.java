package org.nl.magiamerlini.components.sampler.items;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Alias;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "video_sampler_track")
public class VideoSamplerTrack extends SamplerTrack implements Serializable {
	public final static int TYPE_FLAT = 0;
	public final static int TYPE_CUBE = 1;
	public final static int TYPE_SPHERE = 2;

	private final static String START = "start";
	private final static String LENGTH = "length";
	private final static String FADEIN = "fadein";
	private final static String FADEOUT = "fadeout";
	private final static String X_SIZE = "x_size";
	private final static String Y_SIZE = "y_size";
	private final static String Z_SIZE = "z_size";
	private final static String X_POSITION = "x_position";
	private final static String Y_POSITION = "y_position";
	private final static String Z_POSITION = "z_position";
	private final static String X_ROTATION = "x_rotation";
	private final static String Y_ROTATION = "y_rotation";
	private final static String Z_ROTATION = "z_rotation";
	private final static String REPETITIONS = "repetitions";
	private final static String OPACITY = "opacity";
	private final static String TYPE = "type";

	@Column(name = START)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float start;

	@Column(name = LENGTH)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float length;

	@Column(name = REPETITIONS)
	@Parameter(min = 0, max = 100, step = 1, defaultValue = 0)
	private float repetitions;

	@Column(name = FADEIN)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float fadein;

	@Column(name = FADEOUT)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float fadeout;

	@Column(name = TYPE)
	@Parameter(min = 0, max = 1, step = 1, defaultValue = 0, aliases = { @Alias(name = "flat", value = TYPE_FLAT),
			@Alias(name = "cube", value = TYPE_CUBE), @Alias(name = "sphere", value = TYPE_SPHERE) })
	private float type;

	@Column(name = OPACITY)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float opacity;

	@Column(name = X_SIZE)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float xSize;

	@Column(name = Y_SIZE)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float ySize;

	@Column(name = Z_SIZE)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 1)
	private float zSize;

	@Column(name = X_POSITION)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float xPosition;

	@Column(name = Y_POSITION)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float yPosition;

	@Column(name = Z_POSITION)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float zPosition;

	@Column(name = X_ROTATION)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float xRotation;

	@Column(name = Y_ROTATION)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float yRotation;

	@Column(name = Z_ROTATION)
	@Parameter(min = 0, max = 1, step = 0.01f, defaultValue = 0)
	private float zRotation;

	public VideoSamplerTrack() {
		super();
	}

	public VideoSamplerTrack(int bank, int number) {
		super(bank, number);
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

	public float getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(float repetitions) {
		this.repetitions = repetitions;
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

	public float getType() {
		return type;
	}

	public void setType(float type) {
		this.type = type;
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}

	public float getXSize() {
		return xSize;
	}

	public void setXSize(float xSize) {
		this.xSize = xSize;
	}

	public float getYSize() {
		return ySize;
	}

	public void setYSize(float ySize) {
		this.ySize = ySize;
	}

	public float getZSize() {
		return zSize;
	}

	public void setZSize(float zSize) {
		this.zSize = zSize;
	}

	public float getXPosition() {
		return xPosition;
	}

	public void setXPosition(float xPosition) {
		this.xPosition = xPosition;
	}

	public float getYPosition() {
		return yPosition;
	}

	public void setYPosition(float yPosition) {
		this.yPosition = yPosition;
	}

	public float getZPosition() {
		return zPosition;
	}

	public void setZPosition(float zPosition) {
		this.zPosition = zPosition;
	}

	public float getXRotation() {
		return xRotation;
	}

	public void setXRotation(float xRotation) {
		this.xRotation = xRotation;
	}

	public float getYRotation() {
		return yRotation;
	}

	public void setYRotation(float yRotation) {
		this.yRotation = yRotation;
	}

	public float getZRotation() {
		return zRotation;
	}

	public void setZRotation(float zRotation) {
		this.zRotation = zRotation;
	}

}
