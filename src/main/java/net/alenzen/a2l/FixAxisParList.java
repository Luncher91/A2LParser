package net.alenzen.a2l;

import java.io.IOException;

public class FixAxisParList implements IA2LWriteable {
	private double[] axisPtsValues;

	public double[] getAxisPtsValues() {
		return axisPtsValues;
	}

	public void setAxisPtsValues(double[] axisPtsValues) {
		this.axisPtsValues = axisPtsValues;
	}
	
	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("FIX_AXIS_PAR_LIST");
		writer.indent();
		
		for(double d : axisPtsValues) {
			writer.writeln(Double.toString(d));
		}
		
		writer.dedent();
		writer.writelnEnd("FIX_AXIS_PAR_LIST");
	}
}
