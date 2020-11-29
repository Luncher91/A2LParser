package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class CoeffsLinearTest {
	private Asap2File file;
	private CoeffsLinear coeffsLinear;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		coeffsLinear = file.getProject().getModules().get(0).getCompuMethods().get(0).getCoeffsLienar();

		assertNotNull(coeffsLinear);
	}
	

	@Test
	void testA() {
		assertEquals(7.1, coeffsLinear.getA());
	}
	
	@Test
	void testB() {
		assertEquals(-12.5, coeffsLinear.getB());
	}
}
