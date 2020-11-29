package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class FixAxisParTest {
	private Asap2File file;
	private FixAxisPar fixAxisPar;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		fixAxisPar = file.getProject().getModules().get(0).getCharacteristics().get(0).getAxisDescriptions().get(0)
				.getFixAxisPar();

		assertNotNull(fixAxisPar);
	}

	@Test
	void testOffset() {
		assertEquals(2, fixAxisPar.getOffset());
	}

	@Test
	void testShift() {
		assertEquals(4, fixAxisPar.getShift());
	}

	@Test
	void testNumberapo() {
		assertEquals(8, fixAxisPar.getNumberapo());
	}
}
