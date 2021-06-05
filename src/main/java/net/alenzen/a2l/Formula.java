package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class Formula extends A2LSerializer implements IA2LWriteable {
	private String fx;

	// optional parameters
	private String gx;

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getGx() {
		return gx;
	}

	public void setGx(String gx) {
		this.gx = gx;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("FORMULA", A2LWriter.toA2LString(fx));
		writer.indent();
		if(gx != null) {
			writer.writelnSpaced("FORMULA_INV", A2LWriter.toA2LString(gx));
		}
		writer.dedent();
		writer.writelnEnd("FORMULA");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Formula formula = (Formula) o;
		return Objects.equals(fx, formula.fx) && Objects.equals(gx, formula.gx);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fx, gx);
	}
}
