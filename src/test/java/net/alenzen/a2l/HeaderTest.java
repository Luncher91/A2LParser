package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class HeaderTest {
	private Asap2File file;
	private Header header;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		header = file.getProject().getHeader();

		assertNotNull(header);
	}

	@Test
	void testComment() {
		assertEquals("Free Asap2 example file", header.getComment());
	}

	@Test
	void testProjectNo() {
		assertEquals("Free42", header.getProjectNo());
	}

	@Test
	void testVersion() {
		assertEquals("V1.7.1", header.getVersion());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(Header.class).verify();
	}
}
