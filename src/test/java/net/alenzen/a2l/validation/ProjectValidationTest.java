package net.alenzen.a2l.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

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
	void testName_invalid() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testIdentValidation(file, s -> project.setName(s),
				"hello world");
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testName_invalid_longName() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testIdentValidation(file, s -> project.setName(s),
				Asap2ValidationTestHelper.IDENT_1025_CHARACTERS);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testName_invalid_longPartialName() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testIdentValidation(file, s -> project.setName(s),
				Asap2ValidationTestHelper.IDENT_129_CHARACTERS_PARTIAL);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testName_valid() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testIdentValidation(file, s -> project.setName(s),
				"hello_world");
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

	@Test
	void testName_invalid_null() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testIdentValidation(file, s -> project.setName(s),
				null);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testLongIdentifier_valid() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testStringValidation(file,
				s -> project.setLongIdentifier(s));
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

	@Test
	void testLongIdentifier_invalid() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testStringValidation(file,
				s -> project.setLongIdentifier(s), Asap2ValidationTestHelper.STRING_256_CHARACTERS);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testLongIdentifier_invalid_null() {
		List<Asap2ValidationError> errors = Asap2ValidationTestHelper.testStringValidation(file,
				s -> project.setLongIdentifier(s), null);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}
}
