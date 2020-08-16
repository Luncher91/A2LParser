package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HeaderTest {
	private Asap2File file;
	private Header header;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFileA();
		header = file.getProject().getHeader();
		
		assertNotNull(header);
	}
	
	@Test
	void testComment() {
		assertEquals("ASAP2 Example File", header.getComment());
	}
	
	@Test
	void testProjectNo() {
		assertEquals("P2016_09_AE_MCD_2MC_BS_V1_7_1_main", header.getProjectNo());
	}
	
	@Test
	void testVersion() {
		assertEquals("V1.7.1", header.getVersion());
	}
}
