package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class VirtualCharacteristicTest {
	private Asap2File file;
	private VirtualCharacteristic virtualCharacteristic;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		virtualCharacteristic = file.getProject().getModules().get(0).getCharacteristics().get(0)
				.getVirtualCharacteristic();

		assertNotNull(virtualCharacteristic);
	}

	@Test
	void testName() {
		assertEquals("sin(cos(X2-X1))", virtualCharacteristic.getFormula());
	}

	@Test
	void testCharacteristics() {
		String[] chars = new String[] { "characteristicA", "characteristicB" };
		for (int i = 0; i < virtualCharacteristic.getCharacterstics().size(); i++) {
			assertEquals(chars[i], virtualCharacteristic.getCharacterstics().get(i));
		}
	}

	@Test
	public void equalsContract() {
		EqualsVerifierConfigured.getEqualsVerifier().forClass(VirtualCharacteristic.class).verify();
	}
}
