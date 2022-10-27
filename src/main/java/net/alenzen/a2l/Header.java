package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.validation.Asap2Ident;
import net.alenzen.a2l.validation.Asap2String;

public class Header extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	@Asap2String
	private String comment;

	// optional parameters
	@Asap2Ident(nullable = true)
	private String projectNo;

	@Asap2String(nullable = true)
	private String version;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("HEADER", A2LWriter.toA2LString(comment));
		writer.indent();

		if(projectNo != null && !projectNo.isEmpty())
			writer.writelnSpaced("PROJECT_NO", projectNo);
		
		if(version != null)
			writer.writelnSpaced("VERSION", A2LWriter.toA2LString(version));

		writer.dedent();
		writer.writelnEnd("HEADER");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Header header = (Header) o;
		return Objects.equals(comment, header.comment) && Objects.equals(projectNo, header.projectNo)
				&& Objects.equals(version, header.version);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, projectNo, version);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
