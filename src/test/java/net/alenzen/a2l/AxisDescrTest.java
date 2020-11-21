package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

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
}
