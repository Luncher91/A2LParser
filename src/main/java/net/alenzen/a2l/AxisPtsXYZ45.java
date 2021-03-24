package net.alenzen.a2l;

import net.alenzen.a2l.enums.AddrType;
import net.alenzen.a2l.enums.DataType;
import net.alenzen.a2l.enums.IndexOrder;

public class AxisPtsXYZ45 implements IA2LDimensionWriteable {
	private long position;
	private DataType datatype;
	private IndexOrder indexorder;
	private AddrType addressing;

	public long getPosition() {
		return position;
	}

	public void setPosition(long position) {
		this.position = position;
	}

	public DataType getDatatype() {
		return datatype;
	}

	public void setDatatype(DataType datatype) {
		this.datatype = datatype;
	}

	public IndexOrder getIndexorder() {
		return indexorder;
	}

	public void setIndexorder(IndexOrder indexorder) {
		this.indexorder = indexorder;
	}

	public AddrType getAddressing() {
		return addressing;
	}

	public void setAddressing(AddrType addressing) {
		this.addressing = addressing;
	}

	@Override
	public IA2LWriteable getA2lWritable(String dimension) {
		return (A2LWriter writer) -> {
			writer.writelnSpaced("AXIS_PTS_" + dimension, Long.toString(position), datatype.name(), indexorder.name(),
					addressing.name());
		};
	}
}
