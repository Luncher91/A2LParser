package net.alenzen.a2l;

import java.io.IOException;

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
		// TODO Auto-generated method stub

	}
}
