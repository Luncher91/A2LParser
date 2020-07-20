package net.alenzen.a2l;

import java.util.List;

public class Group {
	private String groupName;
	private String longIdentifier;

	// optional parameters
	private List<Annotation> annotations;
	private FunctionList functionList;
	private List<IfData> ifDatas;
	private IdentReferenceList refCharacteristics;
	private IdentReferenceList refMeasurements;
	private boolean root;
	private IdentReferenceList subGroups;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getLongIdentifier() {
		return longIdentifier;
	}

	public void setLongIdentifier(String longIdentifier) {
		this.longIdentifier = longIdentifier;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public FunctionList getFunctionList() {
		return functionList;
	}

	public void setFunctionList(FunctionList functionList) {
		this.functionList = functionList;
	}

	public List<IfData> getIfDatas() {
		return ifDatas;
	}

	public void setIfDatas(List<IfData> ifDatas) {
		this.ifDatas = ifDatas;
	}

	public IdentReferenceList getRefCharacteristics() {
		return refCharacteristics;
	}

	public void setRefCharacteristics(IdentReferenceList refCharacteristics) {
		this.refCharacteristics = refCharacteristics;
	}

	public IdentReferenceList getRefMeasurements() {
		return refMeasurements;
	}

	public void setRefMeasurements(IdentReferenceList refMeasurements) {
		this.refMeasurements = refMeasurements;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public IdentReferenceList getSubGroups() {
		return subGroups;
	}

	public void setSubGroups(IdentReferenceList subGroups) {
		this.subGroups = subGroups;
	}
}
