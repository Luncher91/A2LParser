package net.alenzen.a2l;

import java.io.IOException;

import net.alenzen.a2l.enums.DataType;

public class Identification implements IA2LWriteable {
	private long position;
	private DataType dataType;

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("IDENTIFICATION", Long.toString(position), dataType.name());
	}
}
