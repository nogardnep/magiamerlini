package org.nl.magiamerlini.data.tools;

public class Parameter {
	private String key;
	private float value;
	private float step;
	private float min;
	private float max;
	private String alias; // TODO

	public Parameter(String key, float value, float min, float max, float step) {
		this.key = key;
		this.value = value;
		this.step = step;
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return key + "=" + value;
	}

	public String getName() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getStep() {
		return step;
	}

	public void setStep(float step) {
		this.step = step;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDisplayedValue() {
		// TODO check for aliases

		return String.valueOf(value);
	}

	public void modifyValue(float factor) {
		value += (step * factor);

		if (value > max) {
			value = max;
		}

		if (value < min) {
			value = min;
		}
	}

}
