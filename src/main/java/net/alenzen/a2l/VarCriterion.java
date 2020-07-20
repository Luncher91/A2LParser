package net.alenzen.a2l;

import java.util.List;

public class VarCriterion {
	private String name;
	private String longIdentifier;

	// optional parameters
	private List<String> values;
	private String measurement;
	private String selectionCharacteristic;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongIdentifier() {
		return longIdentifier;
	}

	public void setLongIdentifier(String longIdentifier) {
		this.longIdentifier = longIdentifier;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getSelectionCharacteristic() {
		return selectionCharacteristic;
	}

	public void setSelectionCharacteristic(String selectionCharacteristic) {
		this.selectionCharacteristic = selectionCharacteristic;
	}
}
