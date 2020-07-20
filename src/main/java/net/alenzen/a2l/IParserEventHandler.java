package net.alenzen.a2l;

public interface IParserEventHandler {
	void log(long lineNumber, long position, String message);
}
