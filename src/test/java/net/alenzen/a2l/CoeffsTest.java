package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class CoeffsTest {
	private Asap2File file;
	private Coeffs coeffs;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		coeffs = file.getProject().getModules().get(0).getCompuMethods().get(0).getCoeffs();
		
		assertNotNull(coeffs);
	}
	
	@Test
	void testA() {
		assertEquals(0.0, coeffs.getA());
	}
	
	@Test
	void testB() {
		assertEquals(1.1, coeffs.getB());
	}
	
	@Test
	void testC() {
		assertEquals(0.0, coeffs.getC());
	}
	
	@Test
	void testD() {
		assertEquals(0.0, coeffs.getD());
	}
	
	@Test
	void testE() {
		assertEquals(0.0, coeffs.getE());
	}
	
	@Test
	void testF() {
		assertEquals(1.1, coeffs.getF());
	}
}
