package net.alenzen.a2l;

public class ValueTriple<T1, T2> {
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
}
