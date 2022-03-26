package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Function extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("FUNCTION", name, A2LWriter.toA2LString(longIdentifier));
		writer.indent();

		writer.write(annotations);
		
		toA2lIfAvailable(writer, defCharacteristics, "DEF_CHARACTERISTIC");

		if (functionVersion != null) {
			writer.writelnSpaced("FUNCTION_VERSION", A2LWriter.toA2LString(functionVersion));
		}
		
		writer.write(ifDatas);
		toA2lIfAvailable(writer, inMeasurments, "IN_MEASUREMENT");
		toA2lIfAvailable(writer, locMeasurments, "LOC_MEASUREMENT");
		toA2lIfAvailable(writer, outMeasurments, "OUT_MEASUREMENT");
		toA2lIfAvailable(writer, refCharacteristics, "REF_CHARACTERISTIC");
		toA2lIfAvailable(writer, subFunctions, "SUB_FUNCTION");

		writer.dedent();
		writer.writelnEnd("FUNCTION");
	}
	
	private static void toA2lIfAvailable(A2LWriter writer, IdentReferenceList lst, String name) throws IOException {
		if (lst != null && lst.size() > 0) {
			lst.toA2lAsBlock(writer, name);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Function function = (Function) o;
		return Objects.equals(name, function.name) && Objects.equals(longIdentifier, function.longIdentifier) && Objects
				.equals(annotations, function.annotations) && Objects
				.equals(defCharacteristics, function.defCharacteristics) && Objects
				.equals(functionVersion, function.functionVersion) && Objects.equals(ifDatas, function.ifDatas)
				&& Objects.equals(inMeasurments, function.inMeasurments) && Objects
				.equals(locMeasurments, function.locMeasurments) && Objects
				.equals(outMeasurments, function.outMeasurments) && Objects
				.equals(refCharacteristics, function.refCharacteristics) && Objects
				.equals(subFunctions, function.subFunctions);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(name, longIdentifier, annotations, defCharacteristics, functionVersion, ifDatas, inMeasurments,
						locMeasurments, outMeasurments, refCharacteristics, subFunctions);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.annotations);
		Asap2FileIterator.addIfNotNull(subNodes, this.defCharacteristics);
		Asap2FileIterator.addIfNotNull(subNodes, this.ifDatas);
		Asap2FileIterator.addIfNotNull(subNodes, this.inMeasurments);
		Asap2FileIterator.addIfNotNull(subNodes, this.locMeasurments);
		Asap2FileIterator.addIfNotNull(subNodes, this.outMeasurments);
		Asap2FileIterator.addIfNotNull(subNodes, this.refCharacteristics);
		Asap2FileIterator.addIfNotNull(subNodes, this.subFunctions);
		return subNodes;
	}
}
