package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class UnitConversionTest {
	private Asap2File file;
	private UnitConversion unitConversion;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		unitConversion = file.getProject().getModules().get(0).getUnits().get(0).getUnitConversion();

		assertNotNull(unitConversion);
	}
	
	@Test
	void testGradient() {
		assertEquals(10.1, unitConversion.getGradient());
	}
	
	@Test
	void testOffset() {
		assertEquals(2.2, unitConversion.getOffset());
	}
}
