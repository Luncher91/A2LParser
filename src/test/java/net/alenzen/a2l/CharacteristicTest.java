package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;
import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.CalibrationAccess;
import net.alenzen.a2l.enums.CharacteristicType;

public class CharacteristicTest {
	private Asap2File file;
	private Characteristic characteristic;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		characteristic = file.getProject().getModules().get(0).getCharacteristics().get(0);

		assertNotNull(characteristic);
	}

	@Test
	void testName() {
		assertEquals("char_a", characteristic.getName());
	}

	@Test
	void testLongIdentifier() {
		assertEquals("description here", characteristic.getLongIdentifier());
	}

	@Test
	void testType() {
		assertEquals(CharacteristicType.CUBE_5, characteristic.getType());
	}

	@Test
	void testAddress() {
		assertEquals(0x123, characteristic.getAddress());
	}

	@Test
	void testDeposit() {
		assertEquals("char_deposit", characteristic.getDeposit());
	}

	@Test
	void testMaxDiff() {
		assertEquals(1.1, characteristic.getMaxDiff());
	}

	@Test
	void testConversion() {
		assertEquals("char_conv", characteristic.getConversion());
	}

	@Test
	void testLowerLimit() {
		assertEquals(0.0, characteristic.getLowerLimit());
	}

	@Test
	void TestUpperLimit() {
		assertEquals(10.0, characteristic.getUpperLimit());
	}

	@Test
	void testBitMask() {
		assertEquals(0xFFFFFF, characteristic.getBitmask());
	}

	@Test
	void testByteOrder() {
		assertEquals(ByteOrder.MSB_LAST, characteristic.getByteorder());
	}

	@Test
	void testCalibrationAccess() {
		assertEquals(CalibrationAccess.CALIBRATION, characteristic.getAccess());
	}

	@Test
	void testDiscrete() {
		assertEquals(true, characteristic.isDiscrete());
	}

	@Test
	void testDisplayIdentifier() {
		assertEquals("display_ident_char", characteristic.getDisplayIdentifier());
	}

	@Test
	void testEcuAddressExtension() {
		assertEquals(10000, characteristic.getEcuAddressExtension());
	}

	@Test
	void testFormat() {
		assertEquals("%12.12", characteristic.getFormat());
	}

	@Test
	void testGuardRails() {
		assertEquals(false, characteristic.isGuardRails());
	}

	@Test
	void testMapList() {
		String[] idents = new String[] { "identA", "identB" };
		IdentReferenceList mapLst = characteristic.getMapList();
		for (int i = 0; i < mapLst.size(); i++) {
			assertEquals(idents[i], mapLst.get(i));
		}
	}

	@Test
	void testNumber() {
		assertEquals(1233123, characteristic.getNumber());
	}

	@Test
	void testPhysUnit() {
		assertEquals("kg", characteristic.getPhysUnit());
	}

	@Test
	void testReadOnly() {
		assertEquals(true, characteristic.isReadOnly());
	}

	@Test
	void testRefMemorySegment() {
		assertEquals("memory_seg_ref", characteristic.getMemorySegment());
	}

	@Test
	void testStepSize() {
		assertEquals(99.9, characteristic.getStepSize());
	}

	@Test
	void testComparisonQuantity() {
		assertEquals("comparisonQuantity", characteristic.getComparisonQuantityMeasurment());
	}

	@Test
	void testExtenededLimits() {
		ExtendedLimits exLimits = characteristic.getExtendedLimits();
		assertEquals(1.1, exLimits.getLowerLimit());
		assertEquals(5.5, exLimits.getUpperLimit());
	}

	@Test
	void testIfData() {
		assertEquals("/begin IF_DATA i am not parsed yet /end IF_DATA", characteristic.getIfData().get(0).getContent());
	}

	@Test
	void testSymbolLink() {
		assertEquals("symbol name char", characteristic.getSymbolLink().getSymbolName());
		assertEquals(7, characteristic.getSymbolLink().getOffset());
	}

	@Test
	void testMaxRefresh() {
		assertEquals(6, characteristic.getMaxRefresh().getScalingUnit());
		assertEquals(10, characteristic.getMaxRefresh().getRate());
	}

	@Test
	void testMatrixDim() {
		assertEquals(3, characteristic.getMatrixDim().getxDim());
		assertEquals(4, characteristic.getMatrixDim().getyDim());
		assertEquals(5, characteristic.getMatrixDim().getzDim());
	}

	@Test
	void testFunctionList() {
		String[] functions = new String[] { "f1", "f2", "f3" };
		FunctionList funcs = characteristic.getFunctions();

		assertEquals(functions.length, funcs.size());

		for (int i = 0; i < funcs.size(); i++) {
			assertEquals(functions[i], funcs.get(i));
		}
	}

	@Test
	void testAnnotations() {
		assertEquals("hello world", characteristic.getNotes().get(0).getText().get(0));
	}

	@Test
	public void equalsContract() {
		EqualsVerifierConfigured.getEqualsVerifier().forClass(Characteristic.class).verify();
	}
}
