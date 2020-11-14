package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class ExtendedLimitsTest {
	private Asap2File file;
	private ExtendedLimits extendedLimits;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		extendedLimits = file.getProject().getModules().get(0).getAxisPts().get(0).getExtendedLimits();

		assertNotNull(extendedLimits);
	}

	@Test
	void testLowerLimit() {
		assertEquals(0.1, extendedLimits.getLowerLimit());
	}

	@Test
	void testUpperLimit() {
		assertEquals(0.5, extendedLimits.getUpperLimit());
	}
}
