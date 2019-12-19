package org.nl.magiamerlini.components.mixer.items.effects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.nl.magiamerlini.data.items.Item;

@Entity
@Table(name = "effect")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Effect extends Item {
	
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;
	
	@Column(name = "activated")
	private boolean activated;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActivated() {
		return activated;
	}

	public void setActivated(boolean  activated) {
		this.activated = activated;
	}
	
}
