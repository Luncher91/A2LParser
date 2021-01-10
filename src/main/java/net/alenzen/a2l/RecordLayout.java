package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

public class RecordLayout implements IA2LWriteable {
	private String name;

	// optional parameters
	private Long alignmentByte;
	private Long alignmentFloat32IEEE;
	private Long alignmentFloat64IEEE;
	private Long alignmentInt64;
	private Long alignmentLong;
	private Long alignmentWord;

	private AxisPtsXYZ45 axisPtsX;
	private AxisPtsXYZ45 axisPtsY;
	private AxisPtsXYZ45 axisPtsZ;
	private AxisPtsXYZ45 axisPts4;
	private AxisPtsXYZ45 axisPts5;

	private AxisRescaleXYZ45 axisRescaleX;
	private AxisRescaleXYZ45 axisRescaleY;
	private AxisRescaleXYZ45 axisRescaleZ;
	private AxisRescaleXYZ45 axisRescale4;
	private AxisRescaleXYZ45 axisRescale5;

	private DistOpXYZ45 distOpX;
	private DistOpXYZ45 distOpY;
	private DistOpXYZ45 distOpZ;
	private DistOpXYZ45 distOp4;
	private DistOpXYZ45 distOp5;

	private FixNoAxisPtsXYZ45 fixNoAxisPtsX;
	private FixNoAxisPtsXYZ45 fixNoAxisPtsY;
	private FixNoAxisPtsXYZ45 fixNoAxisPtsZ;
	private FixNoAxisPtsXYZ45 fixNoAxisPts4;
	private FixNoAxisPtsXYZ45 fixNoAxisPts5;

	private FncValues functionValues;
	private Identification identification;

	private NoAxisPtsXYZ45 noAxisPtsX;
	private NoAxisPtsXYZ45 noAxisPtsY;
	private NoAxisPtsXYZ45 noAxisPtsZ;
	private NoAxisPtsXYZ45 noAxisPts4;
	private NoAxisPtsXYZ45 noAxisPts5;

	private NoRescaleXYZ45 noRescaleX;
	private NoRescaleXYZ45 noRescaleY;
	private NoRescaleXYZ45 noRescaleZ;
	private NoRescaleXYZ45 noRescale4;
	private NoRescaleXYZ45 noRescale5;

	private OffsetXYZ45 offsetX;
	private OffsetXYZ45 offsetY;
	private OffsetXYZ45 offsetZ;
	private OffsetXYZ45 offset4;
	private OffsetXYZ45 offset5;

	private List<Reserved> reserved;

	private RipAddrWXYZ45 ripAddressW;
	private RipAddrWXYZ45 ripAddressX;
	private RipAddrWXYZ45 ripAddressY;
	private RipAddrWXYZ45 ripAddressZ;
	private RipAddrWXYZ45 ripAddress4;
	private RipAddrWXYZ45 ripAddress5;

	private SrcAddrXYZ45 srcAddressX;
	private SrcAddrXYZ45 srcAddressY;
	private SrcAddrXYZ45 srcAddressZ;
	private SrcAddrXYZ45 srcAddress4;
	private SrcAddrXYZ45 srcAddress5;

	private ShiftOpXYZ45 shiftOpX;
	private ShiftOpXYZ45 shiftOpY;
	private ShiftOpXYZ45 shiftOpZ;
	private ShiftOpXYZ45 shiftOp4;
	private ShiftOpXYZ45 shiftOp5;

	private boolean staticRecordLayout;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public AxisPtsXYZ45 getAxisPtsX() {
		return axisPtsX;
	}

	public void setAxisPtsX(AxisPtsXYZ45 axisPtsX) {
		this.axisPtsX = axisPtsX;
	}

	public AxisPtsXYZ45 getAxisPtsY() {
		return axisPtsY;
	}

	public void setAxisPtsY(AxisPtsXYZ45 axisPtsY) {
		this.axisPtsY = axisPtsY;
	}

	public AxisPtsXYZ45 getAxisPtsZ() {
		return axisPtsZ;
	}

	public void setAxisPtsZ(AxisPtsXYZ45 axisPtsZ) {
		this.axisPtsZ = axisPtsZ;
	}

