package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.DataSize;

public class Reserved extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Reserved reserved = (Reserved) o;
		return position == reserved.position && dataSize == reserved.dataSize;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, dataSize);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
