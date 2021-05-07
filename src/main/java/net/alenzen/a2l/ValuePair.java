package net.alenzen.a2l;

import java.util.Objects;


public class ValuePair<T, U> {
	private T inVal;
	private U outVal;

	public T getInVal() {
		return inVal;
	}

	public void setInVal(T inVal) {
		this.inVal = inVal;
	}

	public U getOutVal() {
		return outVal;
	}

	public void setOutVal(U outVal) {
		this.outVal = outVal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ValuePair<?, ?> valuePair = (ValuePair<?, ?>) o;
		return Objects.equals(inVal, valuePair.inVal) && Objects.equals(outVal, valuePair.outVal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(inVal, outVal);
	}
}
