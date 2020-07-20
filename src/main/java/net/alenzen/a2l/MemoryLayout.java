package net.alenzen.a2l;

import java.util.List;

public class MemoryLayout {
	private PrgType prgType;
	private long address;
	private long size;
	private long[] offset;

	// optional parameters
	private List<IfData> ifDatas;

	enum PrgType {
		PRG_CODE, PRG_DATA, PRG_RESERVED
	}

	public PrgType getPrgType() {
		return prgType;
	}

	public void setPrgType(PrgType prgType) {
		this.prgType = prgType;
	}

	public long getAddress() {
		return address;
	}

	public void setAddress(long address) {
		this.address = address;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long[] getOffset() {
		return offset;
	}

	public void setOffset(long[] offset) {
		this.offset = offset;
	}

	public List<IfData> getIfDatas() {
		return ifDatas;
	}

	public void setIfDatas(List<IfData> ifDatas) {
		this.ifDatas = ifDatas;
	}
}
