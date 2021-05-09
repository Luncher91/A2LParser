package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class VarCriterionTest {
	private Asap2File file;
	private VarCriterion varCriterion;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		varCriterion = file.getProject().getModules().get(0).getVariantCoding().getVarCriterion().get(0);

		assertNotNull(varCriterion);
	}

	@Test
	void testName() {
		assertEquals("varCriterion", varCriterion.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("var criterion", varCriterion.getLongIdentifier());
	}

	@Test
	void testValues() {
		String[] values = new String[] { "A", "B", "C", "D", "E", "F" };
		List<String> vs = varCriterion.getValues();

		assertEquals(values.length, vs.size());

		for (int i = 0; i < vs.size(); i++) {
			assertEquals(values[i], vs.get(i));
		}
	}

	@Test
	void testVarMeasurement() {
		assertEquals("superA", varCriterion.getMeasurement());
	}

	@Test
	void testVarSelectionCharacteristic() {
		assertEquals("varSelectionCharac", varCriterion.getSelectionCharacteristic());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(VarCriterion.class).verify();
	}
}
