package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import nl.jqno.equalsverifier.EqualsVerifier;

public class IfDataTest {
	private Asap2File file;
	private IfData ifData;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		ifData = file.getProject().getModules().get(0).getMeasurements().get(0).getIfDatas().get(0);

		assertNotNull(ifData);
	}

	@Test
	void testContent() {
		assertEquals("/begin IF_DATA i am not parsed yet /end IF_DATA", ifData.getContent());
	}

	@Test
	public void equalsContract() {
		EqualsVerifier.simple().forClass(IfData.class).verify();
	}
}
