package net.alenzen.a2l.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.A2LWriter;
import net.alenzen.a2l.Asap2File;
import net.alenzen.a2l.Asap2FileTest;
import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.Project;

public class ProjectValidationTest {
	private static final String IDENT_1025_CHARACTERS = "helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.hi";
	private static final String IDENT_129_CHARACTERS_PARTIAL = "helloWorld.helloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorl.end";
	private static final String STRING_256_CHARACTERS = "I am a 254 characters long string which is only 256 long when my special characters are escaped \"correctly\". When the special characters are not escaped I would only be 254 characters long. This is the very last sentence before I quit this special test!!";
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
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2IdentValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		project.setName("hello world");
		errors = sv.validate();
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testName_invalid_longName() {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2IdentValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		project.setName(IDENT_1025_CHARACTERS);
		errors = sv.validate();
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testName_invalid_longPartialName() {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2IdentValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		project.setName(IDENT_129_CHARACTERS_PARTIAL);
		errors = sv.validate();
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testName_valid() {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2IdentValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		project.setName("hello_wold");
		errors = sv.validate();
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

	@Test
	void testName_invalid_null() {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2IdentValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		project.setName(null);
		errors = sv.validate();
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testLongIdentifier_valid() {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2StringValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		project.setLongIdentifier("I am not very long");
		errors = sv.validate();
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

	@Test
	void testLongIdentifier_invalid() {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2StringValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		assumeTrue(STRING_256_CHARACTERS.length() == 254, "The Java string might be shorter than the limit allows.");
		assumeTrue(A2LWriter.toA2LString(STRING_256_CHARACTERS).length() == 258,
				"The final written String is encapsuled by doubled quotation marks and the double quotation marks are escaped.");
		project.setLongIdentifier(STRING_256_CHARACTERS);
		errors = sv.validate();
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	@Test
	void testLongIdentifier_invalid_null() {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(new Asap2StringValidator());
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		project.setLongIdentifier(null);
		errors = sv.validate();
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}
}
