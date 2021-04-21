package net.alenzen.a2l;

import java.io.IOException;

import net.alenzen.a2l.enums.AddrType;
import net.alenzen.a2l.enums.DataType;

public class FncValues implements IA2LWriteable {
	private long position;
	private DataType dataType;
	private IndexMode indexMode;
	private AddrType addressType;

	public enum IndexMode {
		ALTERNATE_CURVES, ALTERNATE_WITH_X, ALTERNATE_WITH_Y, COLUMN_DIR, ROW_DIR
	}

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

	public IndexMode getIndexMode() {
		return indexMode;
	}

	public void setIndexMode(IndexMode indexMode) {
		this.indexMode = indexMode;
	}

	public AddrType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddrType addressType) {
		this.addressType = addressType;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("FNC_VALUES", Long.toString(position), dataType.name(), indexMode.name(),
				addressType.name());
	}
}
