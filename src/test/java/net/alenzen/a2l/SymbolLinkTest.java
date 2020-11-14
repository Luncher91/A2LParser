package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

class SymbolLinkTest {
	private Asap2File file;
	private SymbolLink symbolLink;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		symbolLink = file.getProject().getModules().get(0).getMeasurements().get(0).getSymbolLink();

		assertNotNull(symbolLink);
	}

	@Test
	void testName() {
		assertEquals("symbol name", symbolLink.getSymbolName());
	}

	@Test
	void testOffset() {
		assertEquals(84, symbolLink.getOffset());
	}
}
