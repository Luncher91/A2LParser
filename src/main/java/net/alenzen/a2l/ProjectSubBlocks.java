package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ProjectSubBlocks implements IA2LWriteable {
	// optional parameters
	private List<Module> modules;
	
	private List<ProjectSubBlocks> included;

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<ProjectSubBlocks> getIncluded() {
		return included;
	}

	public void setIncluded(List<ProjectSubBlocks> included) {
		this.included = included;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.write(getModules());
		writer.write(getIncluded());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProjectSubBlocks that = (ProjectSubBlocks) o;
		return Objects.equals(modules, that.modules) && Objects.equals(included, that.included);
	}

	@Override
	public int hashCode() {
		return Objects.hash(modules, included);
	}
}
