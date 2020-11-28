package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class VarCharacteristicTest {
	private Asap2File file;
	private VarCharacteristic varCharacteristic;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		varCharacteristic = file.getProject().getModules().get(0).getVariantCoding().getVarCharacteristics().get(0);

		assertNotNull(varCharacteristic);
	}

	@Test
	void testName() {
		assertEquals("varChar", varCharacteristic.getName());
	}

	@Test
	void testCriterionNames() {
		String[] criterions = new String[] { "crit1", "crit2", "crit3" };
		IdentReferenceList critNames = varCharacteristic.getCriterions();

		assertEquals(criterions.length, critNames.size());

		for (int i = 0; i < critNames.size(); i++) {
			assertEquals(criterions[i], critNames.get(i));
		}
	}

	@Test
	void testVarAddresses() {
		long[] addresses = new long[] { 0x7444, 0x7445, 0x7446, 0x7447 };
		VarAddress varAdresses = varCharacteristic.getAddresses();

		assertEquals(addresses.length, varAdresses.size());

		for (int i = 0; i < varAdresses.size(); i++) {
			assertEquals(addresses[i], varAdresses.get(i).longValue());
		}
	}
}
