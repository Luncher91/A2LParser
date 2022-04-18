package net.alenzen.a2l.validation;

import java.util.List;

import net.alenzen.a2l.IAsap2TreeElement;

public interface Asap2Validator {
	List<? extends Asap2ValidationError> validate(IAsap2TreeElement o);
}
