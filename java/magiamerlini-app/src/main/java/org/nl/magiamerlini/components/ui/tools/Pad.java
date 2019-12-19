package org.nl.magiamerlini.components.ui.tools;

public class Pad {
	public final static float BLINKING_SPEED_NO_BLINKING = 0;
	public static final float BLINKING_SPEED_NORMAL = 200;
	public final static float MIN_LIGHT_INTENSITY = 0;
	public final static float MAX_LIGHT_INTENSITY = 1;

	private int number;
	private float lightBlinkingSpeed;
	private Color lightColor;
	private float lightIntensity;

	public Pad(int number) {
		this.number = number;
		this.lightBlinkingSpeed = BLINKING_SPEED_NO_BLINKING;
		this.lightIntensity = 0;
		this.lightColor = Color.Grey;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getLightBlinkingSpeed() {
		return lightBlinkingSpeed;
	}

	public void setLightBlinkingSpeed(float speed) {
		this.lightBlinkingSpeed = speed;
	}

	public Color getLightColor() {
		return lightColor;
	}

	public void setLightColor(Color color) {
		this.lightColor = color;
	}

	public float getLightIntensity() {
		return lightIntensity;
	}

	public void setLightIntensity(float intensity) {
		this.lightIntensity = intensity;
	}
	
	public String toString() {
		return getClass().getSimpleName() + " [" + "number=" + number + " color=" + lightColor + " intensity=" + lightIntensity + " speed=" + lightBlinkingSpeed + "]";
	}

}
