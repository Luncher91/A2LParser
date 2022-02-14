package net.alenzen.a2l.formula;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import net.alenzen.a2l.FormulaParser;

public class FormulaParsingTest {
	@Test
	void testSimpleFormula() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 + 4");
		double result = f.calculate();
		assertEquals(7d, result);
	}

	@Test
	void testFormulaWithVariable() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 + 4 - X1");
		Set<String> variables = f.getVariables();

		assertNotNull(variables);
		assertEquals(1, variables.size());
		assertTrue(variables.contains("X1"));

		f.setVariable("X1", 12d);
		assertEquals(-5d, f.calculate());
	}

	@Test
	void testFormulaWithSysc() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("sysc(PI)*3");
		f.setVariable("PI", 3);
		assertEquals(9, f.calculate());
	}

	@Test
	void testFormulaWithSyscDollar() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("$(PI)*3");
		f.setVariable("PI", 3);
		assertEquals(9, f.calculate());
	}

	@Test
	void testFormulaWithBitwiseNot() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("~3");
		assertEquals(~3, f.calculate());
	}

	@Test
	void testFormulaWithNot() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("!1");
		assertEquals(0, f.calculate());
	}

	@Test
	void testFormulaWithExponent() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("exp(3)");
		assertEquals(Math.exp(3), f.calculate());
	}

	@Test
	void testFormulaWithSquareroot() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("sqrt(3)");
		assertEquals(Math.sqrt(3), f.calculate());
	}

	@Test
	void testFormulaWithAbs() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("abs(-3)");
		assertEquals(Math.abs(-3), f.calculate());
	}

	@Test
	void testFormulaWithSinus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("sin(3)");
		assertEquals(Math.sin(3d), f.calculate());
	}

	@Test
	void testFormulaWithCosinus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("cos(3)");
		assertEquals(Math.cos(3d), f.calculate());
	}

	@Test
	void testFormulaWithTangens() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("tan(3)");
		assertEquals(Math.tan(3d), f.calculate());
	}

	@Test
	void testFormulaWithArcsinus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("asin(3)");
		assertEquals(Math.asin(3d), f.calculate());
	}

	@Test
	void testFormulaWithArcosinus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("acos(3)");
		assertEquals(Math.acos(3d), f.calculate());
	}

	@Test
	void testFormulaWithArctangens() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("atan(3)");
		assertEquals(Math.atan(3d), f.calculate());
	}

	@Test
	void testFormulaWithHyperbolicSinus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("sinh(3)");
		assertEquals(Math.sinh(3d), f.calculate());
	}

	@Test
	void testFormulaWithhHyperbolicCosinus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("cosh(3)");
		assertEquals(Math.cosh(3d), f.calculate());
	}

	@Test
	void testFormulaWithHyperbolicTangens() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("tanh(3)");
		assertEquals(Math.tanh(3d), f.calculate());
	}

	@Test
	void testFormulaWithLog() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("log(3)");
		assertEquals(Math.log(3d), f.calculate());
	}

	@Test
	void testFormulaWithLog10() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("log10(3)");
		assertEquals(Math.log10(3d), f.calculate());
	}

	@Test
	void testFormulaWithPlus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 + 8");
		assertEquals(3d + 8d, f.calculate());
	}

	@Test
	void testFormulaWithMinus() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 - 8");
		assertEquals(3d - 8d, f.calculate());
	}

	@Test
	void testFormulaWithMultiply() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 * 8");
		assertEquals(3d * 8d, f.calculate());
	}

	@Test
	void testFormulaWithDivide() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 / 8");
		assertEquals(3d / 8d, f.calculate());
	}

	@Test
	void testFormulaWithBitwiseAnd() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 & 8");
		assertEquals(3 & 8, f.calculate());
	}

	@Test
	void testFormulaWithBitwiseOr() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 | 8");
		assertEquals(3 | 8, f.calculate());
	}

	@Test
	void testFormulaWithBitwiseXor() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 ^ 8");
		assertEquals(3 ^ 8, f.calculate());
	}

	@Test
	void testFormulaWithBitshiftLeft() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 << 8");
		assertEquals(3 << 8, f.calculate());
	}

	@Test
	void testFormulaWithBitshiftRight() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("3 >> 8");
		assertEquals(3 >> 8, f.calculate());
	}

	@Test
	void testFormulaWithAnd() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("1 && 0");
		assertEquals(false, f.calculate() != 0d);
	}

	@Test
	void testFormulaWithOr() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("1 || 0");
		assertEquals(true, f.calculate() != 0d);
	}

	@Test
	void testFormulaWithPow() throws Exception {
		FormulaParser f = FormulaParser.createFromFormula("pow(2, 10)");
		assertEquals(1024d, f.calculate());
	}
}
