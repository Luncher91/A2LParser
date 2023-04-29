package net.alenzen.a2l.indexes;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import net.alenzen.a2l.IAsap2TreeElement;

public class ReferenceResolverStackEntry {
	private Stack<IAsap2TreeElement> nodesToVisit;
	private IAsap2TreeElement node;
	private Map<String, Map<String, Object>> indexes;

	public Stack<IAsap2TreeElement> getNodesToVisit() {
		return nodesToVisit;
	}

	public void setNodesToVisit(Stack<IAsap2TreeElement> nodesToVisit) {
		this.nodesToVisit = nodesToVisit;
	}

	public IAsap2TreeElement getNode() {
		return node;
	}

	public void setNode(IAsap2TreeElement node) {
		this.node = node;
	}

	public Map<String, Map<String, Object>> getIndexes() {
		return indexes;
	}

	public void setIndexes(Map<String, Map<String, Object>> indexes) {
		this.indexes = indexes;
	}

	public void generateIndexes()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		this.indexes = new HashMap<String, Map<String, Object>>();
		Class<?> currentClass = node.getClass();
		while (currentClass != null) {
			Field[] fields = currentClass.getDeclaredFields();
			for (Field f : fields) {
				CreateIndex annotation = f.getAnnotation(CreateIndex.class);
				if (annotation != null && fieldIsCollection(f)) {
					Map<String, Object> newIndex = generateIndex(f, annotation.ref());

					if (annotation.nullReference() != null && !annotation.nullReference().isEmpty()) {
						newIndex.put(annotation.nullReference(), null);
					}

					this.indexes.put(annotation.name(), newIndex);
				}
			}

			currentClass = currentClass.getSuperclass();
		}
	}

	private boolean fieldIsCollection(Field f) {
		return Collection.class.isAssignableFrom(f.getType());
	}

	private Map<String, Object> generateIndex(Field f, String ref)
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Map<String, Object> index = new HashMap<String, Object>();
		f.setAccessible(true);
		@SuppressWarnings("unchecked")
		Collection<? extends Object> c = (Collection<? extends Object>) f.get(this.node);

		if (c == null) {
			return index;
		}

		for (Object o : c) {
			Field refField = o.getClass().getDeclaredField(ref);
			refField.setAccessible(true);
			if (refField.get(o) instanceof String) {
				String value = (String) refField.get(o);
				index.put(value, o);
			} else {
				throw new IllegalArgumentException(
						String.format("%s in class %s is not a String!", ref, o.getClass().toGenericString()));
			}
		}

		return index;
	}
}
