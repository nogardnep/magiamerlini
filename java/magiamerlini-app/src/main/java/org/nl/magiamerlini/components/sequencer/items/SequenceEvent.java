package org.nl.magiamerlini.components.sequencer.items;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.nl.magiamerlini.data.items.Item;

@Entity
@Table(name = "sequence_event")
public class SequenceEvent extends Item  implements Serializable{
	public final static int INACTIVE_STATE = 0;
	public final static int PLAY_STATE = 1;
	public final static int STOP_STATE = 1;

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "bank")
	private int bank;

	@Column(name = "state")
	private int state;
	
	public SequenceEvent() {
		super();
	}

	public SequenceEvent(int bank, int number, int state) {
		this();
		this.bank = bank;
		this.number = number;
		this.state = state;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", bank=" + bank + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return "sequence-event_" + bank + "-" + number;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
