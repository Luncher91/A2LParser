package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BitOperationTest {
	private Asap2File file;
	private BitOperation bitOperation;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFileA();
		bitOperation = file.getProject().getModules().get(0).getMeasurements().get(0).getBitOperation();

		assertNotNull(bitOperation);
	}
	
	@Test
	void testLeftShift() {
		assertNull(bitOperation.getLeftShift());
	}
	
	@Test
	void testRightShift() {
		assertEquals(4, bitOperation.getRightShift());
	}
	
	@Test
	void testSignExtend() {
		assertTrue(bitOperation.isSignExtend());
	}
}
