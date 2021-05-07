package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.ConversionType;

public class CompuVTab implements IA2LWriteable {
	private String name;
	private String longIdentifier;
	private ConversionType conversionType;
	private long numberOfValuePairs;

	// optional parameters
	private List<ValuePair<Double, String>> valuePairs;
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

	public List<ValuePair<Double, String>> getValuePairs() {
		return valuePairs;
	}

	public void setValuePairs(List<ValuePair<Double, String>> valuePairs) {
		this.valuePairs = valuePairs;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/*
	 * Instead of writing out the NumberOfValuePairs attribute 
	 * the actual size of the array of valuePairs will be written.
	 */
	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("COMPU_VTAB", name, A2LWriter.toA2LString(longIdentifier), conversionType.name(),
				Integer.toString(valuePairs.size()));
		writer.indent();

		for(ValuePair<Double, String> p : valuePairs) {
			writer.writelnSpaced(Double.toString(p.getInVal()), A2LWriter.toA2LString(p.getOutVal()));
		}

		if (defaultValue != null) {
			writer.writelnSpaced("DEFAULT_VALUE", A2LWriter.toA2LString(defaultValue));
		}

		writer.dedent();
		writer.writelnEnd("COMPU_VTAB");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CompuVTab compuVTab = (CompuVTab) o;
		return numberOfValuePairs == compuVTab.numberOfValuePairs && Objects.equals(name, compuVTab.name) && Objects
				.equals(longIdentifier, compuVTab.longIdentifier) && conversionType == compuVTab.conversionType
				&& Objects.equals(valuePairs, compuVTab.valuePairs) && Objects
				.equals(defaultValue, compuVTab.defaultValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, conversionType, numberOfValuePairs, valuePairs, defaultValue);
	}
}
