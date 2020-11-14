package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.enums.ConversionType;

public class CompuVTabTest {
	private Asap2File file;
	private CompuVTab compuVTab;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		compuVTab = file.getProject().getModules().get(0).getCompuVTabs().get(0);

		assertNotNull(compuVTab);
	}

	@Test
	void testName() {
		assertEquals("firstVtab", compuVTab.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("very first vtab", compuVTab.getLongIdentifier());
	}

	@Test
	void testConversionType() {
		assertEquals(ConversionType.TAB_VERB, compuVTab.getConversionType());
	}

	@Test
	void testNumberValuePairs() {
		assertEquals(3, compuVTab.getNumberOfValuePairs());
	}

	@Test
	void testInValOutVal() {
		double[] inVals = new double[] { 1.1, 0.9, 0 };
		String[] outVals = new String[] { "first entry", "second entry", "third entry" };
		List<ValuePair<Double, String>> actualValues = compuVTab.getValuePairs();
		assertEquals(inVals.length, actualValues.size());
		assertEquals(outVals.length, actualValues.size());
		
		for(int i = 0; i < actualValues.size(); i++) {
			ValuePair<Double, String> entry = actualValues.get(i);
			assertEquals(inVals[i], entry.getInVal());
			assertEquals(outVals[i], entry.getOutVal());
		}
	}
	
	@Test
	void testDefaultValue() {
		assertEquals("default value", compuVTab.getDefaultValue());
	}
}
