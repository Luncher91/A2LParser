package net.alenzen.a2l.enums;

import java.io.IOException;

import net.alenzen.a2l.A2LWriter;
import net.alenzen.a2l.IA2LWriteable;

public enum ByteOrder implements IA2LWriteable {
	LITTLE_ENDIAN, BIG_ENDIAN, MSB_LAST, MSB_FIRST;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("BYTE_ORDER", this.name());
	}
}
