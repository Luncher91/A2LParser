package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class Header implements IA2LWriteable {
	private String comment;

	// optional parameters
	private String projectNo;
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
		
		writer.writelnSpaced("PROJECT_NO", projectNo);
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
		return Objects.equals(comment, header.comment) && Objects.equals(projectNo, header.projectNo) && Objects
				.equals(version, header.version);
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, projectNo, version);
	}
}
