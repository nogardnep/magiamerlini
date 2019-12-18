package org.nl.magiamerlini.components.sequencer.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Item;
import org.nl.magiamerlini.data.tools.Parameter;

@Entity
@Table(name = "song_part")
public class SongPart extends Item {
	public static final String SEQUENCE_PARAMETER = "sequence";
	public static final String REPETITION_PARAMETER = "repetitions";

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = SEQUENCE_PARAMETER)
	@Parameter(min = 1, max = 16, step = 1, defaultValue = 1)
	private float sequence;

	@Column(name = REPETITION_PARAMETER)
	@Parameter(min = 1, max = 100, step = 1, defaultValue = 1)
	private float repetitions;

	public SongPart() {
		super();
	}

	public SongPart(int number) {
		this();

		this.number = number;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return "sequence_" + number;
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

	public float getSequence() {
		return sequence;
	}

	public void setSequence(float sequence) {
		this.sequence = sequence;
	}

	public float getRepetitions() {
		return repetitions;
	}

	public void setRepetitions(float repetitions) {
		this.repetitions = repetitions;
	}

}
