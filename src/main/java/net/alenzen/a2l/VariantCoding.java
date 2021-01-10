package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

public class VariantCoding implements IA2LWriteable {
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
}
