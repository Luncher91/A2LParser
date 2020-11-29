package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class ModParTest {
	private Asap2File file;
	private ModPar modPar;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		modPar = file.getProject().getModules().get(0).getModPar();

		assertNotNull(modPar);
	}

	@Test
	void testComment() {
		assertEquals("cmt", modPar.getComment());
	}

	@Test
	void testAddresses() {
		Long[] addrs = new Long[] { 0x12L, 0x123456L, 0xFL };
		List<Long> actualAddresses = modPar.getAddresses();

		assertEquals(addrs.length, actualAddresses.size());
		for (int i = 0; i < actualAddresses.size(); i++) {
			assertEquals(addrs[i], actualAddresses.get(i));
		}
	}

	@Test
	void testCpuType() {
		assertEquals("RISC", modPar.getCpuType());
	}

	@Test
	void testCustomer() {
		assertEquals("public domain", modPar.getCustomer());
	}

	@Test
	void testCustomerNo() {
		assertEquals("42", modPar.getCustomerNo());
	}

	@Test
	void testEcu() {
		assertEquals("RISC-V", modPar.getEcu());
	}

	@Test
	void testEcuCalibrationOffset() {
		assertEquals(0x54L, modPar.getEcuCalibrationOffset());
	}

	@Test
	void testEpk() {
		assertEquals("EPROM_identification - should it be a string or an identifier?", modPar.getEpk());
	}

	@Test
	void testNumberOfInterfaces() {
		assertEquals(168, modPar.getNumberOfInterfaces());
	}

	@Test
	void testPhoneNumber() {
		assertEquals("911", modPar.getPhoneNumber());
	}

	@Test
	void testSupplier() {
		assertEquals("Andreas Lenzen", modPar.getSupplier());
	}

	@Test
	void testUser() {
		assertEquals("A2l stakeholders", modPar.getUser());
	}

	@Test
	void testVersion() {
		assertEquals("Very high :-)", modPar.getVersion());
	}

	@Test
	void testSystemConstants() {
		String[] names = new String[] { "close to 1", "pi" };
		String[] values = new String[] { "value 1", "about 3" };
		List<SystemConstant> sysConstants = modPar.getSystemConstants();
		
		assertEquals(names.length, sysConstants.size());
		assertEquals(values.length, sysConstants.size());
		
		for(int i = 0; i < sysConstants.size(); i++) {
			assertEquals(names[i], sysConstants.get(i).getName());
			assertEquals(values[i], sysConstants.get(i).getValue());
		}
	}
}
