package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.Deposit;

public class ModCommon implements IA2LWriteable {
	private String comment;

	// optional parameters
	private Long alignmentByte;
	private Long alignmentFloat32IEEE;
	private Long alignmentFloat64IEEE;
	private Long alignmentInt64;
	private Long alignmentLong;
	private Long alignmentWord;
	private ByteOrder byteorder;
	private Long dataSize;
	private Deposit deposit;
	private String standardRecordLayout;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getAlignmentByte() {
		return alignmentByte;
	}

	public void setAlignmentByte(Long alignmentByte) {
		this.alignmentByte = alignmentByte;
	}

	public Long getAlignmentFloat32IEEE() {
		return alignmentFloat32IEEE;
	}

	public void setAlignmentFloat32IEEE(Long alignmentFloat32IEEE) {
		this.alignmentFloat32IEEE = alignmentFloat32IEEE;
	}

	public Long getAlignmentFloat64IEEE() {
		return alignmentFloat64IEEE;
	}

	public void setAlignmentFloat64IEEE(Long alignmentFloat64IEEE) {
		this.alignmentFloat64IEEE = alignmentFloat64IEEE;
	}

	public Long getAlignmentInt64() {
		return alignmentInt64;
	}

	public void setAlignmentInt64(Long alignmentInt64) {
		this.alignmentInt64 = alignmentInt64;
	}

	public Long getAlignmentLong() {
		return alignmentLong;
	}

	public void setAlignmentLong(Long alignmentLong) {
		this.alignmentLong = alignmentLong;
	}

	public Long getAlignmentWord() {
		return alignmentWord;
	}

	public void setAlignmentWord(Long alignmentWord) {
		this.alignmentWord = alignmentWord;
	}

	public ByteOrder getByteorder() {
		return byteorder;
	}

	public void setByteorder(ByteOrder byteorder) {
		this.byteorder = byteorder;
	}

	public Long getDataSize() {
		return dataSize;
	}

	public void setDataSize(Long dataSize) {
		this.dataSize = dataSize;
	}

	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	public String getStandardRecordLayout() {
		return standardRecordLayout;
	}

	public void setStandardRecordLayout(String standardRecordLayout) {
		this.standardRecordLayout = standardRecordLayout;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("MOD_COMMON", A2LWriter.toA2LString(comment));
		writer.indent();

		writeLongOptionalAttribute(writer, "ALIGNMENT_BYTE", alignmentByte);		
		writeLongOptionalAttribute(writer, "ALIGNMENT_FLOAT32_IEEE", alignmentFloat32IEEE);
		writeLongOptionalAttribute(writer, "ALIGNMENT_FLOAT64_IEEE", alignmentFloat64IEEE);
		writeLongOptionalAttribute(writer, "ALIGNMENT_INT64", alignmentInt64);
		writeLongOptionalAttribute(writer, "ALIGNMENT_LONG", alignmentLong);
		writeLongOptionalAttribute(writer, "ALIGNMENT_WORD", alignmentWord);
		writer.write(byteorder);
		writeLongOptionalAttribute(writer, "DATA_SIZE", dataSize);
		writer.write(deposit);
		
		if(standardRecordLayout != null) {
			writer.writelnSpaced("S_REC_LAYOUT", standardRecordLayout);
		}
		
		writer.dedent();
		writer.writelnEnd("MOD_COMMON");
	}
	
	private static void writeLongOptionalAttribute(A2LWriter writer, String name, Long l) throws IOException {
		if(l != null) {
			writer.writelnSpaced(name, l.toString());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ModCommon modCommon = (ModCommon) o;
		return Objects.equals(comment, modCommon.comment) && Objects.equals(alignmentByte, modCommon.alignmentByte)
				&& Objects.equals(alignmentFloat32IEEE, modCommon.alignmentFloat32IEEE) && Objects
				.equals(alignmentFloat64IEEE, modCommon.alignmentFloat64IEEE) && Objects
				.equals(alignmentInt64, modCommon.alignmentInt64) && Objects
				.equals(alignmentLong, modCommon.alignmentLong) && Objects
				.equals(alignmentWord, modCommon.alignmentWord) && byteorder == modCommon.byteorder && Objects
				.equals(dataSize, modCommon.dataSize) && deposit == modCommon.deposit && Objects
				.equals(standardRecordLayout, modCommon.standardRecordLayout);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(comment, alignmentByte, alignmentFloat32IEEE, alignmentFloat64IEEE, alignmentInt64, alignmentLong,
						alignmentWord, byteorder, dataSize, deposit, standardRecordLayout);
	}
}
