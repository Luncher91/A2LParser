package net.alenzen.a2l.indexes;

import java.util.List;
import java.util.NoSuchElementException;

import net.alenzen.a2l.IAsap2TreeElement;

public class ReferenceNotFoundException extends NoSuchElementException {
	private static final long serialVersionUID = -4277935098528086486L;

	private String reference;
	private IAsap2TreeElement source;
	private List<String> indexes;

	public ReferenceNotFoundException(String referenceString, IAsap2TreeElement node, List<String> searchedIndexes) {
		this.reference = referenceString;
		this.source = node;
		this.indexes = searchedIndexes;
	}

	@Override
	public String toString() {
		return String.format("Cannot find reference '%s' of '%s' in index '%s'", reference, source.toString(),
				String.join(",", indexes));
	}
}
