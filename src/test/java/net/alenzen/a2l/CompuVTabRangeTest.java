package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class CompuVTabRangeTest {
	private Asap2File file;
	private CompuVTabRange compuVTabRange;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		compuVTabRange = file.getProject().getModules().get(0).getCompuVTabRanges().get(0);

		assertNotNull(compuVTabRange);
	}

	@Test
	void testName() {
		assertEquals("sophisticated.vtabRange", compuVTabRange.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("I am very sophisticated, trust me", compuVTabRange.getLongIdentifier());
	}

	@Test
	void testNumberOfValueTriples() {
		assertEquals(4, compuVTabRange.getNumberOfValueTriples());
	}

	@Test
	void testValueTriples() {
		double[] from = new double[] { -5.5, 5.5, 10, 100 };
		double[] to = new double[] { 5.5, 10, 100, 200 };
		String[] value = new String[] { "from minus five point five to five point five", "from five point five to ten",
				"from ten to one hundred", "from one hundred to two hundred" };
		List<ValueTriple<Double, String>> actualTriples = compuVTabRange.getValueTriples();

		assertEquals(from.length, actualTriples.size());
		assertEquals(to.length, actualTriples.size());
		assertEquals(value.length, actualTriples.size());

		for (int i = 0; i < actualTriples.size(); i++) {
			ValueTriple<Double, String> triple = actualTriples.get(i);
			assertEquals(from[i], triple.getInValMin());
			assertEquals(to[i], triple.getInValMax());
			assertEquals(value[i], triple.getOutVal());
		}
	}

	@Test
	void testDefaultValue() {
		assertEquals("default", compuVTabRange.getDefaultValue());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(CompuVTabRange.class).verify();
	}
}
