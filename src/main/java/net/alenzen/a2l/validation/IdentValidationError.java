package net.alenzen.a2l.validation;

import java.lang.reflect.Field;

import net.alenzen.a2l.IAsap2TreeElement;

public class IdentValidationError extends Asap2ValidationError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1816761512464891740L;
	private Field field;
	private String value;
	private IAsap2TreeElement target;

	public IdentValidationError(Field f, String value, IAsap2TreeElement o, Exception cause) {
		super(cause);
		this.field = f;
		this.target = o;
		this.value = value;
	}

	public IdentValidationError(Field f, String value, IAsap2TreeElement o) {
		this.field = f;
		this.target = o;
		this.value = value;
	}

	public IdentValidationError(Field f, String value, IAsap2TreeElement o, String message) {
		super(message);
		this.field = f;
		this.target = o;
		this.value = value;
	}

	@Override
	public String getMessage() {
		String superMsg = super.getMessage();

		if (value != null) {
			return String.format("The value '%s' of field '%s' of class %s is not a valid 'ident': %s", value,
					field.getName(), target.getClass().toGenericString(), superMsg);
		}

		return String.format("Cannot validate value of field '%s' of class '%s': %s", field.getName(),
				target.getClass().toGenericString(), superMsg);
	}

	public Field getField() {
		return field;
	}

	public IAsap2TreeElement getTarget() {
		return target;
	}

	public String getValue() {
		return value;
	}
}
