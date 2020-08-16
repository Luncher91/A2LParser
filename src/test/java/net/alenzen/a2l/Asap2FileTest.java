package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Asap2FileTest {		
	public static final String TestFile_A = "freeTest.a2l";
	
	public static Asap2File getTestFileA() throws IOException {
		Asap2Parser parser = new Asap2Parser(ClassLoader.getSystemResourceAsStream(TestFile_A));
		return parser.parse();
	}
	
	@Test
	void testAsap2Version() throws IOException {
		Asap2File f = getTestFileA();
		
		assertEquals(1L, f.getAsap2Version().getVersionNo());
		assertEquals(71L, f.getAsap2Version().getUpgradeNo());
	}
}
