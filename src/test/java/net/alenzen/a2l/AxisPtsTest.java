package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.CalibrationAccess;
import net.alenzen.a2l.enums.Deposit;
import net.alenzen.a2l.enums.Monotony;

public class AxisPtsTest {
	private Asap2File file;
	private AxisPts axisPts;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		axisPts = file.getProject().getModules().get(0).getAxisPts().get(0);

		assertNotNull(axisPts);
	}

	@Test
	void testName() {
		assertEquals("axis_pts_1", axisPts.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("I am a long identifier of an AXIS_PTS object", axisPts.getLongIdentifier());
	}

	@Test
	void testAddress() {
		assertEquals(0xABCDEF, axisPts.getAddress());
	}

	@Test
	void testInputQuantity() {
		assertEquals("in_quant", axisPts.getInputQuantitiy());
	}

	@Test
	void testDeposit() {
		assertEquals("axis_pts_deposit", axisPts.getDeposit());
	}

	@Test
	void testMaxDiff() {
		assertEquals(1.1, axisPts.getMaxDiff());
	}

	@Test
	void testConversion() {
		assertEquals("axis_pts_conv", axisPts.getConversion());
	}

	@Test
	void testMaxAxisPts() {
		assertEquals(123l, axisPts.getMaxAxisPoints());
	}

	@Test
	void testLowerLimit() {
		assertEquals(1.2, axisPts.getLowerLimit());
	}

	@Test
	void testUpperLimit() {
		assertEquals(1.3, axisPts.getUpperLimit());
	}

	@Test
	void testByteOrder() {
		assertEquals(ByteOrder.MSB_FIRST, axisPts.getByteorder());
	}

	@Test
	void testCalibrationAccess() {
		assertEquals(CalibrationAccess.NOT_IN_MCD_SYSTEM, axisPts.getAccess());
	}

	@Test
	void testAxisPtsDeposit() {
		assertEquals(Deposit.DIFFERENCE, axisPts.getAxisPointDeposit());
	}

	@Test
	void testDisplayIdentifier() {
		assertEquals("display.identifier.wohoo", axisPts.getDisplayIdentifier());
	}

	@Test
	void testEcuAddressExtension() {
		assertEquals(0x9783, axisPts.getEcuAddressExtension());
	}

	@Test
	void testFormat() {
		assertEquals("%1212.21", axisPts.getFormat());
	}

	@Test
	void testGuardRails() {
		assertEquals(false, axisPts.isGuardRails());
	}

	@Test
	void testMonotony() {
		assertEquals(Monotony.NOT_MON, axisPts.getMonotony());
	}

	@Test
	void testPhysUnit() {
		assertEquals("m/(s*s)", axisPts.getPhysUnit());
	}

	@Test
	void testReadOnly() {
		assertEquals(false, axisPts.isReadOnly());
	}

	@Test
	void testRefMemorySegment() {
		assertEquals("memorySegment.42", axisPts.getMemorySegment());
	}

	@Test
	void testStepSize() {
		assertEquals(820.028, axisPts.getStepSize());
	}

	@Test
	void testSymbolLink() {
		assertEquals("symbol name char 99", axisPts.getSymbolLink().getSymbolName());
		assertEquals(99l, axisPts.getSymbolLink().getOffset());
	}

	@Test
	public void equalsContract() {
		EqualsVerifierConfigured.getEqualsVerifier().forClass(AxisPts.class).verify();
	}
}
