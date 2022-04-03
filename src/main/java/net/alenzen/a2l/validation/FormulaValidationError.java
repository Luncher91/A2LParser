package net.alenzen.a2l.validation;

import java.util.List;

import net.alenzen.a2l.Formula;
import net.alenzen.a2l.FormulaSyntaxError;

public class FormulaValidationError {
	private Formula formula;
	private List<FormulaSyntaxError> fxErrors;
	private List<FormulaSyntaxError> gxErrors;

	public FormulaValidationError(Formula f, List<FormulaSyntaxError> fxErrors, List<FormulaSyntaxError> gxErrors) {
		this.formula = f;
		this.fxErrors = fxErrors;
		this.gxErrors = gxErrors;
	}

	public Formula getFormula() {
		return formula;
	}

	public List<FormulaSyntaxError> getFxErrors() {
		return fxErrors;
	}

	public List<FormulaSyntaxError> getGxErrors() {
		return gxErrors;
	}
}
