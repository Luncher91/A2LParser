package net.alenzen.a2l;

import java.util.Objects;

import net.alenzen.a2l.enums.DataType;

public class SrcAddrXYZ45 extends A2LSerializer implements IA2LDimensionWriteable {
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
			writer.writelnSpaced("SRC_ADDR_" + dimension, Long.toString(position), dataType.name());
		};
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SrcAddrXYZ45 that = (SrcAddrXYZ45) o;
		return position == that.position && dataType == that.dataType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, dataType);
	}
}
