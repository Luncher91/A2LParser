package net.alenzen.a2l.validation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.alenzen.a2l.IAsap2TreeElement;

public class Asap2IdentValidator implements Asap2Validator {
	private static final int MAX_IDENT = 1024;
	private static final int MAX_PARTIAL_IDENT = 128;
	private final Pattern IDENT_PATTERN = Pattern
			.compile("[a-zA-Z_][a-zA-Z0-9_\\-]*(\\[[a-zA-Z0-9_]+\\])*([_\\.][a-zA-Z0-9_\\-]*(\\[[a-zA-Z0-9_]+\\])*)*");

	@Override
	public List<IdentValidationError> validate(IAsap2TreeElement target) {
		List<IdentValidationError> error = new ArrayList<IdentValidationError>();
		Class<?> currentClass = target.getClass();
		Field[] fields = currentClass.getDeclaredFields();
		while (true) {
			for (Field f : fields) {
				if (f.getAnnotation(Asap2Ident.class) != null && f.getType().equals(String.class)) {
					try {
						validateStringField(f, target);
					} catch (IdentValidationError e) {
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

	private void validateStringField(Field f, IAsap2TreeElement target) throws IdentValidationError {
		f.setAccessible(true);
		try {
			Object sObj = f.get(target);
			if (sObj != null) {
				String s = (String) sObj;
				if (s.length() > MAX_IDENT) {
					throw new IdentValidationError(f, s, target, "Exceeding MAX_IDENT(" + MAX_IDENT + ")");
				}

				if (!isValidPartialString(s)) {
					throw new IdentValidationError(f, s, target,
							"Exceeding MAX_PARTIAL_IDENT(" + MAX_PARTIAL_IDENT + ")");
				}

				if (!IDENT_PATTERN.matcher(s).matches()) {
					throw new IdentValidationError(f, s, target, "Invalid format");
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new IdentValidationError(f, null, target, e);
		}
	}

	private boolean isValidPartialString(String s) {
		String[] partialIdent = s.split("\\.");
		for (int i = 0; i < partialIdent.length; i++) {
			if (partialIdent[i].length() > MAX_PARTIAL_IDENT) {
				return false;
			}
		}
		return true;
	}
}
