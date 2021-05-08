package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class DependentCharacteristic implements IA2LWriteable {
	private String formula;
	private List<String> characterstics;

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public List<String> getCharacterstics() {
		return characterstics;
	}

	public void setCharacterstics(List<String> characterstics) {
		this.characterstics = characterstics;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("DEPENDENT_CHARACTERISTIC", A2LWriter.toA2LString(formula));
		writer.indent();

		if (characterstics != null) {
			for (String s : characterstics) {
				if (s != null) {
					writer.writeln(s);
				}
			}
		}

		writer.dedent();
		writer.writelnEnd("DEPENDENT_CHARACTERISTIC");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DependentCharacteristic that = (DependentCharacteristic) o;
		return Objects.equals(formula, that.formula) && Objects.equals(characterstics, that.characterstics);
	}

	@Override
	public int hashCode() {
		return Objects.hash(formula, characterstics);
	}
}
