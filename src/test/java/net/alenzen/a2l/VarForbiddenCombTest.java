package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class VarForbiddenCombTest {
	private Asap2File file;
	private VarForbiddenComb varCharacteristic;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		varCharacteristic = file.getProject().getModules().get(0).getVariantCoding().getVarForbiddenComb().get(0);

		assertNotNull(varCharacteristic);
	}

	@Test
	void testCombination() {
		String[] criterions = new String[] { "varCriterion", "varCriterion" };
		String[] values = new String[] { "D", "F" };
		List<CriterionTuple> pairs = varCharacteristic.getTuples();

		assertEquals(criterions.length, pairs.size());
		assertEquals(values.length, pairs.size());

		for (int i = 0; i < pairs.size(); i++) {
			assertEquals(criterions[i], pairs.get(i).getName());
			assertEquals(values[i], pairs.get(i).getValue());
		}
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(VarForbiddenComb.class).verify();
	}
}
