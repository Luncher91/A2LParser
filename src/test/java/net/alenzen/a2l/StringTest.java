package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class StringTest {
	private Asap2File file;
	private Annotation annotation;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.E, null);
		annotation = file.getProject().getModules().get(0).getCharacteristics().get(0).getNotes().get(0);

		assertNotNull(annotation);
	}

	@Test
	void testNewLineNotEscaping() {
		assertEquals("line bre" + //
				"\t\t\t\tak without and with es" + //
				"\t\t\t\tcaping", annotation.getText().get(0));
	}
}
