package net.alenzen.a2l;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.ByteOrderMark;

public class A2LWriter {
	public static final String SPACE = " ";
	public static final String BEGIN = "/begin";
	public static final String END = "/end";
	
	private static final String DEFAULT_LINE_BREAK_STRING = "\r\n";
	private static final String DEFAULT_INDENTATION_STRING = "    ";

	private byte[] LINE_BREAK;
	private byte[] indentation;

	private Charset charset = StandardCharsets.UTF_8;

	private OutputStream os;

	private int indentationDepth = 0;

	public A2LWriter(OutputStream os) {
		this.os = os;
		LINE_BREAK = DEFAULT_LINE_BREAK_STRING.getBytes(this.charset);
		indentation = DEFAULT_INDENTATION_STRING.getBytes(this.charset);
	}

	public A2LWriter(OutputStream os, Charset charset) {
		this.os = os;
		this.charset = charset;
		LINE_BREAK = DEFAULT_LINE_BREAK_STRING.getBytes(this.charset);
		indentation = DEFAULT_INDENTATION_STRING.getBytes(this.charset);
	}

	public void setIndenation(String newIndentation) {
		indentation = newIndentation.getBytes(this.charset);
	}

	private byte[] getBOMBytes() {
		if (charset.equals(StandardCharsets.UTF_8)) {
			return ByteOrderMark.UTF_8.getBytes();
		}

		if (charset.equals(StandardCharsets.UTF_16LE)) {
			return ByteOrderMark.UTF_16LE.getBytes();
		}

		if (charset.equals(StandardCharsets.UTF_16BE)) {
			return ByteOrderMark.UTF_16BE.getBytes();
		}

		if (charset.equals(Charset.forName("UTF-32LE"))) {
			return ByteOrderMark.UTF_32LE.getBytes();
		}

		if (charset.equals(Charset.forName("UTF-32BE"))) {
			return ByteOrderMark.UTF_32BE.getBytes();
		}

		return new byte[0];
	}

	public void writeBOM() throws IOException {
		os.write(getBOMBytes());
	}

	public void writeln(String s) throws IOException {
		if(s == null) return;
		
		writeIndetation();
		os.write(s.getBytes(charset));
		writeln();
	}

	public void writeln() throws IOException {
		os.write(LINE_BREAK);
	}
	
	public void write(String s) throws IOException {
		if(s == null) return;
		
		os.write(s.getBytes(charset));
	}
	
	public void writelnBeginSpaced(String... strings) throws IOException {
		if(strings == null) return;
		
		writeIndetation();
		write(A2LWriter.BEGIN + A2LWriter.SPACE + String.join(A2LWriter.SPACE, strings));
		writeln();
	}
	
	public void writelnEnd(String blockType) throws IOException {
		if(blockType == null) return;
		
		writeIndetation();
		write(A2LWriter.END + A2LWriter.SPACE + blockType);
		writeln();
	}
	
	public void writelnSpaced(String... strings) throws IOException {
		if(strings == null) return;
		
		writeIndetation();
		write(String.join(A2LWriter.SPACE, strings));
		writeln();
	}

	public void write(long versionNo) throws IOException {
		os.write(Long.toString(versionNo).getBytes(charset));
	}

	public void writeA2LString(String s) throws IOException {
		write(A2LWriter.toA2LString(s));
	}
	
	public void writeIndetation() throws IOException {
		for (int i = 0; i < this.indentationDepth; i++) {
			os.write(indentation);
		}
	}
	
	public static String toA2LString(String a2lString) {
		if(a2lString == null) return null;
		
		String escapedstring = escapeStringCharacters(a2lString);
		return "\"" + escapedstring + "\"";
	}

	private static String escapeStringCharacters(String a2lString) {
		String replacedStr = a2lString;
		replacedStr = replacedStr.replaceAll("\\\\", "\\\\\\\\");
		// escape "
		replacedStr = replacedStr.replaceAll("\"", "\\\\\"");
		
		// escape '
		replacedStr = replacedStr.replaceAll("'", "\\\\'");

		// escape \n
		replacedStr = replacedStr.replaceAll("\n", "\\\\n");
		
		// escape \r
		replacedStr = replacedStr.replaceAll("\r", "\\\\r");
		
		// escape \t
		replacedStr = replacedStr.replaceAll("\t", "\\\\t");
		
		return replacedStr;
	}

	public int getIndentationDepth() {
		return indentationDepth;
	}

	public void indent() {
		this.indentationDepth++;
	}

	public void dedent() {
		this.indentationDepth--;
	}

	public void setIndentationDepth(int indentation) {
		this.indentationDepth = indentation;
	}

	public Charset getCharset() {
		return charset;
	}
	
	public void write(IA2LWriteable writeable) throws IOException {
		if(writeable != null) {
			writeable.writeTo(this);
		}
	}

	public void write(List<? extends IA2LWriteable> writeables) throws IOException {
		if(writeables != null) {
			for(IA2LWriteable m : writeables) {
				m.writeTo(this);
			}
		}
	}

	public void writeMultiLine(String content) throws IOException {
		String[] lines = content.split("\\r?\\n");
		for(String l : lines) {
			writeln(l.trim());
		}
	}
}
