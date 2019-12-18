package org.nl.magiamerlini.data.tools;
public class ParameterSnapshot {
	private String name;
	private float value;
	private float step;
	private float min;
	private float max;
	private String alias; // TODO

	public ParameterSnapshot(String name, float value, float min, float max, float step) {
		this.name = name;
		this.value = value;
		this.step = step;
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return name + "=" + value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
