package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.Deposit;

public class ModCommonTest {
	private Asap2File file;
	private ModCommon modCommon;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		modCommon = file.getProject().getModules().get(0).getModCommon();
		
		assertNotNull(modCommon);
	}
	
	@Test
	void testComment() {
		assertEquals("", modCommon.getComment());
	}
	
	@Test
	void testDataSize() {
		assertEquals(128L, modCommon.getDataSize());
	}
	
	@Test
	void testStandardRecordLayout() {
		assertNull(modCommon.getStandardRecordLayout());
	}
	
	@Test
	void testDeposit() {
		assertEquals(Deposit.DIFFERENCE, modCommon.getDeposit());
	}
	
	@Test
	void testByteOrder() {
		assertEquals(ByteOrder.MSB_FIRST, modCommon.getByteorder());
	}
	
	@Test
	void testAlignmentByte() {
		assertEquals(2L, modCommon.getAlignmentByte());
	}
	
	@Test
	void testAlignmentWord() {
		assertEquals(1L, modCommon.getAlignmentWord());
	}
	
	@Test
	void testAlignmentLong() {
		assertEquals(3L, modCommon.getAlignmentLong());
	}
	
	@Test
	void testAlignmentFloat32() {
		assertEquals(8L, modCommon.getAlignmentFloat32IEEE());
	}
	
	@Test
	void testAlignmentFloat64() {
		assertEquals(8L, modCommon.getAlignmentFloat64IEEE());
	}
}
