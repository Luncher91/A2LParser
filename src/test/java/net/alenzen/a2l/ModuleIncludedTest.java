package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class ModuleIncludedTest {
	private Asap2File file;
	private Module module;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.B);
		module = file.getProject().getModules().get(0);

		assertNotNull(module);
	}
	
	@Test
	void testIncluded() {
		assertEquals(1, module.getIncluded().size());
	}
	
	@Test
	void testIncludedCharacteristic() {
		assertEquals("inclChar", module.getIncluded().get(0).getCharacteristics().get(0).getName());
	}
}
