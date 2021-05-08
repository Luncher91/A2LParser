package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


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
		writer.writelnBeginSpaced("VIRTUAL_CHARACTERISTIC", A2LWriter.toA2LString(formula));
		writer.indent();
		
		if(characterstics != null) {
			for(String s : characterstics) {
				writer.writeln(s);
			}
		}
		
		writer.dedent();
		writer.writelnEnd("VIRTUAL_CHARACTERISTIC");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VirtualCharacteristic that = (VirtualCharacteristic) o;
		return Objects.equals(formula, that.formula) && Objects.equals(characterstics, that.characterstics);
	}

	@Override
	public int hashCode() {
		return Objects.hash(formula, characterstics);
	}
}
