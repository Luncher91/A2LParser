package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.DataType;

public class MeasurementTest {
	private Asap2File file;
	private Measurement measurement;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		measurement = file.getProject().getModules().get(0).getMeasurements().get(0);

		assertNotNull(measurement);
	}

	@Test
	void testName() {
		assertEquals("superMeasurement", measurement.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("ultimate test", measurement.getLongIdentifier());
	}

	@Test
	void testDatatype() {
		assertEquals(DataType.UBYTE, measurement.getDatatype());
	}

	@Test
	void testConversion() {
		assertEquals("THE.IDENTITY", measurement.getConversion());
	}

	@Test
	void testResolution() {
		assertEquals(1L, measurement.getResolution());
	}

	@Test
	void testAccuracy() {
		assertEquals(2.1, measurement.getAccuracy());
	}

	@Test
	void testLowerLimit() {
		assertEquals(4.2, measurement.getLowerLimit());
	}

	@Test
	void testUpperLimit() {
		assertEquals(255, measurement.getUpperLimit());
	}
	
	@Test
	void testArraySize() {
		assertEquals(7, measurement.getArraySize());
	}
	
	@Test
	void testDiscrete() {
		assertEquals(true, measurement.isDiscrete());
	}
	
	@Test
	void testEcuAddressExtension() {
		assertEquals(0xFF, measurement.getEcuAddressExtension());
	}
	
	@Test
	void testErrorMask() {
		assertEquals(0x000E, measurement.getErrorMask());
	}
	
	@Test
	void testLayout() {
		assertEquals(Measurement.LayoutIndexMode.ROW_DIR, measurement.getLayout());
	}
	
	@Test
	void testReadWrite() {
		assertEquals(true, measurement.isReadWrite());
	}

	@Test
	void testPhysUnit() {
		assertEquals("kg/m", measurement.getPhysUnit());
	}

	@Test
	void testBitMask() {
		assertEquals(0xAFFF, measurement.getBitMask());
	}

	@Test
	void testByteorder() {
		assertEquals(ByteOrder.MSB_FIRST, measurement.getByteorder());
	}

	@Test
	void testRefMemorySegment() {
		assertEquals("mem1", measurement.getMemorySegment());
	}

	@Test
	void testEcuAddress() {
		assertEquals(0xABCDEF, measurement.getEcuAddress());
	}

	@Test
	void testFormat() {
		assertEquals("%3.1", measurement.getFormat());
	}

	@Test
	void testDisplayIdentifier() {
		assertEquals("mega.superMeasurement", measurement.getDisplayIdentifier());
	}
}
