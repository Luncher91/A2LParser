package net.alenzen.a2l;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.Test;

public class Asap2ParserTest {
	private final String TEST_ESCAPE_STRING = "\\" + "\"" + "'" + "\n" + "\r" + "\t";
	@Test
	void testToA2LString() {
		String result = A2LWriter.toA2LString(TEST_ESCAPE_STRING);
		assertEquals("\"\\\\\\\"\\'\\n\\r\\t\"", result);
	}
	
	@Test
	void testToA2LString_backAndForth() throws InvalidParameterException {
		String resultA = A2LWriter.toA2LString(TEST_ESCAPE_STRING);
		String resultB = A2LVisitor.toJavaString(resultA);
		assertEquals(TEST_ESCAPE_STRING, resultB);
	}
}
