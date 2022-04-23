package net.alenzen.a2l.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import net.alenzen.a2l.Asap2File;

public class Asap2ValidationTestHelper {

	private static final String INVALID_IDENT_VALUE = "hello world";
	static final String IDENT_1025_CHARACTERS = "helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.helloworld.hi";
	static final String IDENT_129_CHARACTERS_PARTIAL = "helloWorld.helloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorldhelloWorl.end";
	/**
	 * assumeTrue(Asap2ValidationTestHelper.STRING_256_CHARACTERS.length() == 254,
	 * "The Java string might be shorter than the limit allows.");
	 * assumeTrue(A2LWriter.toA2LString(Asap2ValidationTestHelper.STRING_256_CHARACTERS).length()
	 * == 258, "The final written String is encapsuled by doubled quotation marks
	 * and the double quotation marks are escaped.");
	 */
	static final String STRING_256_CHARACTERS = "I am a 254 characters long string which is only 256 long when my special characters are escaped \"correctly\". When the special characters are not escaped I would only be 254 characters long. This is the very last sentence before I quit this special test!!";
	static final String VALID_STRING_VALUE = "Hello World";
	private static final String VALID_IDENT_VALUE = "hello_world";

	public static List<Asap2ValidationError> testStringValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue, String testValue) {
		return testValidation(file, getFieldValue, setFieldValue, testValue, new Asap2StringValidator());
	}

	public static void asap2NullableStringValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		asap2StringBasicValidation(file, getFieldValue, setFieldValue);
		asap2StringValidationValidNull(file, getFieldValue, setFieldValue);
	}

	private static void asap2StringValidationValidNull(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testStringValidation(file, getFieldValue, setFieldValue, null);
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

	private static void asap2StringBasicValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		asap2StringValidationValid(file, getFieldValue, setFieldValue);
		asap2StringValidationInvalid(file, getFieldValue, setFieldValue);
	}

	public static List<Asap2ValidationError> testIdentValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue, String testValue) {
		return testValidation(file, getFieldValue, setFieldValue, testValue, new Asap2IdentValidator());
	}

	private static List<Asap2ValidationError> testValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue, String testValue, Asap2Validator object) {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(object);
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		String valueBefore = getFieldValue.get();
		setFieldValue.accept(testValue);
		errors = sv.validate();
		setFieldValue.accept(valueBefore);
		return errors;
	}

	public static void asap2NullableIdentValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		asap2IdentBasicValidation(file, getFieldValue, setFieldValue);
		asap2IdentValidationValidNull(file, getFieldValue, setFieldValue);
	}

	private static void asap2IdentBasicValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		asap2IdentValidationValid(file, getFieldValue, setFieldValue);
		asap2IdentValidationInvalid(file, getFieldValue, setFieldValue);
		asap2IdentValidationInvalidLongName(file, getFieldValue, setFieldValue);
		asap2IdentValidationInvalidLongPartialName(file, getFieldValue, setFieldValue);
	}

	public static void asap2IdentValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		asap2IdentBasicValidation(file, getFieldValue, setFieldValue);
		asap2IdentValidationInvalidNull(file, getFieldValue, setFieldValue);
	}

	private static void asap2IdentValidationInvalidLongPartialName(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testIdentValidation(file, getFieldValue, setFieldValue,
				IDENT_129_CHARACTERS_PARTIAL);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	private static void asap2IdentValidationInvalidLongName(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testIdentValidation(file, getFieldValue, setFieldValue,
				IDENT_1025_CHARACTERS);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	private static void asap2IdentValidationInvalidNull(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testIdentValidation(file, getFieldValue, setFieldValue, null);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	private static void asap2IdentValidationValidNull(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testIdentValidation(file, getFieldValue, setFieldValue, null);
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

	private static void asap2IdentValidationInvalid(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testIdentValidation(file, getFieldValue, setFieldValue,
				INVALID_IDENT_VALUE);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	private static void asap2IdentValidationValid(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testIdentValidation(file, getFieldValue, setFieldValue, VALID_IDENT_VALUE);
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

	public static void asap2StringValidation(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		asap2StringBasicValidation(file, getFieldValue, setFieldValue);
		asap2StringValidationInvalidNull(file, getFieldValue, setFieldValue);
	}

	private static void asap2StringValidationInvalidNull(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testStringValidation(file, getFieldValue, setFieldValue, null);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	private static void asap2StringValidationInvalid(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testStringValidation(file, getFieldValue, setFieldValue,
				STRING_256_CHARACTERS);
		assertEquals(1, errors.size(), "Exactly one error should be logged.");
	}

	private static void asap2StringValidationValid(Asap2File file, Supplier<String> getFieldValue,
			Consumer<String> setFieldValue) {
		List<Asap2ValidationError> errors = testStringValidation(file, getFieldValue, setFieldValue,
				VALID_STRING_VALUE);
		assertTrue(errors.isEmpty(), "No error should be logged");
	}

}