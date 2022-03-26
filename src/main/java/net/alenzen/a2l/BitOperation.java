package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class BitOperation extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("BIT_OPERATION");
		writer.indent();
		
		if(leftShift != null) {
			writer.writelnSpaced("LEFT_SHIFT", leftShift.toString());
		}
		
		if(rightShift != null) {
			writer.writelnSpaced("RIGHT_SHIFT", rightShift.toString());
		}
		
		if(signExtend) {
			writer.writeln("SIGN_EXTEND");
		}
		
		writer.dedent();
		writer.writelnEnd("BIT_OPERATION");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BitOperation that = (BitOperation) o;
		return signExtend == that.signExtend && Objects.equals(leftShift, that.leftShift) && Objects
				.equals(rightShift, that.rightShift);
	}

	@Override
	public int hashCode() {
		return Objects.hash(leftShift, rightShift, signExtend);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
