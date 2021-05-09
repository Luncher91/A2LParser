package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class UnitTest {
	private Asap2File file;
	private Unit unit;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		unit = file.getProject().getModules().get(0).getUnits().get(0);

		assertNotNull(unit);
	}

	@Test
	void testName() {
		assertEquals("unitA", unit.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("detailed description with spaces", unit.getLongIdentifier());
	}

	@Test
	void testDisplay() {
		assertEquals("display string", unit.getDisplay());
	}

	@Test
	void testType() {
		assertEquals(Unit.Type.DERIVED, unit.getType());
	}

	@Test
	void testRefUnit() {
		assertEquals("unitB", unit.getUnit_ref());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(Unit.class).verify();
	}
}
