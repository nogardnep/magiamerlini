package org.nl.magiamerlini.components.sequencer.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Item;

@Entity
@Table(name = "song")
public class Song extends Item  implements Serializable{
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@OneToMany(targetEntity = SongPart.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<SongPart> songParts;

	public Song() {
		super();
	}

	public Song(int number) {
		this();
		this.number = number;
		songParts = new ArrayList<SongPart>();
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return "song_" + number;
	}
	
	public void addSongPart(SongPart songPart) {
		songParts.add(songPart);
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

	public Collection<SongPart> getSongParts() {
		return songParts;
	}

	public void setSongParts(Collection<SongPart> songParts) {
		this.songParts = songParts;
	}

}
