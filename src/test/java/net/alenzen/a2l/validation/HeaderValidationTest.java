package net.alenzen.a2l.validation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2File;
import net.alenzen.a2l.Asap2FileTest;
import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.Header;

public class HeaderValidationTest {
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
		Asap2ValidationTestHelper.asap2StringValidation(file, () -> header.getComment(), s -> header.setComment(s));
	}
	
	@Test
	void testProjectNo() {
		Asap2ValidationTestHelper.asap2NullableIdentValidation(file, () -> header.getProjectNo(), s -> header.setProjectNo(s));
	}
	
	@Test
	void testVersion() {
		Asap2ValidationTestHelper.asap2NullableStringValidation(file, () -> header.getVersion(), s -> header.setVersion(s));
	}
}
