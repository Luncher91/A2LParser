package net.alenzen.a2l;

import java.io.IOException;

import net.alenzen.a2l.enums.DataSize;

public class Reserved implements IA2LWriteable {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("RESERVED", Long.toString(position), dataSize.name());
	}
}
