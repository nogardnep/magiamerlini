package org.nl.magiamerlini.components.video.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Alias;
import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "video_sampler_track")
public class VideoSamplerTrack extends Item {
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
		this();
		this.bank = bank;
		this.number = number;
	}

	@Override
	public String toDisplay() {
		return "video-sampler-track_" + bank + "-" + number;
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

	public float getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(float repetitions) {
		this.repetitions = repetitions;
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

	public float getxSize() {
		return xSize;
	}

	public void setxSize(float xSize) {
		this.xSize = xSize;
	}

	public float getySize() {
		return ySize;
	}

	public void setySize(float ySize) {
		this.ySize = ySize;
	}

	public float getzSize() {
		return zSize;
	}

	public void setzSize(float zSize) {
		this.zSize = zSize;
	}

	public float getxPosition() {
		return xPosition;
	}

	public void setxPosition(float xPosition) {
		this.xPosition = xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public void setyPosition(float yPosition) {
		this.yPosition = yPosition;
	}

	public float getzPosition() {
		return zPosition;
	}

	public void setzPosition(float zPosition) {
		this.zPosition = zPosition;
	}

	public float getxRotation() {
		return xRotation;
	}

	public void setxRotation(float xRotation) {
		this.xRotation = xRotation;
	}

	public float getyRotation() {
		return yRotation;
	}

	public void setyRotation(float yRotation) {
		this.yRotation = yRotation;
	}

	public float getzRotation() {
		return zRotation;
	}

	public void setzRotation(float zRotation) {
		this.zRotation = zRotation;
	}

}
