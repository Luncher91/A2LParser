package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.AxisDescr.Attribute;
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
		assertEquals("superMeasurement", axisDescr.getInputQuantity());
	}

	@Test
	void testConversion() {
		assertEquals("char_conv", axisDescr.getConversion());
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
		assertEquals("axis_pts_1", axisDescr.getAxisPoints_ref());
	}

	@Test
	void testByteOrder() {
		assertEquals(ByteOrder.MSB_FIRST, axisDescr.getByteorder());
	}

	@Test
	void testCurveAxisRef() {
		assertEquals("char_b", axisDescr.getCurveAxis_ref());
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
	void testInputQuantityMeasurementReference() {
		file.updateReferences();
		assertNotNull(axisDescr.getInputQuantityMeasurement());
		assertEquals(axisDescr.getInputQuantity(), axisDescr.getInputQuantityMeasurement().getName());
	}

	@Test
	void testConversionCompuMethodReference() {
		file.updateReferences();
		assertNotNull(axisDescr.getConversionCompuMethod());
		assertEquals(axisDescr.getConversion(), axisDescr.getConversionCompuMethod().getName());
	}

	@Test
	void testAxisPointsAxisPtsReference() {
		file.updateReferences();
		assertNotNull(axisDescr.getAxisPointsAxisPts());
		assertEquals(axisDescr.getAxisPoints_ref(), axisDescr.getAxisPointsAxisPts().getName());
	}

	@Test
	void testCurveAxisCharacteristicReference() {
		file.updateReferences();
		assertNotNull(axisDescr.getCurveAxisCharacteristic());
		assertEquals(axisDescr.getCurveAxis_ref(), axisDescr.getCurveAxisCharacteristic().getName());
	}

	@Test
	public void equalsContract() {
		EqualsVerifierConfigured.getEqualsVerifier().forClass(AxisDescr.class)
				.withIgnoredFields("conversionCompuMethod", "inputQuantityMeasurement", "axisPointsAxisPts",
						"curveAxisCharacteristic")
				.verify();
	}

	public static AxisDescr getPrefabRed() {
		AxisDescr a = new AxisDescr();
		a.setAttribute(Attribute.COM_AXIS);
		a.setInputQuantity("RedInputQuantity");
		a.setConversion("RedAxisDescrConversion");
		a.setMaxAxisPoints(42);
		a.setLowerLimit(0.0);
		a.setUpperLimit(1.0);
		return a;
	}

	public static AxisDescr getPrefabBlue() {
		AxisDescr a = new AxisDescr();
		a.setAttribute(Attribute.CURVE_AXIS);
		a.setInputQuantity("BlueInputQuantity");
		a.setConversion("BlueAxisDescrConversion");
		a.setMaxAxisPoints(43);
		a.setLowerLimit(0.1);
		a.setUpperLimit(1.1);
		return a;
	}
}
