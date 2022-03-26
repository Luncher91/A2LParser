package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.AddrType;
import net.alenzen.a2l.enums.DataType;

public class FncValues extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FncValues fncValues = (FncValues) o;
		return position == fncValues.position && dataType == fncValues.dataType && indexMode == fncValues.indexMode
				&& addressType == fncValues.addressType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, dataType, indexMode, addressType);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
