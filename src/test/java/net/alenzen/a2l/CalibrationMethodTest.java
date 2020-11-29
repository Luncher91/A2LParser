package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class CalibrationMethodTest {
	private Asap2File file;
	private CalibrationMethod calibrationMethod;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		calibrationMethod = file.getProject().getModules().get(0).getModPar().getCalibrationMethods().get(0);

		assertNotNull(calibrationMethod);
	}
	
	@Test
	void testMethod() {
		assertEquals("methodA", calibrationMethod.getName());
	}
	
	@Test
	void testVersion() {
		assertEquals(3141, calibrationMethod.getVersion());
	}
}
