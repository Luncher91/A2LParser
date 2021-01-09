package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;

public class IdentReferenceList extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void toA2l(A2LWriter writer, String attributeName) throws IOException {
		writer.writeln(attributeName);
		for(String ident : this) {
			writer.writeln(ident);
		}
	}

	public void toA2lAsBlock(A2LWriter writer, String name) throws IOException {
		writer.writelnBeginSpaced(name);
		writer.indent();
		for(String ident : this) {
			writer.writeln(ident);
		}
		writer.dedent();
		writer.writelnEnd(name);
	}

}
