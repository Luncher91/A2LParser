package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class FixAxisParListTest {
	private Asap2File file;
	private FixAxisParList fixAxisParList;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		fixAxisParList = file.getProject().getModules().get(0).getCharacteristics().get(0).getAxisDescriptions().get(0)
				.getFixAxisParList();

		assertNotNull(fixAxisParList);
	}

	@Test
	void testValues() {
		double[] values = new double[] { 1.1, 2.2, 3.3, 4.4, 5.5 };
		double[] vs = fixAxisParList.getAxisPtsValues();
		
		assertEquals(values.length, vs.length);
		
		for(int i = 0; i < vs.length; i++) {
			assertEquals(values[i], vs[i]);
		}
	}
}
