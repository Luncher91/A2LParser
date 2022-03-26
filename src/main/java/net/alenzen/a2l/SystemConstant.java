package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class SystemConstant extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SystemConstant that = (SystemConstant) o;
		return Objects.equals(name, that.name) && Objects.equals(value, that.value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, value);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
