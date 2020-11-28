package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class GroupTest {
	private Asap2File file;
	private Group group;

	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		group = file.getProject().getModules().get(0).getGroups().get(0);

		assertNotNull(group);
	}
	
	@Test
	void testName() {
		assertEquals("groupies", group.getGroupName());
	}
	
	@Test
	void testLongIdentifier() {
		assertEquals("stick to the group", group.getLongIdentifier());
	}
	
	@Test
	void testRoot() {
		assertTrue(group.isRoot());
	}
	
	@Test
	void testRefCharacteristic() {
		String[] characteristics = new String[] {"refChar1", "refChar2", "refChar3"};
		IdentReferenceList refChars = group.getRefCharacteristics();
		
		assertEquals(characteristics.length, refChars.size());
		
		for(int i = 0; i < refChars.size(); i++) {
			assertEquals(characteristics[i], refChars.get(i));
		}
	}
	
	@Test
	void testRefMeasurement() {
		String[] measurements = new String[] {"meas1", "meas2", "meas3"};
		IdentReferenceList refMeas = group.getRefMeasurements();
		
		assertEquals(measurements.length, refMeas.size());
		
		for(int i = 0; i < refMeas.size(); i++) {
			assertEquals(measurements[i], refMeas.get(i));
		}
	}
	
	@Test
	void testSubGroup() {
		String[] groups = new String[] {"subGroup1", "subGroup2", "subGroup3"};
		IdentReferenceList subGroups = group.getSubGroups();
		
		assertEquals(groups.length, subGroups.size());
		
		for(int i = 0; i < subGroups.size(); i++) {
			assertEquals(groups[i], subGroups.get(i));
		}
	}
}
