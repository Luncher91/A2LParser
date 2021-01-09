package net.alenzen.a2l;

import java.io.IOException;

public class CoeffsLinear implements IA2LWriteable {
	private double a;
	private double b;

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("COEFFS_LINEAR", Double.toString(a), Double.toString(b));
	}
}
