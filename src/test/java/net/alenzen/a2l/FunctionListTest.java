package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.alenzen.a2l.Asap2FileTest.TestFile;

public class FunctionListTest {
	private Asap2File file;
	private FunctionList functionList;
	
	@BeforeEach
	void initTestfile() throws IOException {
		file = Asap2FileTest.getTestFile(TestFile.A);
		functionList = file.getProject().getModules().get(0).getMeasurements().get(0).getFunctionList();

		assertNotNull(functionList);
	}
	
	@Test
	void testEntries() {
		String[] values = new String[] {"firstFunction", "secondFunction", "thirdFunction"};
		for(int i = 0; i < functionList.size(); i++) {
			assertEquals(values[i], functionList.get(i));
		}
	}
}
