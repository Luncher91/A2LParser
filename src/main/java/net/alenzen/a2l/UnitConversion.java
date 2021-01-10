package net.alenzen.a2l;

import java.io.IOException;

public class UnitConversion implements IA2LWriteable {
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
}
