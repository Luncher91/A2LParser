package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class CalibrationHandleTest {
	private Asap2File file;
	private CalibrationHandle calibrationHandle;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		calibrationHandle = file.getProject().getModules().get(0).getModPar().getCalibrationMethods().get(0)
				.getCalibrationHandle().get(0);

		assertNotNull(calibrationHandle);
	}

	@Test
	void testHandleValues() {
		long[] values = new long[] { 0x10000, 0x300, 0x7, 0x700000, 0x40000 };
		List<Long> vs = calibrationHandle.getHandles();

		assertEquals(values.length, vs.size());

		for (int i = 0; i < vs.size(); i++) {
			assertEquals(values[i], vs.get(i));
		}
	}

	@Test
	void testCalibrationText() {
		assertEquals("important!", calibrationHandle.getCalibrationText());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(CalibrationHandle.class).verify();
	}
}
