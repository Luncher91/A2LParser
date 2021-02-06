package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class ProjectIncludedTest {
	private Asap2File file;
	private Project project;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.B);
		project = file.getProject();

		assertNotNull(project);
	}
	
	@Test
	void testIncluded() {
		assertEquals(1, project.getIncluded().size());
	}
	
	@Test
	void testIncludedModuleSize() {
		assertEquals(1, project.getIncluded().get(0).getModules().size());
	}
	
	@Test
	void testIncludedModuleName() {
		assertEquals("sample_module_included", project.getIncluded().get(0).getModules().get(0).getName());
	}
}
