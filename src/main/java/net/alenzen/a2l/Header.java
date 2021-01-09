package net.alenzen.a2l;

import java.io.IOException;

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
}
