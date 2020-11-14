package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class VirtualTest {
	private Asap2File file;
	private Virtual virtual;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		virtual = file.getProject().getModules().get(0).getMeasurements().get(0).getVirtual();

		assertNotNull(virtual);
	}

	@Test
	void testMeasurementReferences() {
		String[] meas = new String[] { "meas_1", "meas_2" };
		for (int i = 0; i < virtual.size(); i++) {
			assertEquals(meas[i], virtual.get(i));
		}
	}
}
