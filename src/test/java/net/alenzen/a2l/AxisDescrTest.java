package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.Deposit;
import net.alenzen.a2l.enums.Monotony;

public class AxisDescrTest {
	private Asap2File file;
	private AxisDescr axisDescr;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		axisDescr = file.getProject().getModules().get(0).getCharacteristics().get(0).getAxisDescriptions().get(0);

		assertNotNull(axisDescr);
	}

	@Test
	void testAttribute() {
		assertEquals(AxisDescr.Attribute.CURVE_AXIS, axisDescr.getAttribute());
	}

	@Test
	void testInputQuantity() {
		assertEquals("in_quant_char", axisDescr.getInputQuantity());
	}

	@Test
	void testConversion() {
		assertEquals("char_axis_descr_conv", axisDescr.getConversion());
	}

	@Test
	void testMaxAxisPoints() {
		assertEquals(22, axisDescr.getMaxAxisPoints());
	}

	@Test
	void testLowerLimit() {
		assertEquals(5.5, axisDescr.getLowerLimit());
	}

	@Test
	void testUpperLimit() {
		assertEquals(21.21, axisDescr.getUpperLimit());
	}

	@Test
	void testAxisPtsRef() {
		assertEquals("axisPtsA", axisDescr.getAxisPoints_ref());
	}

	@Test
	void testByteOrder() {
		assertEquals(ByteOrder.MSB_FIRST, axisDescr.getByteorder());
	}

	@Test
	void testCurveAxisRef() {
		assertEquals("curveAxisA", axisDescr.getCurveAxis_ref());
	}

	@Test
	void testDeposit() {
		assertEquals(Deposit.DIFFERENCE, axisDescr.getDeposit());
	}

	@Test
	void testFormat() {
		assertEquals("%3.3", axisDescr.getFormat());
	}

	@Test
	void testMaxGrad() {
		assertEquals(30.0, axisDescr.getMaxGrad());
	}

	@Test
	void testMonotony() {
		assertEquals(Monotony.NOT_MON, axisDescr.getMonotony());
	}

	@Test
	void testPhysUnit() {
		assertEquals("m/(s*s)", axisDescr.getPhysUnit());
	}

	@Test
	void testReadOnly() {
		assertEquals(true, axisDescr.isReadOnly());
	}

	@Test
	void testStepSize() {
		assertEquals(1.2, axisDescr.getStepSize());
	}

	@Test
	public void equalsContract() {
		EqualsVerifierConfigured.getEqualsVerifier().forClass(AxisDescr.class).verify();
	}
}
