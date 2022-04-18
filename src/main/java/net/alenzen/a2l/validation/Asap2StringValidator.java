package net.alenzen.a2l.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.alenzen.a2l.A2LWriter;
import net.alenzen.a2l.IAsap2TreeElement;

public class Asap2StringValidator implements Asap2Validator {

	private static final int MAX_STRING = 256;

	@Override
	public List<? extends Asap2ValidationError> validate(IAsap2TreeElement target) {
		List<StringValidationError> error = new ArrayList<StringValidationError>();
		Class<?> currentClass = target.getClass();
		Field[] fields = currentClass.getDeclaredFields();
		while (true) {
			for (Field f : fields) {
				if (f.getAnnotation(Asap2String.class) != null && f.getType().equals(String.class)) {
					try {
						validateStringField(f, target);
					} catch (StringValidationError e) {
						error.add(e);
					}
				}
			}

			currentClass = currentClass.getSuperclass();
			if (currentClass == null)
				break;

			fields = currentClass.getDeclaredFields();
		}

		return error;
	}

	private void validateStringField(Field f, IAsap2TreeElement target) throws StringValidationError {
		f.setAccessible(true);
		try {
			Object sObj = f.get(target);
			if (sObj != null) {
				String s = (String) sObj;
				String escapedString = A2LWriter.toA2LString(s);
				if (escapedString.length() > MAX_STRING) {
					throw new StringValidationError(f, escapedString, target,
							"Exceeding MAX_STRING(" + MAX_STRING + ")");
				}
			} else if(!f.getAnnotation(Asap2String.class).nullable()) {
				throw new StringValidationError(f, null, target,
						"String is not nullable");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new StringValidationError(f, null, target, e);
		}
	}

}
