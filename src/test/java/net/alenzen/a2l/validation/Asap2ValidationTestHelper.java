package net.alenzen.a2l.validation;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.util.List;
import java.util.function.Consumer;

import net.alenzen.a2l.Asap2File;

public class Asap2ValidationTestHelper {

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

	public static List<Asap2ValidationError> testStringValidation(Asap2File file, Consumer<String> setFieldValue,
			String testValue) {
		return testValidation(file, setFieldValue, testValue, new Asap2StringValidator());
	}

	private static List<Asap2ValidationError> testValidation(Asap2File file, Consumer<String> setFieldValue,
			String testValue, Asap2Validator object) {
		Asap2Validation sv = new Asap2Validation(file);
		sv.getValidators().add(object);
		List<Asap2ValidationError> errors = sv.validate();
		assumeTrue(errors.isEmpty(),
				"The given A2L file already has some issues. For the test we need a valid A2L file.");
		setFieldValue.accept(testValue);
		errors = sv.validate();
		return errors;
	}

	public static List<Asap2ValidationError> testStringValidation(Asap2File file, Consumer<String> setFieldValue) {
		return testStringValidation(file, setFieldValue, VALID_STRING_VALUE);
	}

	public static List<Asap2ValidationError> testIdentValidation(Asap2File file, Consumer<String> setFieldValue,
			String testValue) {
		return testValidation(file, setFieldValue, testValue, new Asap2IdentValidator());
	}

}
