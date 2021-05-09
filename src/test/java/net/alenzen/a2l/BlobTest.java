package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class BlobTest {
	private Asap2File file;
	private Blob blob;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		blob = file.getProject().getModules().get(0).getBlobs().get(0);

		assertNotNull(blob);
	}

	@Test
	void testContent() {
		assertEquals("/begin BLOB i am not parsed neither /end BLOB", blob.getContent());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(Blob.class).verify();
	}
}