	public AxisPtsXYZ45 getAxisPts4() {
		return axisPts4;
	}

	public void setAxisPts4(AxisPtsXYZ45 axisPts4) {
		this.axisPts4 = axisPts4;
	}

	public AxisPtsXYZ45 getAxisPts5() {
		return axisPts5;
	}

	public void setAxisPts5(AxisPtsXYZ45 axisPts5) {
		this.axisPts5 = axisPts5;
	}

	public AxisRescaleXYZ45 getAxisRescaleX() {
		return axisRescaleX;
	}

	public void setAxisRescaleX(AxisRescaleXYZ45 axisRescaleX) {
		this.axisRescaleX = axisRescaleX;
	}

	public AxisRescaleXYZ45 getAxisRescaleY() {
		return axisRescaleY;
	}

	public void setAxisRescaleY(AxisRescaleXYZ45 axisRescaleY) {
		this.axisRescaleY = axisRescaleY;
	}

	public AxisRescaleXYZ45 getAxisRescaleZ() {
		return axisRescaleZ;
	}

	public void setAxisRescaleZ(AxisRescaleXYZ45 axisRescaleZ) {
		this.axisRescaleZ = axisRescaleZ;
	}

	public AxisRescaleXYZ45 getAxisRescale4() {
		return axisRescale4;
	}

	public void setAxisRescale4(AxisRescaleXYZ45 axisRescale4) {
		this.axisRescale4 = axisRescale4;
	}

	public AxisRescaleXYZ45 getAxisRescale5() {
		return axisRescale5;
	}

	public void setAxisRescale5(AxisRescaleXYZ45 axisRescale5) {
		this.axisRescale5 = axisRescale5;
	}

	public DistOpXYZ45 getDistOpX() {
		return distOpX;
	}

	public void setDistOpX(DistOpXYZ45 distOpX) {
		this.distOpX = distOpX;
	}

	public DistOpXYZ45 getDistOpY() {
		return distOpY;
	}

	public void setDistOpY(DistOpXYZ45 distOpY) {
		this.distOpY = distOpY;
	}

	public DistOpXYZ45 getDistOpZ() {
		return distOpZ;
	}

	public void setDistOpZ(DistOpXYZ45 distOpZ) {
		this.distOpZ = distOpZ;
	}

	public DistOpXYZ45 getDistOp4() {
		return distOp4;
	}

	public void setDistOp4(DistOpXYZ45 distOp4) {
		this.distOp4 = distOp4;
	}

	public DistOpXYZ45 getDistOp5() {
		return distOp5;
	}

	public void setDistOp5(DistOpXYZ45 distOp5) {
		this.distOp5 = distOp5;
	}

	public FixNoAxisPtsXYZ45 getFixNoAxisPtsX() {
		return fixNoAxisPtsX;
	}

	public void setFixNoAxisPtsX(FixNoAxisPtsXYZ45 fixNoAxisPtsX) {
		this.fixNoAxisPtsX = fixNoAxisPtsX;
	}

	public FixNoAxisPtsXYZ45 getFixNoAxisPtsY() {
		return fixNoAxisPtsY;
	}

	public void setFixNoAxisPtsY(FixNoAxisPtsXYZ45 fixNoAxisPtsY) {
		this.fixNoAxisPtsY = fixNoAxisPtsY;
	}

	public FixNoAxisPtsXYZ45 getFixNoAxisPtsZ() {
		return fixNoAxisPtsZ;
	}

	public void setFixNoAxisPtsZ(FixNoAxisPtsXYZ45 fixNoAxisPtsZ) {
		this.fixNoAxisPtsZ = fixNoAxisPtsZ;
	}

	public FixNoAxisPtsXYZ45 getFixNoAxisPts4() {
		return fixNoAxisPts4;
	}

	public void setFixNoAxisPts4(FixNoAxisPtsXYZ45 fixNoAxisPts4) {
		this.fixNoAxisPts4 = fixNoAxisPts4;
	}

	public FixNoAxisPtsXYZ45 getFixNoAxisPts5() {
		return fixNoAxisPts5;
	}

