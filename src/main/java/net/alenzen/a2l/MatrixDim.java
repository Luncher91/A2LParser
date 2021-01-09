package net.alenzen.a2l;

import java.io.IOException;

public class MatrixDim implements IA2LWriteable {
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
}
