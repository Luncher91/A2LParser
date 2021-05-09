package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.FncValues.IndexMode;
import net.alenzen.a2l.enums.AddrType;
import net.alenzen.a2l.enums.DataSize;
import net.alenzen.a2l.enums.DataType;
import net.alenzen.a2l.enums.IndexOrder;
import nl.jqno.equalsverifier.EqualsVerifier;

public class RecordLayoutTest {
	private Asap2File file;
	private RecordLayout recordLayout;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		recordLayout = file.getProject().getModules().get(0).getRecordLayouts().get(0);

		assertNotNull(recordLayout);
	}

	@Test
	void testName() {
		assertEquals("master_record", recordLayout.getName());
	}

	@Test
	void testAlignmentByte() {
		assertEquals(2, recordLayout.getAlignmentByte());
	}

	@Test
	void testAlignmentFloat32() {
		assertEquals(4, recordLayout.getAlignmentFloat32IEEE());
	}

	@Test
	void testAlignmentFloat64() {
		assertEquals(8, recordLayout.getAlignmentFloat64IEEE());
	}

	@Test
	void testAlignmentInt64() {
		assertEquals(16, recordLayout.getAlignmentInt64());
	}

	@Test
	void testAlignmentLong() {
		assertEquals(32, recordLayout.getAlignmentLong());
	}

	@Test
	void testAlignmentWord() {
		assertEquals(64, recordLayout.getAlignmentWord());
	}

	@Test
	void testAxisPtsX() {
		AxisPtsXYZ45 axisPts = recordLayout.getAxisPtsX();
		assertEquals(1, axisPts.getPosition());
		assertEquals(DataType.SBYTE, axisPts.getDatatype());
		assertEquals(IndexOrder.INDEX_INCR, axisPts.getIndexorder());
		assertEquals(AddrType.DIRECT, axisPts.getAddressing());
	}

	@Test
	void testAxisPtsY() {
		AxisPtsXYZ45 axisPts = recordLayout.getAxisPtsY();
		assertEquals(2, axisPts.getPosition());
		assertEquals(DataType.UBYTE, axisPts.getDatatype());
		assertEquals(IndexOrder.INDEX_DECR, axisPts.getIndexorder());
		assertEquals(AddrType.PBYTE, axisPts.getAddressing());
	}

	@Test
	void testAxisPtsZ() {
		AxisPtsXYZ45 axisPts = recordLayout.getAxisPtsZ();
		assertEquals(3, axisPts.getPosition());
		assertEquals(DataType.UBYTE, axisPts.getDatatype());
		assertEquals(IndexOrder.INDEX_DECR, axisPts.getIndexorder());
		assertEquals(AddrType.PBYTE, axisPts.getAddressing());
	}

	@Test
	void testAxisPts4() {
		AxisPtsXYZ45 axisPts = recordLayout.getAxisPts4();
		assertEquals(4, axisPts.getPosition());
		assertEquals(DataType.UBYTE, axisPts.getDatatype());
		assertEquals(IndexOrder.INDEX_DECR, axisPts.getIndexorder());
		assertEquals(AddrType.PBYTE, axisPts.getAddressing());
	}

	@Test
	void testAxisPts5() {
		AxisPtsXYZ45 axisPts = recordLayout.getAxisPts5();
		assertEquals(5, axisPts.getPosition());
		assertEquals(DataType.UBYTE, axisPts.getDatatype());
		assertEquals(IndexOrder.INDEX_DECR, axisPts.getIndexorder());
		assertEquals(AddrType.PBYTE, axisPts.getAddressing());
	}

	@Test
	void testAxisRescaleX() {
		AxisRescaleXYZ45 axisRescale = recordLayout.getAxisRescaleX();
		assertEquals(6, axisRescale.getPosition());
		assertEquals(DataType.UBYTE, axisRescale.getDatatype());
		assertEquals(3141, axisRescale.getMaxNumberOfRescalePairs());
		assertEquals(IndexOrder.INDEX_DECR, axisRescale.getIndexorder());
		assertEquals(AddrType.PBYTE, axisRescale.getAddressing());
	}

	@Test
	void testAxisRescaleY() {
		AxisRescaleXYZ45 axisRescale = recordLayout.getAxisRescaleY();
		assertEquals(7, axisRescale.getPosition());
		assertEquals(DataType.UBYTE, axisRescale.getDatatype());
		assertEquals(3141, axisRescale.getMaxNumberOfRescalePairs());
		assertEquals(IndexOrder.INDEX_DECR, axisRescale.getIndexorder());
		assertEquals(AddrType.PBYTE, axisRescale.getAddressing());
	}

	@Test
	void testAxisRescaleZ() {
		AxisRescaleXYZ45 axisRescale = recordLayout.getAxisRescaleZ();
		assertEquals(8, axisRescale.getPosition());
		assertEquals(DataType.UBYTE, axisRescale.getDatatype());
		assertEquals(3141, axisRescale.getMaxNumberOfRescalePairs());
		assertEquals(IndexOrder.INDEX_DECR, axisRescale.getIndexorder());
		assertEquals(AddrType.PBYTE, axisRescale.getAddressing());
	}

	@Test
	void testAxisRescale4() {
		AxisRescaleXYZ45 axisRescale = recordLayout.getAxisRescale4();
		assertEquals(9, axisRescale.getPosition());
		assertEquals(DataType.UBYTE, axisRescale.getDatatype());
		assertEquals(3141, axisRescale.getMaxNumberOfRescalePairs());
		assertEquals(IndexOrder.INDEX_DECR, axisRescale.getIndexorder());
		assertEquals(AddrType.PBYTE, axisRescale.getAddressing());
	}

	@Test
	void testAxisRescale5() {
		AxisRescaleXYZ45 axisRescale = recordLayout.getAxisRescale5();
		assertEquals(10, axisRescale.getPosition());
		assertEquals(DataType.UBYTE, axisRescale.getDatatype());
		assertEquals(3141, axisRescale.getMaxNumberOfRescalePairs());
		assertEquals(IndexOrder.INDEX_DECR, axisRescale.getIndexorder());
		assertEquals(AddrType.PBYTE, axisRescale.getAddressing());
	}

	@Test
	void testDistOpX() {
		DistOpXYZ45 distOp = recordLayout.getDistOpX();
		assertEquals(11, distOp.getPosition());
		assertEquals(DataType.SBYTE, distOp.getDataType());
	}

	@Test
	void testDistOpY() {
		DistOpXYZ45 distOp = recordLayout.getDistOpY();
		assertEquals(12, distOp.getPosition());
		assertEquals(DataType.SBYTE, distOp.getDataType());
	}

	@Test
	void testDistOpZ() {
		DistOpXYZ45 distOp = recordLayout.getDistOpZ();
		assertEquals(13, distOp.getPosition());
		assertEquals(DataType.SBYTE, distOp.getDataType());
	}

	@Test
	void testDistOp4() {
		DistOpXYZ45 distOp = recordLayout.getDistOp4();
		assertEquals(14, distOp.getPosition());
		assertEquals(DataType.SBYTE, distOp.getDataType());
	}

	@Test
	void testDistOp5() {
		DistOpXYZ45 distOp = recordLayout.getDistOp5();
		assertEquals(15, distOp.getPosition());
		assertEquals(DataType.SBYTE, distOp.getDataType());
	}

	@Test
	void testFixNoAxisPtsX() {
		FixNoAxisPtsXYZ45 fixNoAxisPts = recordLayout.getFixNoAxisPtsX();
		assertEquals(1, fixNoAxisPts.getNumberOfAxisPoints());
	}

	@Test
	void testFixNoAxisPtsY() {
		FixNoAxisPtsXYZ45 fixNoAxisPts = recordLayout.getFixNoAxisPtsY();
		assertEquals(2, fixNoAxisPts.getNumberOfAxisPoints());
	}

	@Test
	void testFixNoAxisPtsZ() {
		FixNoAxisPtsXYZ45 fixNoAxisPts = recordLayout.getFixNoAxisPtsZ();
		assertEquals(3, fixNoAxisPts.getNumberOfAxisPoints());
	}

	@Test
	void testFixNoAxisPts4() {
		FixNoAxisPtsXYZ45 fixNoAxisPts = recordLayout.getFixNoAxisPts4();
		assertEquals(4, fixNoAxisPts.getNumberOfAxisPoints());
	}

	@Test
	void testFixNoAxisPts5() {
		FixNoAxisPtsXYZ45 fixNoAxisPts = recordLayout.getFixNoAxisPts5();
		assertEquals(5, fixNoAxisPts.getNumberOfAxisPoints());
	}

	@Test
	void testFncValues() {
		FncValues fncValues = recordLayout.getFunctionValues();
		assertEquals(47, fncValues.getPosition());
		assertEquals(DataType.UBYTE, fncValues.getDataType());
		assertEquals(IndexMode.ALTERNATE_CURVES, fncValues.getIndexMode());
		assertEquals(AddrType.PLONG, fncValues.getAddressType());
	}

	@Test
	void testIdentification() {
		Identification ident = recordLayout.getIdentification();
		assertEquals(48, ident.getPosition());
		assertEquals(DataType.ULONG, ident.getDataType());
	}

	@Test
	void testNoAxisPtsX() {
		NoAxisPtsXYZ45 noAxisPts = recordLayout.getNoAxisPtsX();
		assertEquals(16, noAxisPts.getPosition());
		assertEquals(DataType.UBYTE, noAxisPts.getDataType());
	}

	@Test
	void testNoAxisPtsY() {
		NoAxisPtsXYZ45 noAxisPts = recordLayout.getNoAxisPtsY();
		assertEquals(17, noAxisPts.getPosition());
		assertEquals(DataType.UBYTE, noAxisPts.getDataType());
	}

	@Test
	void testNoAxisPtsZ() {
		NoAxisPtsXYZ45 noAxisPts = recordLayout.getNoAxisPtsZ();
		assertEquals(18, noAxisPts.getPosition());
		assertEquals(DataType.UBYTE, noAxisPts.getDataType());
	}

	@Test
	void testNoAxisPts4() {
		NoAxisPtsXYZ45 noAxisPts = recordLayout.getNoAxisPts4();
		assertEquals(19, noAxisPts.getPosition());
		assertEquals(DataType.UBYTE, noAxisPts.getDataType());
	}

	@Test
	void testNoAxisPts5() {
		NoAxisPtsXYZ45 noAxisPts = recordLayout.getNoAxisPts5();
		assertEquals(20, noAxisPts.getPosition());
		assertEquals(DataType.UBYTE, noAxisPts.getDataType());
	}

	@Test
	void testNoRescaleX() {
		NoRescaleXYZ45 noRescale = recordLayout.getNoRescaleX();
		assertEquals(21, noRescale.getPosition());
		assertEquals(DataType.ULONG, noRescale.getDataType());
	}

	@Test
	void testNoRescaleY() {
		NoRescaleXYZ45 noRescale = recordLayout.getNoRescaleY();
		assertEquals(22, noRescale.getPosition());
		assertEquals(DataType.ULONG, noRescale.getDataType());
	}

	@Test
	void testNoRescaleZ() {
		NoRescaleXYZ45 noRescale = recordLayout.getNoRescaleZ();
		assertEquals(23, noRescale.getPosition());
		assertEquals(DataType.ULONG, noRescale.getDataType());
	}

	@Test
	void testNoRescale4() {
		NoRescaleXYZ45 noRescale = recordLayout.getNoRescale4();
		assertEquals(24, noRescale.getPosition());
		assertEquals(DataType.ULONG, noRescale.getDataType());
	}

	@Test
	void testNoRescale5() {
		NoRescaleXYZ45 noRescale = recordLayout.getNoRescale5();
		assertEquals(25, noRescale.getPosition());
		assertEquals(DataType.ULONG, noRescale.getDataType());
	}

	@Test
	void testOffsetX() {
		OffsetXYZ45 offset = recordLayout.getOffsetX();
		assertEquals(26, offset.getPosition());
		assertEquals(DataType.ULONG, offset.getDataType());
	}

	@Test
	void testOffsetY() {
		OffsetXYZ45 offset = recordLayout.getOffsetY();
		assertEquals(27, offset.getPosition());
		assertEquals(DataType.ULONG, offset.getDataType());
	}

	@Test
	void testOffsetZ() {
		OffsetXYZ45 offset = recordLayout.getOffsetZ();
		assertEquals(28, offset.getPosition());
		assertEquals(DataType.ULONG, offset.getDataType());
	}

	@Test
	void testOffset4() {
		OffsetXYZ45 offset = recordLayout.getOffset4();
		assertEquals(29, offset.getPosition());
		assertEquals(DataType.ULONG, offset.getDataType());
	}

	@Test
	void testOffset5() {
		OffsetXYZ45 offset = recordLayout.getOffset5();
		assertEquals(30, offset.getPosition());
		assertEquals(DataType.ULONG, offset.getDataType());
	}

	@Test
	void testReserved() {
		long[] positions = new long[] { 49, 50 };
		DataSize[] dataSizes = new DataSize[] { DataSize.LONG, DataSize.WORD };
		List<Reserved> reserved = recordLayout.getReserved();

		assertEquals(positions.length, reserved.size());
		assertEquals(dataSizes.length, reserved.size());

		for (int i = 0; i < reserved.size(); i++) {
			Reserved r = reserved.get(i);
			assertEquals(positions[i], r.getPosition());
			assertEquals(dataSizes[i], r.getDataSize());
		}
	}

	@Test
	void testRipAddrW() {
		RipAddrWXYZ45 ripAddr = recordLayout.getRipAddressW();
		assertEquals(31, ripAddr.getPosition());
		assertEquals(DataType.ULONG, ripAddr.getDataType());
	}

	@Test
	void testRipAddrX() {
		RipAddrWXYZ45 ripAddr = recordLayout.getRipAddressX();
		assertEquals(32, ripAddr.getPosition());
		assertEquals(DataType.ULONG, ripAddr.getDataType());
	}

	@Test
	void testRipAddrY() {
		RipAddrWXYZ45 ripAddr = recordLayout.getRipAddressY();
		assertEquals(33, ripAddr.getPosition());
		assertEquals(DataType.ULONG, ripAddr.getDataType());
	}

	@Test
	void testRipAddrZ() {
		RipAddrWXYZ45 ripAddr = recordLayout.getRipAddressZ();
		assertEquals(34, ripAddr.getPosition());
		assertEquals(DataType.ULONG, ripAddr.getDataType());
	}

	@Test
	void testRipAddr4() {
		RipAddrWXYZ45 ripAddr = recordLayout.getRipAddress4();
		assertEquals(35, ripAddr.getPosition());
		assertEquals(DataType.ULONG, ripAddr.getDataType());
	}

	@Test
	void testRipAddr5() {
		RipAddrWXYZ45 ripAddr = recordLayout.getRipAddress5();
		assertEquals(36, ripAddr.getPosition());
		assertEquals(DataType.ULONG, ripAddr.getDataType());
	}

	@Test
	void testSrcAddrX() {
		SrcAddrXYZ45 srcAddr = recordLayout.getSrcAddressX();
		assertEquals(37, srcAddr.getPosition());
		assertEquals(DataType.ULONG, srcAddr.getDataType());
	}

	@Test
	void testSrcAddrY() {
		SrcAddrXYZ45 srcAddr = recordLayout.getSrcAddressY();
		assertEquals(38, srcAddr.getPosition());
		assertEquals(DataType.ULONG, srcAddr.getDataType());
	}

	@Test
	void testSrcAddrZ() {
		SrcAddrXYZ45 srcAddr = recordLayout.getSrcAddressZ();
		assertEquals(39, srcAddr.getPosition());
		assertEquals(DataType.ULONG, srcAddr.getDataType());
	}

	@Test
	void testSrcAddr4() {
		SrcAddrXYZ45 srcAddr = recordLayout.getSrcAddress4();
		assertEquals(40, srcAddr.getPosition());
		assertEquals(DataType.ULONG, srcAddr.getDataType());
	}

	@Test
	void testSrcAddr5() {
		SrcAddrXYZ45 srcAddr = recordLayout.getSrcAddress5();
		assertEquals(41, srcAddr.getPosition());
		assertEquals(DataType.ULONG, srcAddr.getDataType());
	}

	@Test
	void testShiftOpX() {
		ShiftOpXYZ45 shiftOp = recordLayout.getShiftOpX();
		assertEquals(42, shiftOp.getPosition());
		assertEquals(DataType.ULONG, shiftOp.getDataType());
	}

	@Test
	void testShiftOpY() {
		ShiftOpXYZ45 shiftOp = recordLayout.getShiftOpY();
		assertEquals(43, shiftOp.getPosition());
		assertEquals(DataType.ULONG, shiftOp.getDataType());
	}

	@Test
	void testShiftOpZ() {
		ShiftOpXYZ45 shiftOp = recordLayout.getShiftOpZ();
		assertEquals(44, shiftOp.getPosition());
		assertEquals(DataType.ULONG, shiftOp.getDataType());
	}

	@Test
	void testShiftOp4() {
		ShiftOpXYZ45 shiftOp = recordLayout.getShiftOp4();
		assertEquals(45, shiftOp.getPosition());
		assertEquals(DataType.ULONG, shiftOp.getDataType());
	}

	@Test
	void testShiftOp5() {
		ShiftOpXYZ45 shiftOp = recordLayout.getShiftOp5();
		assertEquals(46, shiftOp.getPosition());
		assertEquals(DataType.ULONG, shiftOp.getDataType());
	}

	@Test
	void testStaticRecordLayout() {
		assertEquals(true, recordLayout.isStaticRecordLayout());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(RecordLayout.class).verify();
	}
}
