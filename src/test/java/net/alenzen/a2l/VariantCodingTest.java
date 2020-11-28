package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.VariantCoding.VarNaming;

public class VariantCodingTest {
	private Asap2File file;
	private VariantCoding variantCoding;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		variantCoding = file.getProject().getModules().get(0).getVariantCoding();

		assertNotNull(variantCoding);
	}
	
	@Test
	void testVarSeparator() {
		assertEquals("#", variantCoding.getVarSeparator());
	}
	
	@Test
	void testVarNaming() {
		assertEquals(VarNaming.NUMERIC, variantCoding.getVarNaming());
	}
}
