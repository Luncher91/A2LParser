package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class A2mlTest {
	private Asap2File file;
	private A2ml a2ml;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		a2ml = file.getProject().getModules().get(0).getA2ml().get(0);

		assertNotNull(a2ml);
	}

	@Test
	void testContent() {
		assertEquals("/begin A2ML i am not parsed /end A2ML", a2ml.getContent());
	}
}