	public void setFixNoAxisPts5(FixNoAxisPtsXYZ45 fixNoAxisPts5) {
		this.fixNoAxisPts5 = fixNoAxisPts5;
	}

	public FncValues getFunctionValues() {
		return functionValues;
	}

	public void setFunctionValues(FncValues functionValues) {
		this.functionValues = functionValues;
	}

	public Identification getIdentification() {
		return identification;
	}

	public void setIdentification(Identification identification) {
		this.identification = identification;
	}

	public NoAxisPtsXYZ45 getNoAxisPtsX() {
		return noAxisPtsX;
	}

	public void setNoAxisPtsX(NoAxisPtsXYZ45 noAxisPtsX) {
		this.noAxisPtsX = noAxisPtsX;
	}

	public NoAxisPtsXYZ45 getNoAxisPtsY() {
		return noAxisPtsY;
	}

	public void setNoAxisPtsY(NoAxisPtsXYZ45 noAxisPtsY) {
		this.noAxisPtsY = noAxisPtsY;
	}

	public NoAxisPtsXYZ45 getNoAxisPtsZ() {
		return noAxisPtsZ;
	}

	public void setNoAxisPtsZ(NoAxisPtsXYZ45 noAxisPtsZ) {
		this.noAxisPtsZ = noAxisPtsZ;
	}

	public NoAxisPtsXYZ45 getNoAxisPts4() {
		return noAxisPts4;
	}

	public void setNoAxisPts4(NoAxisPtsXYZ45 noAxisPts4) {
		this.noAxisPts4 = noAxisPts4;
	}

	public NoAxisPtsXYZ45 getNoAxisPts5() {
		return noAxisPts5;
	}

	public void setNoAxisPts5(NoAxisPtsXYZ45 noAxisPts5) {
		this.noAxisPts5 = noAxisPts5;
	}

	public NoRescaleXYZ45 getNoRescaleX() {
		return noRescaleX;
	}

	public void setNoRescaleX(NoRescaleXYZ45 noRescaleX) {
		this.noRescaleX = noRescaleX;
	}

	public NoRescaleXYZ45 getNoRescaleY() {
		return noRescaleY;
	}

	public void setNoRescaleY(NoRescaleXYZ45 noRescaleY) {
		this.noRescaleY = noRescaleY;
	}

	public NoRescaleXYZ45 getNoRescaleZ() {
		return noRescaleZ;
	}

	public void setNoRescaleZ(NoRescaleXYZ45 noRescaleZ) {
		this.noRescaleZ = noRescaleZ;
	}

	public NoRescaleXYZ45 getNoRescale4() {
		return noRescale4;
	}

	public void setNoRescale4(NoRescaleXYZ45 noRescale4) {
		this.noRescale4 = noRescale4;
	}

	public NoRescaleXYZ45 getNoRescale5() {
		return noRescale5;
	}

	public void setNoRescale5(NoRescaleXYZ45 noRescale5) {
		this.noRescale5 = noRescale5;
	}

	public OffsetXYZ45 getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(OffsetXYZ45 offsetX) {
		this.offsetX = offsetX;
	}

	public OffsetXYZ45 getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(OffsetXYZ45 offsetY) {
		this.offsetY = offsetY;
	}

	public OffsetXYZ45 getOffsetZ() {
		return offsetZ;
	}

	public void setOffsetZ(OffsetXYZ45 offsetZ) {
		this.offsetZ = offsetZ;
	}

	public OffsetXYZ45 getOffset4() {
		return offset4;
	}

	public void setOffset4(OffsetXYZ45 offset4) {
		this.offset4 = offset4;
	}

	public OffsetXYZ45 getOffset5() {
		return offset5;
	}

	public void setOffset5(OffsetXYZ45 offset5) {
		this.offset5 = offset5;
	}

	public List<Reserved> getReserved() {
		return reserved;
	}

	public void setReserved(List<Reserved> reserved) {
		this.reserved = reserved;
	}

	public RipAddrWXYZ45 getRipAddressW() {
		return ripAddressW;
	}

	public void setRipAddressW(RipAddrWXYZ45 ripAddressW) {
		this.ripAddressW = ripAddressW;
	}

