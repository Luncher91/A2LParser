package net.alenzen.a2l.validation;

import net.alenzen.a2l.Asap2File;

public class StandardAsap2Validation extends Asap2Validation {

	public StandardAsap2Validation(Asap2File f) {
		super(f);

		getValidators().add(new FormulaValidator());
		getValidators().add(new Asap2IdentValidator());
		getValidators().add(new Asap2StringValidator());
	}

}
