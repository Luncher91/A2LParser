package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Virtual extends ArrayList<String> implements IA2LWriteable, IAsap2TreeElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("VIRTUAL");
		writer.indent();
		
		for(String s : this) {
			if(s != null) {
				writer.writeln(s);
			}
		}
		
		writer.dedent();
		writer.writelnEnd("VIRTUAL");
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}

}
