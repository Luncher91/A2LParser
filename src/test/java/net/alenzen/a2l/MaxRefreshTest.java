package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

class MaxRefreshTest {
	private Asap2File file;
	private MaxRefresh maxRefresh;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		maxRefresh = file.getProject().getModules().get(0).getMeasurements().get(0).getMaxRefresh();

		assertNotNull(maxRefresh);
	}

	@Test
	void testRate() {
		assertEquals(42, maxRefresh.getRate());
	}

	@Test
	void testScalingUnit() {
		assertEquals(12, maxRefresh.getScalingUnit());
	}
}
