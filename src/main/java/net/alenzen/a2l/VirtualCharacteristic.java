package net.alenzen.a2l;

import java.io.IOException;

public class VirtualCharacteristic implements IA2LWriteable {
	private String formula;
	private IdentReferenceList characterstics;

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public IdentReferenceList getCharacterstics() {
		return characterstics;
	}

	public void setCharacterstics(IdentReferenceList characterstics) {
		this.characterstics = characterstics;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		// TODO Auto-generated method stub

	}
}
