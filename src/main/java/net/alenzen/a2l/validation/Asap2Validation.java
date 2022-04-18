package net.alenzen.a2l.validation;

import java.util.ArrayList;
import java.util.List;

import net.alenzen.a2l.Asap2File;
import net.alenzen.a2l.IAsap2TreeElement;

public class Asap2Validation {
	private Asap2File file;
	private List<Asap2Validator> validators;

	public Asap2Validation(Asap2File f) {
		this.file = f;
		this.validators = new ArrayList<Asap2Validator>();
	}

	public List<Asap2ValidationError> validate() {
		List<Asap2ValidationError> errorLog = new ArrayList<Asap2ValidationError>();

		for (IAsap2TreeElement o : this.file) {
			for (Asap2Validator v : this.validators) {
				List<? extends Asap2ValidationError> errors = v.validate(o);

				if (errors == null) {
					continue;
				}

				errorLog.addAll(errors);
			}
		}

		return errorLog;
	}

	public List<Asap2Validator> getValidators() {
		return validators;
	}

	public void setValidators(List<Asap2Validator> validators) {
		this.validators = validators;
	}
}
