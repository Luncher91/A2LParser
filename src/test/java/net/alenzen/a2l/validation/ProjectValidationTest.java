package net.alenzen.a2l.validation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2File;
import net.alenzen.a2l.Asap2FileTest;
import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.Project;

public class ProjectValidationTest {
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
		Asap2ValidationTestHelper.asap2IdentValidation(file, () -> project.getName(), s -> project.setName(s));
	}

	@Test
	void testLongIdentifier() {
		Asap2ValidationTestHelper.asap2StringValidation(file, () -> project.getLongIdentifier(),
				s -> project.setLongIdentifier(s));
	}
}
