package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class ProjectTest {
	private Asap2File file;
	private Project project;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		project = file.getProject();
		
		assertNotNull(project);
	}
	
	@Test
	void testName() {
		assertEquals("Free_Example", project.getName());
	}
	
	@Test
	void testLongIdentifier() {
		assertEquals("MIT licensed example file", project.getLongIdentifier());
	}
}
