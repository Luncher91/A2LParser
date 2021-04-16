package net.alenzen.a2l;

import java.io.IOException;

public class Module extends ModuleSubBlocks implements IA2LWriteable {
	private String name;
	private String longIdentifier;

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

	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("MODULE", name, A2LWriter.toA2LString(longIdentifier));
		writer.indent();
		
		super.writeTo(writer);
		
		writer.dedent();
		writer.writelnEnd("MODULE");
	}
}
