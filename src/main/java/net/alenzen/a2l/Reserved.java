package net.alenzen.a2l;

import net.alenzen.a2l.enums.DataSize;

public class Reserved {
	private long position;
	private DataSize dataSize;

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public DataSize getDataSize() {
		return dataSize;
	}

	public void setDataSize(DataSize dataSize) {
		this.dataSize = dataSize;
	}
}
