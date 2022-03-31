package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class FormulaTest {
	private Asap2File file;
	private Formula formula;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		formula = file.getProject().getModules().get(0).getCompuMethods().get(1).getFormula();

		assertNotNull(formula);
	}

	@Test
	void testFx() {
		assertEquals("X1-42", formula.getFx());
	}

	@Test
	void testGx() {
		assertEquals("X1+42", formula.getGx());
	}

	@Test
	void testValidate() {
		Formula f = createFormula();

		List<FormulaSyntaxError> errorsFx = f.validateFx();
		assertTrue(errorsFx.isEmpty());

		List<FormulaSyntaxError> errorsGx = f.validateGx();
		assertTrue(errorsGx.isEmpty());
	}

	private Formula createFormula() {
		Formula f = new Formula();
		f.setFx("1+2+3+4+X1");
		f.setGx("X1-(1+2+3+4)");
		return f;
	}

	@Test
	void testValidateError() {
		Formula f = new Formula();
		f.setFx("1+2#+3+4+X1");

		List<FormulaSyntaxError> errorsFx = f.validateFx();
		assertEquals(1, errorsFx.size());

		FormulaSyntaxError error = errorsFx.get(0);
		assertEquals(1, error.getLine());
		assertEquals(3, error.getCharPosition());
		assertFalse(error.getMessage().isEmpty());
	}
	
	@Test
	void testCalculateFx() {
		Formula f = new Formula();
		f.setFx("3*X1+$(G_OFFSET)");
		
		Map<String, Double> variables = new HashMap<String, Double>();
		variables.put("X1", 8.1);
		variables.put("G_OFFSET", 10.0);
		
		double result = f.calculateFx(variables);
		assertEquals(3*8.1+10.0, result);
	}
	
	@Test
	void testCalculateGx() {
		Formula f = new Formula();
		f.setGx("3/X1-$(G_OFFSET)");
		
		Map<String, Double> variables = new HashMap<String, Double>();
		variables.put("X1", 8.1);
		variables.put("G_OFFSET", 10.0);
		
		double result = f.calculateGx(variables);
		assertEquals(3.0/8.1-10.0, result);
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(Formula.class).verify();
	}
}
