package net.alenzen.a2l;

public class Asap2File {
	private Version a2mlVersion;
	private Version asap2Version;
	private Project project;

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project p) {
		project = p;
	}

	public Version getAsap2Version() {
		return asap2Version;
	}

	public void setAsap2Version(Version asap2Version) {
		this.asap2Version = asap2Version;
	}

	public Version getA2mlVersion() {
		return a2mlVersion;
	}

	public void setA2mlVersion(Version a2mlVersion) {
		this.a2mlVersion = a2mlVersion;
	}
}
