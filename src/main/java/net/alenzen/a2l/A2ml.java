package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class A2ml implements IA2LWriteable {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writeMultiLine(content);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		A2ml a2ml = (A2ml) o;
		return Objects.equals(content, a2ml.content);
	}

	@Override
	public int hashCode() {
		return Objects.hash(content);
	}
}
