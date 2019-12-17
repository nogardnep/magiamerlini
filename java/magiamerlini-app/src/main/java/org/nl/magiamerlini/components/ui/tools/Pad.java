package org.nl.magiamerlini.components.ui.tools;

public class Pad {
	public final static int SPEED_FOR_NO_BLINKING = 0;
	public final static int MIN_LIGHT_INTENSITY = 0;
	public final static int MAX_LIGHT_INTENSITY = 1;

	private int number;
	private float blinkingSpeed;
	private Color lightColor;
	private float lightIntensity;

	public Pad(int number) {
		
		this.number = number;
		this.blinkingSpeed = SPEED_FOR_NO_BLINKING;
		this.lightIntensity = 0;
		this.lightColor = Color.Grey;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getBlinkingSpeed() {
		return blinkingSpeed;
	}

	public void setBlinkingSpeed(float speed) {
		this.blinkingSpeed = speed;
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
		return getClass().getSimpleName() + " [" + "number=" + number + " color=" + lightColor + " intensity=" + lightIntensity + " speed=" + blinkingSpeed + "]";
	}

}
