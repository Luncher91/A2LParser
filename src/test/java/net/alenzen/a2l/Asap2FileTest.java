package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Asap2FileTest {
	public enum TestFile {
		A("freeTest.a2l");

		private String filename;
		
		TestFile(String fName) {
			setFilename(fName);
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}
	}
	
	public static Asap2File getTestFile(TestFile tf) throws IOException {
		Asap2Parser parser = new Asap2Parser(ClassLoader.getSystemResourceAsStream(tf.getFilename()));
		return parser.parse();
	}
	
	@Test
	void testAsap2Version() throws IOException {
		Asap2File f = getTestFile(TestFile.A);
		
		assertEquals(1L, f.getAsap2Version().getVersionNo());
		assertEquals(71L, f.getAsap2Version().getUpgradeNo());
	}
}
