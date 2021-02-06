package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

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
}
