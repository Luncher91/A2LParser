package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class VariantCoding extends A2LSerializer implements IA2LWriteable {
	private List<VarCharacteristic> varCharacteristics;
	private List<VarCriterion> varCriterion;
	private List<VarForbiddenComb> varForbiddenComb;
	private VarNaming varNaming;
	private String varSeparator;

	enum VarNaming implements IA2LWriteable {
		NUMERIC;

		@Override
		public void writeTo(A2LWriter writer) throws IOException {
			writer.writelnSpaced("VAR_NAMING", this.name());
		}
	}

	public List<VarCharacteristic> getVarCharacteristics() {
		return varCharacteristics;
	}

	public void setVarCharacteristics(List<VarCharacteristic> varCharacteristics) {
		this.varCharacteristics = varCharacteristics;
	}

	public List<VarCriterion> getVarCriterion() {
		return varCriterion;
	}

	public void setVarCriterion(List<VarCriterion> varCriterion) {
		this.varCriterion = varCriterion;
	}

	public List<VarForbiddenComb> getVarForbiddenComb() {
		return varForbiddenComb;
	}

	public void setVarForbiddenComb(List<VarForbiddenComb> varForbiddenComb) {
		this.varForbiddenComb = varForbiddenComb;
	}

	public VarNaming getVarNaming() {
		return varNaming;
	}

	public void setVarNaming(VarNaming varNaming) {
		this.varNaming = varNaming;
	}

	public String getVarSeparator() {
		return varSeparator;
	}

	public void setVarSeparator(String varSeparator) {
		this.varSeparator = varSeparator;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("VARIANT_CODING");
		writer.indent();

		writer.write(varCharacteristics);
		writer.write(varCriterion);
		writer.write(varForbiddenComb);
		writer.write(varNaming);

		if (varSeparator != null) {
			writer.writelnSpaced("VAR_SEPARATOR", A2LWriter.toA2LString(varSeparator));
		}

		writer.dedent();
		writer.writelnEnd("VARIANT_CODING");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VariantCoding that = (VariantCoding) o;
		return Objects.equals(varCharacteristics, that.varCharacteristics) && Objects
				.equals(varCriterion, that.varCriterion) && Objects.equals(varForbiddenComb, that.varForbiddenComb)
				&& varNaming == that.varNaming && Objects.equals(varSeparator, that.varSeparator);
	}

	@Override
	public int hashCode() {
		return Objects.hash(varCharacteristics, varCriterion, varForbiddenComb, varNaming, varSeparator);
	}
}
