package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ExtendedLimits extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	private double lowerLimit;
	private double upperLimit;

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("EXTENDED_LIMITS", Double.toString(lowerLimit), Double.toString(upperLimit));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ExtendedLimits that = (ExtendedLimits) o;
		return Double.compare(that.lowerLimit, lowerLimit) == 0 && Double.compare(that.upperLimit, upperLimit) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lowerLimit, upperLimit);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
