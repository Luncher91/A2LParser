package net.alenzen.a2l;

import java.util.List;

import net.alenzen.a2l.enums.ConversionType;

public class CompuTab {
	private String name;
	private String longIdentifier;
	private ConversionType conversionType;
	private long numberOfValuePairs;

	// optional parameters
	private List<ValuePair<Double, Double>> valuePairs;
	private String defaultValue;
	private Double defaultValueNumeric;

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

	public ConversionType getConversionType() {
		return conversionType;
	}

	public void setConversionType(ConversionType conversionType) {
		this.conversionType = conversionType;
	}

	public long getNumberOfValuePairs() {
		return numberOfValuePairs;
	}

	public void setNumberOfValuePairs(long numberOfValuePairs) {
		this.numberOfValuePairs = numberOfValuePairs;
	}

	public List<ValuePair<Double, Double>> getValuePairs() {
		return valuePairs;
	}

	public void setValuePairs(List<ValuePair<Double, Double>> valuePairs) {
		this.valuePairs = valuePairs;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Double getDefaultValueNumeric() {
		return defaultValueNumeric;
	}

	public void setDefaultValueNumeric(Double defaultValueNumeric) {
		this.defaultValueNumeric = defaultValueNumeric;
	}
}
