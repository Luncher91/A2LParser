package net.alenzen.a2l;

import java.io.IOException;

public class SystemConstant implements IA2LWriteable {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("SYSTEM_CONSTANT", A2LWriter.toA2LString(name), A2LWriter.toA2LString(value));
	}
}
