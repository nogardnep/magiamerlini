package org.nl.magiamerlini.components.mixer.items;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "audio_effect")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AudioEffect extends Effect {

}
