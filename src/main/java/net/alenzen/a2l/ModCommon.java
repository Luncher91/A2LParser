package net.alenzen.a2l;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.Deposit;

public class ModCommon {
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
}
