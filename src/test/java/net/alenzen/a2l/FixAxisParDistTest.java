package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class FixAxisParDistTest {
	private Asap2File file;
	private FixAxisParDist fixAxisParDist;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		fixAxisParDist = file.getProject().getModules().get(0).getCharacteristics().get(0).getAxisDescriptions().get(0)
				.getFixAxisParDist();

		assertNotNull(fixAxisParDist);
	}

	@Test
	void testOffset() {
		assertEquals(4, fixAxisParDist.getOffset());
	}

	@Test
	void testShift() {
		assertEquals(16, fixAxisParDist.getShift());
	}

	@Test
	void testNumberapo() {
		assertEquals(32, fixAxisParDist.getNumberapo());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(FixAxisParDist.class).verify();
	}
}
