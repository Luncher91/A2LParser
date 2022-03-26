package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class VarForbiddenComb extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VarForbiddenComb that = (VarForbiddenComb) o;
		return Objects.equals(tuples, that.tuples);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tuples);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.tuples);
		return subNodes;
	}
}
