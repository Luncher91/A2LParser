package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class FixAxisPar implements IA2LWriteable {
	private long offset;
	private long shift;
	private long numberapo;

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	public long getShift() {
		return shift;
	}

	public void setShift(long shift) {
		this.shift = shift;
	}

	public long getNumberapo() {
		return numberapo;
	}

	public void setNumberapo(long numberapo) {
		this.numberapo = numberapo;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("FIX_AXIS_PAR", Long.toString(offset), Long.toString(shift), Long.toString(numberapo));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FixAxisPar that = (FixAxisPar) o;
		return offset == that.offset && shift == that.shift && numberapo == that.numberapo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(offset, shift, numberapo);
	}
}
