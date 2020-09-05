package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.enums.ConversionType;

public class CompuMethodTest {
	private Asap2File file;
	private CompuMethod compuMethod;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFileA();
		compuMethod = file.getProject().getModules().get(0).getCompuMethods().get(0);
		
		assertNotNull(compuMethod);
	}
	
	@Test
	void testName() {
		assertEquals("THE.IDENTITY", compuMethod.getName());
	}
	
	@Test
	void testLongIdentifier() {
		assertEquals("very unique", compuMethod.getLongIdentifier());
	}
	
	@Test
	void testConversionType() {
		assertEquals(ConversionType.RAT_FUNC, compuMethod.getConversionType());
	}
	
	@Test
	void testFormat() {
		assertEquals("%8.4", compuMethod.getFormat());
	}
	
	@Test
	void testUnit() {
		assertEquals("°", compuMethod.getUnit());
	}
}
