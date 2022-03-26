package net.alenzen.a2l;

import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.DataType;

public class RipAddrWXYZ45 extends A2LSerializer implements IA2LDimensionWriteable, IAsap2TreeElement {
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
	public IA2LWriteable getA2lWritable(String dimension) {
		return (A2LWriter writer) -> {
			writer.writelnSpaced("RIP_ADDR_" + dimension, Long.toString(position), dataType.name());
		};
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		RipAddrWXYZ45 that = (RipAddrWXYZ45) o;
		return position == that.position && dataType == that.dataType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, dataType);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
