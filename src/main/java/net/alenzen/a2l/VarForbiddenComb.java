package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

public class VarForbiddenComb implements IA2LWriteable {
	private List<CriterionTuple> tuples;

	public List<CriterionTuple> getTuples() {
		return tuples;
	}

	public void setTuples(List<CriterionTuple> tuples) {
		this.tuples = tuples;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("VAR_FORBIDDEN_COMB");
		writer.indent();
		
		writer.write(tuples);
		
		writer.dedent();
		writer.writelnEnd("VAR_FORBIDDEN_COMB");
	}
}
