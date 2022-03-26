package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationText extends ArrayList<String> implements IA2LWriteable, IAsap2TreeElement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2462679269677919800L;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("ANNOTATION_TEXT");
		writer.indent();
		
		for(String s : this) {
			if(s != null) {
				writer.writeln(A2LWriter.toA2LString(s));
			}
		}
		
		writer.dedent();
		writer.writelnEnd("ANNOTATION_TEXT");
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}

}
