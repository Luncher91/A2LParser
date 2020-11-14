package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class ModuleTest {
	private Asap2File file;
	private Module module;
	private List<Module> modules;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		modules = file.getProject().getModules();
		
		assertEquals(1, modules.size());
		
		module = modules.get(0);
		
		assertNotNull(module);
	}
	
	@Test
	void testName() {
		assertEquals("sample_module", module.getName());
	}
	
	@Test
	void testLongIdentifier() {
		assertEquals("great example with different escaped quotation \" marks like double quotes \" and backslash escaped at the end \"", module.getLongIdentifier());
	}
}
