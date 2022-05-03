package net.alenzen.a2l.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2File;
import net.alenzen.a2l.Asap2FileTest;
import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.Formula;
import net.alenzen.a2l.IAsap2TreeElement;

public class FormulaValidatorTest {
	@Test
	void testValidate() throws IOException {
		Asap2File f = Asap2FileTest.getTestFile(TestFile.A);

		Asap2Validation validator = new Asap2Validation(f);
		validator.getValidators().add(new FormulaValidator());
		List<Asap2ValidationError> valResult = validator.validate();
		assertEquals(1, valResult.size());
		for (Asap2ValidationError err : valResult) {
			FormulaValidationError formErr = (FormulaValidationError) err;
			assertNotNull(formErr.getFormula());
			assertTrue(formErr.getFxErrors().isEmpty());
			assertTrue(formErr.getGxErrors().isEmpty());
		}
	}

	@Test
	void testValidate_brokenFormula() throws IOException {
		Asap2File f = Asap2FileTest.getTestFile(TestFile.A);
		for (IAsap2TreeElement form : f) {
			if (form instanceof Formula) {
				Formula formula = (Formula) form;
				formula.setFx("$(PI-89");
			}
		}

		Asap2Validation validator = new Asap2Validation(f);
		validator.getValidators().add(new FormulaValidator());
		List<Asap2ValidationError> valResult = validator.validate();
		assertEquals(1, valResult.size());
		for (Asap2ValidationError err : valResult) {
			FormulaValidationError formErr = (FormulaValidationError) err;
			assertNotNull(formErr.getFormula());
			assertFalse(formErr.getFxErrors().isEmpty());
			assertTrue(formErr.getGxErrors().isEmpty());
		}
	}
}
