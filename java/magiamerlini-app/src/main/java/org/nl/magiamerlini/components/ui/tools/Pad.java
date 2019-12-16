package org.nl.magiamerlini.components.ui.tools;

public class Pad {
	public final static int SPEED_FOR_NO_BLINKING = 0;

	private int number;
	private float speed;
	private Color color;
	private float intensity;

	public Pad(int number) {
		
		this.number = number;
		this.speed = SPEED_FOR_NO_BLINKING;
		this.intensity = 0;
		this.color = Color.Grey;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
	public String toString() {
		return getClass().getSimpleName() + " [" + "number=" + number + " color=" + color + " intensity=" + intensity + " speed=" + speed + "]";
	}

}
