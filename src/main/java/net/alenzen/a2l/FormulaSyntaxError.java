package net.alenzen.a2l;

public class FormulaSyntaxError {
	private int line;
	private int charPosition;
	private String message;

	public FormulaSyntaxError(int line, int charPositionInLine, String msg) {
		this.line = line;
		this.charPosition = charPositionInLine;
		this.message = msg;
	}

	public int getLine() {
		return line;
	}

	public int getCharPosition() {
		return charPosition;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return String.format("%d:%d %s", line, charPosition, message);
	}
}
