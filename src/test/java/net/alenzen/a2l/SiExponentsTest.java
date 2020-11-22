package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class SiExponentsTest {
	private Asap2File file;
	private SiExponents siExponents;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		siExponents = file.getProject().getModules().get(0).getUnits().get(0).getSiExponents();

		assertNotNull(siExponents);
	}
	
	@Test
	void testLength() {
		assertEquals(5, siExponents.getLength());
	}
	
	@Test
	void testMass() {
		assertEquals(2, siExponents.getMass());
	}
	
	@Test
	void testTime() {
		assertEquals(7, siExponents.getTime());
	}
	
	@Test
	void testElectricCurrent() {
		assertEquals(10, siExponents.getElectricCurrent());
	}
	
	@Test
	void testTemperature() {
		assertEquals(12, siExponents.getTemperature());
	}
	
	@Test
	void testAmountOfSubstance() {
		assertEquals(14, siExponents.getAmountOfSubstance());
	}
	
	@Test
	void testLuminousIntensity() {
		assertEquals(16, siExponents.getLuminousIntensity());
	}
}
