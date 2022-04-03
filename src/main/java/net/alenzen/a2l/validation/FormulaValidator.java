package net.alenzen.a2l.validation;

import java.util.ArrayList;
import java.util.List;

import net.alenzen.a2l.Asap2File;
import net.alenzen.a2l.Formula;
import net.alenzen.a2l.FormulaSyntaxError;
import net.alenzen.a2l.IAsap2TreeElement;

public class FormulaValidator {
	private Asap2File a2lFile;

	public FormulaValidator(Asap2File f) {
		this.a2lFile = f;
	}

	public List<FormulaValidationError> validate() {
		List<FormulaValidationError> errorLog = new ArrayList<FormulaValidationError>();
		for (IAsap2TreeElement f : this.a2lFile) {
			if (f instanceof Formula) {
				Formula formula = (Formula) f;
				List<FormulaSyntaxError> fx = formula.validateFx();
				List<FormulaSyntaxError> gx = formula.validateGx();
				errorLog.add(new FormulaValidationError(formula, fx, gx));
			}
		}
		return errorLog;
	}
}
