package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProjectTest {
	private Asap2File file;
	private Project project;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFileA();
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
