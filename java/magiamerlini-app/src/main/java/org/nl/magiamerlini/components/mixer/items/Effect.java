package org.nl.magiamerlini.components.mixer.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.nl.magiamerlini.data.tools.Item;

@Entity(name = "Effect")
@Table(name = "effect")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Effect extends Item {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private int id;

	@Column(name = "activated")
	private boolean activated;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mixer_track_id")
	MixerTrack mixerTrack;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public MixerTrack getMixerTrack() {
		return mixerTrack;
	}

	public void setMixerTrack(MixerTrack mixerTrack) {
		this.mixerTrack = mixerTrack;
	}

}
