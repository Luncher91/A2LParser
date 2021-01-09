package net.alenzen.a2l;

import java.io.IOException;

public class FixAxisParDist extends FixAxisPar {

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("FIX_AXIS_PAR_DIST", Long.toString(getOffset()), Long.toString(getShift()),
				Long.toString(getNumberapo()));
	}
}
