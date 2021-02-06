package net.alenzen.a2l;

import java.io.IOException;

public class Project extends ProjectSubBlocks implements IA2LWriteable {
	private String name;
	private String longIdentifier;

	// optional parameters
	private Header header;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongIdentifier() {
		return longIdentifier;
	}

	public void setLongIdentifier(String longIdentifier) {
		this.longIdentifier = longIdentifier;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("PROJECT", name, A2LWriter.toA2LString(longIdentifier));
		writer.indent();

		writer.write(this.getHeader());
		super.writeTo(writer);

		writer.dedent();
		writer.writelnEnd("PROJECT");
	}
}
