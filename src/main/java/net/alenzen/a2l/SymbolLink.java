package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class SymbolLink implements IA2LWriteable {
	private String symbolName;
	private long offset;

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("SYMBOL_LINK", A2LWriter.toA2LString(symbolName), Long.toString(offset));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SymbolLink that = (SymbolLink) o;
		return offset == that.offset && Objects.equals(symbolName, that.symbolName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(symbolName, offset);
	}
}
