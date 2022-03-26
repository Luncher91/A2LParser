package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class CoeffsLinear extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CoeffsLinear that = (CoeffsLinear) o;
		return Double.compare(that.a, a) == 0 && Double.compare(that.b, b) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
