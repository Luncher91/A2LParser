package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Asap2FileTest {
	// https://www.asam.net/standards/detail/mcd-2-mc/wiki/#Downloads		
	public static final String TestFile_A = "ASAP2_Demo_V171.a2l";
	// https://github.com/christoph2/pyA2L/blob/master/examples/example-a2l-file.a2l
	public static final String TestFile_B = "example-a2l-file.a2l";
	
	public static Asap2File getTestFileA() throws IOException {
		Asap2Parser parser = new Asap2Parser(ClassLoader.getSystemResourceAsStream(TestFile_A));
		return parser.parse();
	}
	
	public static Asap2File getTestFileB() throws IOException {
		Asap2Parser parser = new Asap2Parser(ClassLoader.getSystemResourceAsStream(TestFile_B));
		return parser.parse();
	}
	
	@Test
	void testAsap2Version() throws IOException {
		Asap2File f = getTestFileA();
		
		assertEquals(1L, f.getAsap2Version().getVersionNo());
		assertEquals(71L, f.getAsap2Version().getUpgradeNo());
	}
}
