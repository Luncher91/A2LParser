package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class MatrixDim extends A2LSerializer implements IA2LWriteable {
	private long xDim;
	private long yDim;
	private long zDim;

	public long getxDim() {
		return xDim;
	}

	public void setxDim(long xDim) {
		this.xDim = xDim;
	}

	public long getyDim() {
		return yDim;
	}

	public void setyDim(long yDim) {
		this.yDim = yDim;
	}

	public long getzDim() {
		return zDim;
	}

	public void setzDim(long zDim) {
		this.zDim = zDim;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("MATRIX_DIM", Long.toString(xDim), Long.toString(yDim), Long.toString(zDim));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MatrixDim matrixDim = (MatrixDim) o;
		return xDim == matrixDim.xDim && yDim == matrixDim.yDim && zDim == matrixDim.zDim;
	}

	@Override
	public int hashCode() {
		return Objects.hash(xDim, yDim, zDim);
	}
}
