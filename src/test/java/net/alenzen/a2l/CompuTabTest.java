package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.enums.ConversionType;
import nl.jqno.equalsverifier.EqualsVerifier;

public class CompuTabTest {
	private Asap2File file;
	private CompuTab compuTab;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		compuTab = file.getProject().getModules().get(0).getCompuTabs().get(0);

		assertNotNull(compuTab);
	}

	@Test
	void testName() {
		assertEquals("PERFECT_COMPU.TAB", compuTab.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("very perfect compu tab", compuTab.getLongIdentifier());
	}

	@Test
	void testConversionType() {
		assertEquals(ConversionType.TAB_INTP, compuTab.getConversionType());
	}

	@Test
	void testNumberValuePairs() {
		assertEquals(5, compuTab.getNumberOfValuePairs());
	}

	@Test
	void testInValOutVal() {
		double[] inVals = new double[] { -1.1, 1d, 1.123456, 3123d, 0d };
		double[] outVals = new double[] { -1.1, -1d, 0.312312, 3123d, 0.0 };

		List<ValuePair<Double, Double>> valuePairs = compuTab.getValuePairs();
		assertEquals(inVals.length, valuePairs.size());
		assertEquals(outVals.length, valuePairs.size());

		for (int i = 0; i < valuePairs.size(); i++) {
			ValuePair<Double, Double> entry = valuePairs.get(i);
			assertEquals(inVals[i], entry.getInVal());
			assertEquals(outVals[i], entry.getOutVal());
		}
	}

	@Test
	void testDefaultValue() {
		assertEquals("invalid input value", compuTab.getDefaultValue());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(CompuTab.class).verify();
	}
}
