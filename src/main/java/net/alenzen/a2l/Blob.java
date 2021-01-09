package net.alenzen.a2l;

import java.io.IOException;

public class Blob implements IA2LWriteable {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writeln(content);
	}
}
