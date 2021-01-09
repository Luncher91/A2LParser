package net.alenzen.a2l.enums;

import java.io.IOException;

import net.alenzen.a2l.A2LWriter;
import net.alenzen.a2l.IA2LWriteable;

public enum Deposit implements IA2LWriteable {
	ABSOLUTE, DIFFERENCE;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("DEPOSIT", this.name());
	}
}
