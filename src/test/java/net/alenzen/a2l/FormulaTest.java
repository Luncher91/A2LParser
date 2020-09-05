package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FormulaTest {
	private Asap2File file;
	private Formula formula;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFileA();
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
}
