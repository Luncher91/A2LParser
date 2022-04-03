package net.alenzen.a2l.validation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

		FormulaValidator validator = new FormulaValidator(f);
		List<FormulaValidationError> valResult = validator.validate();
		assertEquals(1, valResult.size());
		for (FormulaValidationError err : valResult) {
			assertNotNull(err.getFormula());
			assertTrue(err.getFxErrors().isEmpty());
			assertTrue(err.getGxErrors().isEmpty());
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

		FormulaValidator validator = new FormulaValidator(f);
		List<FormulaValidationError> valResult = validator.validate();
		assertEquals(1, valResult.size());
		for (FormulaValidationError err : valResult) {
			assertNotNull(err.getFormula());
			assertFalse(err.getFxErrors().isEmpty());
			assertTrue(err.getGxErrors().isEmpty());
		}
	}
}
