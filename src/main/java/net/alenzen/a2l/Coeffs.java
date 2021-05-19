package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class Coeffs extends A2LSerializer implements IA2LWriteable {
	private double a, b, c, d, e, f;

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

	public double getC() {
		return c;
	}

	public void setC(double c) {
		this.c = c;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getE() {
		return e;
	}

	public void setE(double e) {
		this.e = e;
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("COEFFS", Double.toString(a), Double.toString(b), Double.toString(c), Double.toString(d),
				Double.toString(e), Double.toString(f));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Coeffs coeffs = (Coeffs) o;
		return Double.compare(coeffs.a, a) == 0 && Double.compare(coeffs.b, b) == 0 && Double.compare(coeffs.c, c) == 0
				&& Double.compare(coeffs.d, d) == 0 && Double.compare(coeffs.e, e) == 0
				&& Double.compare(coeffs.f, f) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(a, b, c, d, e, f);
	}
}
