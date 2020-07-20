package net.alenzen.a2l;

import java.util.List;

public class CompuVTabRange {
	private String name;
	private String longIdentifier;
	private long numberOfValueTriples;

	private List<ValueTriple<Double, String>> valueTriples;
	private String defaultValue;

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

	public long getNumberOfValueTriples() {
		return numberOfValueTriples;
	}

	public void setNumberOfValueTriples(long numberOfValueTriples) {
		this.numberOfValueTriples = numberOfValueTriples;
	}

	public List<ValueTriple<Double, String>> getValueTriples() {
		return valueTriples;
	}

	public void setValueTriples(List<ValueTriple<Double, String>> valueTriples) {
		this.valueTriples = valueTriples;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}
