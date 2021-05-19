package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class VarCriterion extends A2LSerializer implements IA2LWriteable {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("VAR_CRITERION", name, A2LWriter.toA2LString(longIdentifier));
		writer.indent();

		if (values != null) {
			for (String s : values) {
				if (s != null) {
					writer.writeln(s);
				}
			}
		}
		
		if(measurement != null) {
			writer.writelnSpaced("VAR_MEASUREMENT", measurement);
		}
		
		if(selectionCharacteristic != null) {
			writer.writelnSpaced("VAR_SELECTION_CHARACTERISTIC", selectionCharacteristic);
		}

		writer.dedent();
		writer.writelnEnd("VAR_CRITERION");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VarCriterion that = (VarCriterion) o;
		return Objects.equals(name, that.name) && Objects.equals(longIdentifier, that.longIdentifier) && Objects
				.equals(values, that.values) && Objects.equals(measurement, that.measurement) && Objects
				.equals(selectionCharacteristic, that.selectionCharacteristic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, values, measurement, selectionCharacteristic);
	}
}