	public RipAddrWXYZ45 getRipAddressX() {
		return ripAddressX;
	}

	public void setRipAddressX(RipAddrWXYZ45 ripAddressX) {
		this.ripAddressX = ripAddressX;
	}

	public RipAddrWXYZ45 getRipAddressY() {
		return ripAddressY;
	}

	public void setRipAddressY(RipAddrWXYZ45 ripAddressY) {
		this.ripAddressY = ripAddressY;
	}

	public RipAddrWXYZ45 getRipAddressZ() {
		return ripAddressZ;
	}

	public void setRipAddressZ(RipAddrWXYZ45 ripAddressZ) {
		this.ripAddressZ = ripAddressZ;
	}

	public RipAddrWXYZ45 getRipAddress4() {
		return ripAddress4;
	}

	public void setRipAddress4(RipAddrWXYZ45 ripAddress4) {
		this.ripAddress4 = ripAddress4;
	}

	public RipAddrWXYZ45 getRipAddress5() {
		return ripAddress5;
	}

	public void setRipAddress5(RipAddrWXYZ45 ripAddress5) {
		this.ripAddress5 = ripAddress5;
	}

	public SrcAddrXYZ45 getSrcAddressX() {
		return srcAddressX;
	}

	public void setSrcAddressX(SrcAddrXYZ45 srcAddressX) {
		this.srcAddressX = srcAddressX;
	}

	public SrcAddrXYZ45 getSrcAddressY() {
		return srcAddressY;
	}

	public void setSrcAddressY(SrcAddrXYZ45 srcAddressY) {
		this.srcAddressY = srcAddressY;
	}

	public SrcAddrXYZ45 getSrcAddressZ() {
		return srcAddressZ;
	}

	public void setSrcAddressZ(SrcAddrXYZ45 srcAddressZ) {
		this.srcAddressZ = srcAddressZ;
	}

	public SrcAddrXYZ45 getSrcAddress4() {
		return srcAddress4;
	}

	public void setSrcAddress4(SrcAddrXYZ45 srcAddress4) {
		this.srcAddress4 = srcAddress4;
	}

	public SrcAddrXYZ45 getSrcAddress5() {
		return srcAddress5;
	}

	public void setSrcAddress5(SrcAddrXYZ45 srcAddress5) {
		this.srcAddress5 = srcAddress5;
	}

	public ShiftOpXYZ45 getShiftOpX() {
		return shiftOpX;
	}

	public void setShiftOpX(ShiftOpXYZ45 shiftOpX) {
		this.shiftOpX = shiftOpX;
	}

	public ShiftOpXYZ45 getShiftOpY() {
		return shiftOpY;
	}

	public void setShiftOpY(ShiftOpXYZ45 shiftOpY) {
		this.shiftOpY = shiftOpY;
	}

	public ShiftOpXYZ45 getShiftOpZ() {
		return shiftOpZ;
	}

	public void setShiftOpZ(ShiftOpXYZ45 shiftOpZ) {
		this.shiftOpZ = shiftOpZ;
	}

	public ShiftOpXYZ45 getShiftOp4() {
		return shiftOp4;
	}

	public void setShiftOp4(ShiftOpXYZ45 shiftOp4) {
		this.shiftOp4 = shiftOp4;
	}

	public ShiftOpXYZ45 getShiftOp5() {
		return shiftOp5;
	}

	public void setShiftOp5(ShiftOpXYZ45 shiftOp5) {
		this.shiftOp5 = shiftOp5;
	}

	public boolean isStaticRecordLayout() {
		return staticRecordLayout;
	}

