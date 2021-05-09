package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class DecimalParsingTest {
	private Asap2File file;
	private List<Characteristic> characteristics;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		characteristics = file.getProject().getModules().get(0).getCharacteristics();

		assertNotNull(characteristics);
	}

	@Test
	void testMissingTrailingZero() {
		// 1.
		assertEquals(1.0, characteristics.get(1).getUpperLimit());
	}

	@Test
	void testMissingLeadingZero() {
		// .1
		assertEquals(0.1, characteristics.get(1).getLowerLimit());
	}

	@Test
	void testMissingTrailingZeroExpontents() {
		// 1.E2
		assertEquals(100.0, characteristics.get(2).getUpperLimit());
	}

	@Test
	void testMissingLeadingZeroExpontents() {
		// .1E-2
		assertEquals(0.001, characteristics.get(2).getLowerLimit());
	}
}
