package org.nl.magiamerlini.components.ui.tools;

import org.nl.magiamerlini.utils.EnumUtils;

public class ButtonEvent {
	public final static float MIN_VELOCITY = 0;
	public final static float MAX_VELOCITY = 1;

	String name;
	InputSection section;
	float velocity;

	public ButtonEvent(String name, InputSection section, float velocity) {
		this.section = section;
		this.name = name;
		this.velocity = velocity;
	}

	public ButtonEvent(String name, InputSection section) {
		this(name, section, MAX_VELOCITY);
	}

	public InputSection getSection() {
		return section;
	}

	public String getName() {
		return name;
	}

	public float getVelocity() {
		return velocity;
	}

	public boolean equals(ButtonEvent button) {
		return button.hasName(name) && button.hasSection(section);
	}

	public boolean hasName(String name) {
		return this.name.equals(name);
	}

	public boolean hasName(ButtonName name) {
		return this.name.equals(EnumUtils.getCorrespondingString(name.name()));
	}

	public boolean hasSection(InputSection section) {
		return this.section == section;
	}
}
