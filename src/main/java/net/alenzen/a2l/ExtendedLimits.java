package net.alenzen.a2l;

import java.io.IOException;

public class ExtendedLimits implements IA2LWriteable {
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
}
