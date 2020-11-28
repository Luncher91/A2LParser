package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class FrameTest {
	private Asap2File file;
	private Frame frame;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		frame = file.getProject().getModules().get(0).getFrame();

		assertNotNull(frame);
	}

	@Test
	void testName() {
		assertEquals("frame", frame.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("big frame", frame.getLongIdentifier());
	}

	@Test
	void testScalingUnit() {
		assertEquals(7, frame.getScalingUnit());
	}

	@Test
	void testRate() {
		assertEquals(49, frame.getRate());
	}

	@Test
	void testFrameMeasurement() {
		String[] identifier = new String[] { "identA", "identB", "identC" };
		IdentReferenceList idents = frame.getFrameMeasurements();
		
		assertEquals(identifier.length, idents.size());
		
		for(int i = 0; i < idents.size(); i++) {
			assertEquals(identifier[i], idents.get(i));
		}
	}
}
