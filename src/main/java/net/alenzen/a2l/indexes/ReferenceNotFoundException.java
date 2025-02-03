package net.alenzen.a2l.indexes;

import java.util.List;
import java.util.NoSuchElementException;

import net.alenzen.a2l.IAsap2TreeElement;

public class ReferenceNotFoundException extends NoSuchElementException {
	private static final long serialVersionUID = -4277935098528086486L;

	private final String reference;
	private final IAsap2TreeElement source;
	private final List<String> indexes;

	public ReferenceNotFoundException(String referenceString, IAsap2TreeElement node, List<String> searchedIndexes) {
		this.reference = referenceString;
		this.source = node;
		this.indexes = searchedIndexes;
	}

	public String getReference() {
		return reference;
	}

	public IAsap2TreeElement getSource() {
		return source;
	}

	public List<String> getIndexes() {
		return indexes;
	}

	@Override
	public String toString() {
		return String.format("Cannot find reference '%s' of '%s' in index(es) '%s'", reference, source.toString(),
				String.join(",", indexes));
	}
}
