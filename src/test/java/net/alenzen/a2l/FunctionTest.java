package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class FunctionTest {
	private Asap2File file;
	private Function function;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		function = file.getProject().getModules().get(0).getFunctions().get(0);

		assertNotNull(function);
	}
	
	@Test
	void testName() {
		assertEquals("firstFunction", function.getName());
	}
	
	@Test
	void testLongIdentifier() {
		assertEquals("very first function", function.getLongIdentifier());
	}
	
	@Test
	void testDefCharacteristic() {
		String[] characteristics = new String[] {"char1", "char2", "char3"};
		IdentReferenceList defChars = function.getDefCharacteristics();
		
		assertEquals(characteristics.length, defChars.size());
		
		for(int i = 0; i < defChars.size(); i++) {
			assertEquals(characteristics[i], defChars.get(i));
		}
	}
	
	@Test
	void testFunctionVersion() {
		assertEquals("version3.1.4.1", function.getFunctionVersion());
	}
	
	@Test
	void testInMeasurement() {
		String[] measurements = new String[] {"meas1", "meas2", "meas3"};
		IdentReferenceList inMeas = function.getInMeasurments();
		
		assertEquals(measurements.length, inMeas.size());
		
		for(int i = 0; i < inMeas.size(); i++) {
			assertEquals(measurements[i], inMeas.get(i));
		}
	}
	
	@Test
	void testLocMeasurement() {
		String[] measurements = new String[] {"locMeas1", "locMeas2", "locMeas3"};
		IdentReferenceList locMeas = function.getLocMeasurments();
		
		assertEquals(measurements.length, locMeas.size());
		
		for(int i = 0; i < locMeas.size(); i++) {
			assertEquals(measurements[i], locMeas.get(i));
		}
	}
	
	@Test
	void testOutMeasurement() {
		String[] measurements = new String[] {"outMeas1", "outMeas2", "outMeas3"};
		IdentReferenceList outMeas = function.getOutMeasurments();
		
		assertEquals(measurements.length, outMeas.size());
		
		for(int i = 0; i < outMeas.size(); i++) {
			assertEquals(measurements[i], outMeas.get(i));
		}
	}
	
	@Test
	void testRefCharacteristic() {
		String[] characteristics = new String[] {"refChar1", "refChar2", "refChar3"};
		IdentReferenceList refChars = function.getRefCharacteristics();
		
		assertEquals(characteristics.length, refChars.size());
		
		for(int i = 0; i < refChars.size(); i++) {
			assertEquals(characteristics[i], refChars.get(i));
		}
	}
	
	@Test
	void testSubFunctions() {
		String[] subFunctions = new String[] {"subFunc1", "subFunc2", "subFunc3"};
		IdentReferenceList subFuncs = function.getSubFunctions();
		
		assertEquals(subFunctions.length, subFuncs.size());
		
		for(int i = 0; i < subFuncs.size(); i++) {
			assertEquals(subFunctions[i], subFuncs.get(i));
		}
	}
}
