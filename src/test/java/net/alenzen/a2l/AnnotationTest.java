package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class AnnotationTest {
	private Asap2File file;
	private Annotation annotation;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		annotation = file.getProject().getModules().get(0).getMeasurements().get(0).getAnnotations().get(0);

		assertNotNull(annotation);
	}

	@Test
	void testLabel() {
		assertEquals("annotation I", annotation.getLabel());
	}

	@Test
	void testOrigin() {
		assertEquals("me", annotation.getOrigin());
	}

	@Test
	void testText() {
		String[] textLines = new String[] { "row \\'1'\\", "row '2'\r\n", "row\t3" };
		List<String> actualText = annotation.getText();
		assertEquals(textLines.length, actualText.size());

		for (int i = 0; i < actualText.size(); i++) {
			assertEquals(textLines[i], actualText.get(i));
		}
	}

	@Test
	public void equalsContract() {
		EqualsVerifierConfigured.getEqualsVerifier().forClass(Annotation.class).verify();
	}
}
