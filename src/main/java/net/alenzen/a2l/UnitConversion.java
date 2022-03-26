package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class UnitConversion extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	private double gradient;
	private double offset;

	public double getGradient() {
		return gradient;
	}

	public void setGradient(double gradient) {
		this.gradient = gradient;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("UNIT_CONVERSION", Double.toString(gradient), Double.toString(offset));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UnitConversion that = (UnitConversion) o;
		return Double.compare(that.gradient, gradient) == 0 && Double.compare(that.offset, offset) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gradient, offset);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
