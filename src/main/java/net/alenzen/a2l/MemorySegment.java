package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

public class MemorySegment implements IA2LWriteable {
	private String name;
	private String longIdentifier;
	private PrgType prgType;
	private MemoryType memoryType;
	private Attribute attribute;
	private long address;
	private long size;
	private long[] offset;

	// optional parameters
	private List<IfData> ifDatas;

	enum PrgType {
		CALIBRATION_VARIABLES, CODE, DATA, EXCLUDE_FROM_FLASH, OFFLINE_DATA, RESERVED, SERAM, VARIABLES
	}

	enum MemoryType {
		EEPROM, EPROM, FLASH, RAM, ROM, REGISTER, NOT_IN_ECU
	}

	enum Attribute {
		INTERN, EXTERN
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongIdentifier() {
		return longIdentifier;
	}

	public void setLongIdentifier(String longIdentifier) {
		this.longIdentifier = longIdentifier;
	}

	public PrgType getPrgType() {
		return prgType;
	}

	public void setPrgType(PrgType prgType) {
		this.prgType = prgType;
	}

	public MemoryType getMemoryType() {
		return memoryType;
	}

	public void setMemoryType(MemoryType memoryType) {
		this.memoryType = memoryType;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("MEMORY_SEGMENT", name, A2LWriter.toA2LString(longIdentifier), prgType.name(), memoryType.name(), attribute.name(), "0x" + Long.toHexString(address), Long.toString(size));
		writer.indent();
		
		for (long l : offset) {
			writer.writeln(Long.toString(l));
		}

		writer.write(ifDatas);
		
		writer.dedent();
		writer.writelnEnd("MEMORY_SEGMENT");
	}
}
