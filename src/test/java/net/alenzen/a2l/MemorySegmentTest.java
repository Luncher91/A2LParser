package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class MemorySegmentTest {
	private Asap2File file;
	private MemorySegment memSegment;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		memSegment = file.getProject().getModules().get(0).getModPar().getMemorySegments().get(0);

		assertNotNull(memSegment);
	}

	@Test
	void testName() {
		assertEquals("mem1", memSegment.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("lots of code in internal flash", memSegment.getLongIdentifier());
	}

	@Test
	void testPrgType() {
		assertEquals(MemorySegment.PrgType.CODE, memSegment.getPrgType());
	}

	@Test
	void testMemoryType() {
		assertEquals(MemorySegment.MemoryType.FLASH, memSegment.getMemoryType());
	}

	@Test
	void testAttribute() {
		assertEquals(MemorySegment.Attribute.INTERN, memSegment.getAttribute());
	}

	@Test
	void testAddress() {
		assertEquals(0x42, memSegment.getAddress());
	}

	@Test
	void testSize() {
		assertEquals(4096, memSegment.getSize());
	}

	@Test
	void testOffset() {
		long[] offsets = new long[] { -1, -1, -1, -1, -1 };
		long[] actualOffsets = memSegment.getOffset();

		assertEquals(offsets.length, actualOffsets.length);

		for (int i = 0; i < actualOffsets.length; i++) {
			assertEquals(offsets[i], actualOffsets[i]);
		}
	}
}
