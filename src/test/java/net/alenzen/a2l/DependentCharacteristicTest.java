package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class DependentCharacteristicTest {
	private Asap2File file;
	private DependentCharacteristic depCharacteristic;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		depCharacteristic = file.getProject().getModules().get(0).getCharacteristics().get(0)
				.getDependetCharacteristic();

		assertNotNull(depCharacteristic);
	}

	@Test
	void testFormula() {
		assertEquals("X1*X2", depCharacteristic.getFormula());
	}

	@Test
	void testCharacteristics() {
		String[] characteristics = new String[] { "characA", "characB" };
		List<String> chars = depCharacteristic.getCharacterstics();

		assertEquals(characteristics.length, chars.size());

		for (int i = 0; i < chars.size(); i++) {
			assertEquals(characteristics[i], chars.get(i));
		}
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(DependentCharacteristic.class).verify();
	}
}
