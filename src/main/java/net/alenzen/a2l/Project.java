package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.validation.Asap2Ident;
import net.alenzen.a2l.validation.Asap2String;

public class Project extends ProjectSubBlocks {
	@Asap2Ident
	private String name;

	@Asap2String
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("PROJECT", name, A2LWriter.toA2LString(longIdentifier));
		writer.indent();

		writer.write(this.getHeader());
		super.writeTo(writer);

		writer.dedent();
		writer.writelnEnd("PROJECT");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Project project = (Project) o;
		return Objects.equals(name, project.name) && Objects.equals(longIdentifier, project.longIdentifier)
				&& Objects.equals(header, project.header);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), name, longIdentifier, header);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = super.collectSubNodes();

		if (header != null) {
			subNodes.add(header);
		}

		return subNodes;
	}
}
