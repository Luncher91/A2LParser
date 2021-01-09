package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

public class Project implements IA2LWriteable {
	private String name;
	private String longIdentifier;

	// optional parameters
	private Header header;
	private List<Module> modules;

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

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("PROJECT", name, A2LWriter.toA2LString(longIdentifier));
		writer.indent();
		
		writer.write(header);
		writer.write(modules);
		
		writer.dedent();
		writer.writelnEnd("PROJECT");
	}
}
