package net.alenzen.a2l;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class MemorySegment extends A2LSerializer implements IA2LWriteable {
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

	public enum PrgType {
		CALIBRATION_VARIABLES, CODE, DATA, EXCLUDE_FROM_FLASH, OFFLINE_DATA, RESERVED, SERAM, VARIABLES
	}

	public enum MemoryType {
		EEPROM, EPROM, FLASH, RAM, ROM, REGISTER, NOT_IN_ECU
	}

	public enum Attribute {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MemorySegment that = (MemorySegment) o;
		return address == that.address && size == that.size && Objects.equals(name, that.name) && Objects
				.equals(longIdentifier, that.longIdentifier) && prgType == that.prgType && memoryType == that.memoryType
				&& attribute == that.attribute && Arrays.equals(offset, that.offset) && Objects
				.equals(ifDatas, that.ifDatas);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(name, longIdentifier, prgType, memoryType, attribute, address, size, ifDatas);
		result = 31 * result + Arrays.hashCode(offset);
		return result;
	}
}