	public void setStaticRecordLayout(boolean staticRecordLayout) {
		this.staticRecordLayout = staticRecordLayout;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("RECORD_LAYOUT", name);
		writer.indent();
		
		writeLongOptionalAttribute(writer, "ALIGNMENT_BYTE", alignmentByte);		
		writeLongOptionalAttribute(writer, "ALIGNMENT_FLOAT32_IEEE", alignmentFloat32IEEE);
		writeLongOptionalAttribute(writer, "ALIGNMENT_FLOAT64_IEEE", alignmentFloat64IEEE);
		writeLongOptionalAttribute(writer, "ALIGNMENT_INT64", alignmentInt64);
		writeLongOptionalAttribute(writer, "ALIGNMENT_LONG", alignmentLong);
		writeLongOptionalAttribute(writer, "ALIGNMENT_WORD", alignmentWord);
		
		writer.write(axisPtsX.getA2lWritable("X"));
		writer.write(axisPtsY.getA2lWritable("Y"));
		writer.write(axisPtsZ.getA2lWritable("Z"));
		writer.write(axisPts4.getA2lWritable("4"));
		writer.write(axisPts5.getA2lWritable("5"));
		
		writer.write(axisRescaleX.getA2lWritable("X"));
		writer.write(axisRescaleY.getA2lWritable("Y"));
		writer.write(axisRescaleZ.getA2lWritable("Z"));
		writer.write(axisRescale4.getA2lWritable("4"));
		writer.write(axisRescale5.getA2lWritable("5"));
		
		writer.write(distOpX.getA2lWritable("X"));
		writer.write(distOpY.getA2lWritable("Y"));
		writer.write(distOpZ.getA2lWritable("Z"));
		writer.write(distOp4.getA2lWritable("4"));
		writer.write(distOp5.getA2lWritable("5"));
		
		writer.write(fixNoAxisPtsX.getA2lWritable("X"));
		writer.write(fixNoAxisPtsY.getA2lWritable("Y"));
		writer.write(fixNoAxisPtsZ.getA2lWritable("Z"));
		writer.write(fixNoAxisPts4.getA2lWritable("4"));
		writer.write(fixNoAxisPts5.getA2lWritable("5"));
		
		writer.write(functionValues);
		writer.write(identification);
		
		writer.write(noAxisPtsX.getA2lWritable("X"));
		writer.write(noAxisPtsY.getA2lWritable("Y"));
		writer.write(noAxisPtsZ.getA2lWritable("Z"));
		writer.write(noAxisPts4.getA2lWritable("4"));
		writer.write(noAxisPts5.getA2lWritable("5"));
		
		writer.write(noRescaleX.getA2lWritable("X"));
		writer.write(noRescaleY.getA2lWritable("Y"));
		writer.write(noRescaleZ.getA2lWritable("Z"));
		writer.write(noRescale4.getA2lWritable("4"));
		writer.write(noRescale5.getA2lWritable("5"));
		
		writer.write(offsetX.getA2lWritable("X"));
		writer.write(offsetY.getA2lWritable("Y"));
		writer.write(offsetZ.getA2lWritable("Z"));
		writer.write(offset4.getA2lWritable("4"));
		writer.write(offset5.getA2lWritable("5"));
		
		writer.write(reserved);
		
		writer.write(ripAddressW.getA2lWritable("W"));
		writer.write(ripAddressX.getA2lWritable("X"));
		writer.write(ripAddressY.getA2lWritable("Y"));
		writer.write(ripAddressZ.getA2lWritable("Z"));
		writer.write(ripAddress4.getA2lWritable("4"));
		writer.write(ripAddress5.getA2lWritable("5"));
		
		writer.write(srcAddressX.getA2lWritable("X"));
		writer.write(srcAddressY.getA2lWritable("Y"));
		writer.write(srcAddressZ.getA2lWritable("Z"));
		writer.write(srcAddress4.getA2lWritable("4"));
		writer.write(srcAddress5.getA2lWritable("5"));
		
		writer.write(shiftOpX.getA2lWritable("X"));
		writer.write(shiftOpY.getA2lWritable("Y"));
		writer.write(shiftOpZ.getA2lWritable("Z"));
		writer.write(shiftOp4.getA2lWritable("4"));
		writer.write(shiftOp5.getA2lWritable("5"));
		
		if(staticRecordLayout) {
			writer.writeln("STATIC_RECORD_LAYOUT");
		}
		
		writer.dedent();
		writer.writelnEnd("RECORD_LAYOUT");
	}
	
	private static void writeLongOptionalAttribute(A2LWriter writer, String name, Long l) throws IOException {
		if(l != null) {
			writer.writelnSpaced(name, l.toString());
		}
	}
}
