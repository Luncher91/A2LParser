package net.alenzen.a2l;

import java.util.List;

public class Function {
	private String name;
	private String longIdentifier;

	// optional parameters
	private List<Annotation> annotations;
	private IdentReferenceList defCharacteristics;
	private String functionVersion;
	private List<IfData> ifDatas;
	private IdentReferenceList inMeasurments;
	private IdentReferenceList locMeasurments;
	private IdentReferenceList outMeasurments;
	private IdentReferenceList refCharacteristics;
	private IdentReferenceList subFunctions;

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

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public IdentReferenceList getDefCharacteristics() {
		return defCharacteristics;
	}

	public void setDefCharacteristics(IdentReferenceList defCharacteristics) {
		this.defCharacteristics = defCharacteristics;
	}

	public String getFunctionVersion() {
		return functionVersion;
	}

	public void setFunctionVersion(String functionVersion) {
		this.functionVersion = functionVersion;
	}

	public List<IfData> getIfDatas() {
		return ifDatas;
	}

	public void setIfDatas(List<IfData> ifDatas) {
		this.ifDatas = ifDatas;
	}

	public IdentReferenceList getInMeasurments() {
		return inMeasurments;
	}

	public void setInMeasurments(IdentReferenceList inMeasurments) {
		this.inMeasurments = inMeasurments;
	}

	public IdentReferenceList getLocMeasurments() {
		return locMeasurments;
	}

	public void setLocMeasurments(IdentReferenceList locMeasurments) {
		this.locMeasurments = locMeasurments;
	}

	public IdentReferenceList getOutMeasurments() {
		return outMeasurments;
	}

	public void setOutMeasurments(IdentReferenceList outMeasurments) {
		this.outMeasurments = outMeasurments;
	}

	public IdentReferenceList getRefCharacteristics() {
		return refCharacteristics;
	}

	public void setRefCharacteristics(IdentReferenceList refCharacteristics) {
		this.refCharacteristics = refCharacteristics;
	}

	public IdentReferenceList getSubFunctions() {
		return subFunctions;
	}

	public void setSubFunctions(IdentReferenceList subFunctions) {
		this.subFunctions = subFunctions;
	}
}
