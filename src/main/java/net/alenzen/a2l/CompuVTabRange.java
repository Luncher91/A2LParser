package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CompuVTabRange extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	/**
	 * NumberOfValueTriples will actually not being used to write A2L.
	 * Instead the actual size of the ValueTriples list will be written.
	 * @param numberOfValuePairs
	 */
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

	/*
	 * Instead of writing out the NumberOfValueTriples attribute 
	 * the actual size of the array of valueTriples will be written.
	 */
	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("COMPU_VTAB_RANGE", name, A2LWriter.toA2LString(longIdentifier),
				Integer.toString(valueTriples.size()));
		writer.indent();

		for (ValueTriple<Double, String> p : valueTriples) {
			writer.writelnSpaced(Double.toString(p.getInValMin()), Double.toString(p.getInValMax()),
					A2LWriter.toA2LString(p.getOutVal()));
		}

		if (defaultValue != null) {
			writer.writelnSpaced("DEFAULT_VALUE", A2LWriter.toA2LString(defaultValue));
		}

		writer.dedent();
		writer.writelnEnd("COMPU_VTAB_RANGE");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CompuVTabRange that = (CompuVTabRange) o;
		return numberOfValueTriples == that.numberOfValueTriples && Objects.equals(name, that.name) && Objects
				.equals(longIdentifier, that.longIdentifier) && Objects.equals(valueTriples, that.valueTriples)
				&& Objects.equals(defaultValue, that.defaultValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, numberOfValueTriples, valueTriples, defaultValue);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.valueTriples);
		return subNodes;
	}
}
