package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;

public class VarAddress extends ArrayList<Long> implements IA2LWriteable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("VAR_ADDRESS");
		writer.indent();

		for (Long l : this) {
			if (l != null) {
				writer.writeln("0x" + Long.toHexString(l));
			}
		}

		writer.dedent();
		writer.writelnEnd("VAR_ADDRESS");
	}

}
