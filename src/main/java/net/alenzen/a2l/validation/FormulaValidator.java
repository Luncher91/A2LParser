package net.alenzen.a2l.validation;

import java.util.ArrayList;
import java.util.List;

import net.alenzen.a2l.Formula;
import net.alenzen.a2l.FormulaSyntaxError;
import net.alenzen.a2l.IAsap2TreeElement;

public class FormulaValidator implements Asap2Validator {
	public FormulaValidator() {
	}

	@Override
	public List<? extends Asap2ValidationError> validate(IAsap2TreeElement fGen) {
		if (!(fGen instanceof Formula)) {
			return null;
		}

		Formula f = (Formula) fGen;
		List<FormulaValidationError> errorLog = new ArrayList<FormulaValidationError>();
		List<FormulaSyntaxError> fx = f.validateFx();
		List<FormulaSyntaxError> gx = f.validateGx();
		errorLog.add(new FormulaValidationError(f, fx, gx));
		return errorLog;
	}
}
