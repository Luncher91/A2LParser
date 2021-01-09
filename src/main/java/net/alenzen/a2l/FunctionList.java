package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;

public class FunctionList extends ArrayList<String> implements IA2LWriteable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("FUNCTION_LIST");
		writer.indent();
		for(String ident : this) {
			writer.writeln(ident);
		}
		writer.dedent();
		writer.writelnEnd("FUNCTION_LIST");
	}

}
