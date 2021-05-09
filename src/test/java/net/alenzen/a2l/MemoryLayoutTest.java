package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class MemoryLayoutTest {
	private Asap2File file;
	private MemoryLayout memoryLayout;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		memoryLayout = file.getProject().getModules().get(0).getModPar().getMemoryLayouts().get(0);

		assertNotNull(memoryLayout);
	}

	@Test
	void testPrgType() {
		assertEquals(MemoryLayout.PrgType.PRG_CODE, memoryLayout.getPrgType());
	}

	@Test
	void testAddress() {
		assertEquals(0x0010, memoryLayout.getAddress());
	}

	@Test
	void testSize() {
		assertEquals(4096, memoryLayout.getSize());
	}

	@Test
	void testOffsets() {
		long[] offsets = new long[] { 5, 4, 3, 2, 1 };

		assertEquals(5, memoryLayout.getOffset().length);

		for (int i = 0; i < memoryLayout.getOffset().length; i++) {
			assertEquals(offsets[i], memoryLayout.getOffset()[i]);
		}
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(MemoryLayout.class).verify();
	}
}
