package net.alenzen.a2l;

public class BitOperation {
	// optional parameters
	private Long leftShift;
	private Long rightShift;
	private boolean signExtend;

	public Long getLeftShift() {
		return leftShift;
	}

	public void setLeftShift(Long leftShift) {
		this.leftShift = leftShift;
	}

	public Long getRightShift() {
		return rightShift;
	}

	public void setRightShift(Long rightShift) {
		this.rightShift = rightShift;
	}

	public boolean isSignExtend() {
		return signExtend;
	}

	public void setSignExtend(boolean signExtend) {
		this.signExtend = signExtend;
	}
}
