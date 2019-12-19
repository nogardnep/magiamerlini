package org.nl.magiamerlini.components.sampler.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.nl.magiamerlini.data.items.Item;

@Entity
@Table(name = "sampler_track")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class SamplerTrack extends Item {

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

	@Transient
	private boolean playing;

	public SamplerTrack() {
		super();
		playing = false;
	}

	public SamplerTrack(int bank, int number) {
		this();
		this.number = number;
		this.bank = bank;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", bank=" + bank + ", number=" + number + ", filePath="
				+ getFilePath() + "]";
	}

	@Override
	public String toDisplay() {
		return this.getClass().getSimpleName() + "_" + getBank() + "-" + number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

}
