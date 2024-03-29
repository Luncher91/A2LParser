package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Formula extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	private String fx;

	// optional parameters
	private String gx;

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("FORMULA", A2LWriter.toA2LString(fx));
		writer.indent();
		if (gx != null) {
			writer.writelnSpaced("FORMULA_INV", A2LWriter.toA2LString(gx));
		}
		writer.dedent();
		writer.writelnEnd("FORMULA");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Formula formula = (Formula) o;
		return Objects.equals(fx, formula.fx) && Objects.equals(gx, formula.gx);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fx, gx);
	}

	public List<FormulaSyntaxError> validateFx() {
		return new FormulaValidator(fx).validate();
	}

	public List<FormulaSyntaxError> validateGx() {
		if (gx == null) {
			return null;
		}

		return new FormulaValidator(gx).validate();
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
	
	private static double calulcateFormula(String f, Map<String, Double> variables) {
		FormulaParser fp = FormulaParser.createFromFormula(f);
		fp.setVariables(variables);
		return fp.calculate();
	}
	
	public FormulaParser createFormulaFx() {
		return FormulaParser.createFromFormula(fx);
	}
	
	public FormulaParser createFormulaGx() {
		return FormulaParser.createFromFormula(fx);
	}
	
	public double calculateFx(Map<String, Double> variables) {
		return calulcateFormula(fx, variables);
	}

	public double calculateGx(Map<String, Double> variables) {
		return calulcateFormula(gx, variables);
	}
}
