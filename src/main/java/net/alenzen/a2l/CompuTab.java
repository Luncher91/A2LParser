package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

import net.alenzen.a2l.enums.ConversionType;

public class CompuTab implements IA2LWriteable {
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

	/**
	 * NumberOfValuePairs will actually not being used to write A2L.
	 * Instead the actual size of the ValuePairs list will be written.
	 * @param numberOfValuePairs
	 */
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

	/*
	 * Instead of writing out the NumberOfValuePairs attribute 
	 * the actual size of the array of valuePairs will be written.
	 */
	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("COMPU_TAB", name, A2LWriter.toA2LString(longIdentifier), conversionType.name(),
				Integer.toString(valuePairs.size()));
		writer.indent();

		for(ValuePair<Double, Double> p : valuePairs) {
			writer.writelnSpaced(Double.toString(p.getInVal()), Double.toString(p.getOutVal()));
		}

		if (defaultValue != null) {
			writer.writelnSpaced("DEFAULT_VALUE", A2LWriter.toA2LString(defaultValue));
		}

		if (defaultValueNumeric != null) {
			writer.writelnSpaced("DEFAULT_VALUE_NUMERIC", Double.toString(defaultValueNumeric));
		}

		writer.dedent();
		writer.writelnEnd("COMPU_TAB");
	}
}
