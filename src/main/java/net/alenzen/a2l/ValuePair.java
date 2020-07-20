package net.alenzen.a2l;

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
}
