package net.alenzen.a2l;

import net.alenzen.a2l.enums.DataType;

public class OffsetXYZ45 implements IA2LDimensionWriteable {
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
			writer.writelnSpaced("OFFSET_" + dimension, Long.toString(position), dataType.name());
		};
	}
}
