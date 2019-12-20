package org.nl.magiamerlini.components.mixer.items;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.nl.magiamerlini.data.items.Item;

@Entity(name = "MixerTrack")
@Table(name = "mixer_track")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MixerTrack extends Item {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "number")
	private int number;

	@Column(name = "muted")
	private boolean muted;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Effect> effects;

	public MixerTrack() {
		super();
		muted = false;
		effects = new ArrayList<Effect>();
	}

	public MixerTrack(int number) {
		this();
		this.number = number;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [id=" + id + ", number=" + number + "]";
	}

	@Override
	public String toDisplay() {
		return this.getClass().getSimpleName() + "_" + number;
	}

	public Effect getEffectOfType (Class<?> type) {
		Effect found = null;
		
		for (Effect effect : effects) {
			if (type.isInstance(effect)) {
				found = effect;
			}
		}
		
		 return found;
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

	public boolean isMuted() {
		return muted;
	}

	public void setMuted(boolean muted) {
		this.muted = muted;
	}

	public List<Effect> getEffects() {
		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}

}
