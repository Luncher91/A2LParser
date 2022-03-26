package net.alenzen.a2l;

import java.util.List;
import java.util.Objects;


public class ValueTriple<T1, T2> extends A2LSerializer implements IAsap2TreeElement {
	private T1 inValMin;
	private T1 inValMax;
	private T2 outVal;

	public T1 getInValMin() {
		return inValMin;
	}

	public void setInValMin(T1 inValMin) {
		this.inValMin = inValMin;
	}

	public T1 getInValMax() {
		return inValMax;
	}

	public void setInValMax(T1 inValMax) {
		this.inValMax = inValMax;
	}

	public T2 getOutVal() {
		return outVal;
	}

	public void setOutVal(T2 outVal) {
		this.outVal = outVal;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ValueTriple<?, ?> that = (ValueTriple<?, ?>) o;
		return Objects.equals(inValMin, that.inValMin) && Objects.equals(inValMax, that.inValMax) && Objects
				.equals(outVal, that.outVal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(inValMin, inValMax, outVal);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
