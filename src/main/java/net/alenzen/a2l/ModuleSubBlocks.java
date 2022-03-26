package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ModuleSubBlocks extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	// optional parameters
	private List<A2ml> a2ml;
	private List<AxisPts> axisPts;
	private List<Characteristic> characteristics;
	private List<CompuMethod> compuMethods;
	private List<CompuTab> compuTabs;
	private List<CompuVTab> compuVTabs;
	private List<CompuVTabRange> compuVTabRanges;
	private Frame frame;
	private List<Function> functions;
	private List<Group> groups;
	private List<IfData> ifDatas;
	private List<Measurement> measurements;
	private ModCommon modCommon;
	private ModPar modPar;
	private List<RecordLayout> recordLayouts;
	private List<Unit> units;
	private List<UserRights> userRights;
	private VariantCoding variantCoding;
	private List<Blob> blobs;
	
	private List<ModuleSubBlocks> included;

	public List<A2ml> getA2ml() {
		return a2ml;
	}

	public void setA2ml(List<A2ml> a2ml) {
		this.a2ml = a2ml;
	}

	public List<AxisPts> getAxisPts() {
		return axisPts;
	}

	public void setAxisPts(List<AxisPts> axisPts) {
		this.axisPts = axisPts;
	}

	public List<Characteristic> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<Characteristic> characteristics) {
		this.characteristics = characteristics;
	}

	public List<CompuMethod> getCompuMethods() {
		return compuMethods;
	}

	public void setCompuMethods(List<CompuMethod> compuMethods) {
		this.compuMethods = compuMethods;
	}

	public List<CompuTab> getCompuTabs() {
		return compuTabs;
	}

	public void setCompuTabs(List<CompuTab> compuTabs) {
		this.compuTabs = compuTabs;
	}

	public List<CompuVTab> getCompuVTabs() {
		return compuVTabs;
	}

	public void setCompuVTabs(List<CompuVTab> compuVTabs) {
		this.compuVTabs = compuVTabs;
	}

	public List<CompuVTabRange> getCompuVTabRanges() {
		return compuVTabRanges;
	}

	public void setCompuVTabRanges(List<CompuVTabRange> compuVTabRanges) {
		this.compuVTabRanges = compuVTabRanges;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public List<Function> getFunctions() {
		return functions;
	}

	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<IfData> getIfDatas() {
		return ifDatas;
	}

	public void setIfDatas(List<IfData> ifDatas) {
		this.ifDatas = ifDatas;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public ModCommon getModCommon() {
		return modCommon;
	}

	public void setModCommon(ModCommon modCommon) {
		this.modCommon = modCommon;
	}

	public ModPar getModPar() {
		return modPar;
	}

	public void setModPar(ModPar modPar) {
		this.modPar = modPar;
	}

	public List<RecordLayout> getRecordLayouts() {
		return recordLayouts;
	}

	public void setRecordLayouts(List<RecordLayout> recordLayouts) {
		this.recordLayouts = recordLayouts;
	}

	public List<Unit> getUnits() {
		return units;
	}

	public void setUnits(List<Unit> units) {
		this.units = units;
	}

	public List<UserRights> getUserRights() {
		return userRights;
	}

	public void setUserRights(List<UserRights> userRights) {
		this.userRights = userRights;
	}

	public VariantCoding getVariantCoding() {
		return variantCoding;
	}

	public void setVariantCoding(VariantCoding variantCoding) {
		this.variantCoding = variantCoding;
	}

	public List<Blob> getBlobs() {
		return blobs;
	}

	public void setBlobs(List<Blob> blobs) {
		this.blobs = blobs;
	}
	
	public List<ModuleSubBlocks> getIncluded() {
		return included;
	}
	
	public void setIncluded(List<ModuleSubBlocks> included) {
		this.included = included;
	}

	public void writeTo(A2LWriter writer) throws IOException {
		writer.write(a2ml);
		writer.write(axisPts);
		writer.write(characteristics);
		writer.write(compuMethods);
		writer.write(compuTabs);
		writer.write(compuVTabs);
		writer.write(compuVTabRanges);
		writer.write(frame);
		writer.write(functions);
		writer.write(groups);
		writer.write(ifDatas);
		writer.write(measurements);
		writer.write(modCommon);
		writer.write(modPar);
		writer.write(recordLayouts);
		writer.write(units);
		writer.write(userRights);
		writer.write(variantCoding);
		writer.write(blobs);
		
		writer.write(getIncluded());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ModuleSubBlocks that = (ModuleSubBlocks) o;
		return Objects.equals(a2ml, that.a2ml) && Objects.equals(axisPts, that.axisPts) && Objects
				.equals(characteristics, that.characteristics) && Objects.equals(compuMethods, that.compuMethods)
				&& Objects.equals(compuTabs, that.compuTabs) && Objects.equals(compuVTabs, that.compuVTabs) && Objects
				.equals(compuVTabRanges, that.compuVTabRanges) && Objects.equals(frame, that.frame) && Objects
				.equals(functions, that.functions) && Objects.equals(groups, that.groups) && Objects
				.equals(ifDatas, that.ifDatas) && Objects.equals(measurements, that.measurements) && Objects
				.equals(modCommon, that.modCommon) && Objects.equals(modPar, that.modPar) && Objects
				.equals(recordLayouts, that.recordLayouts) && Objects.equals(units, that.units) && Objects
				.equals(userRights, that.userRights) && Objects.equals(variantCoding, that.variantCoding) && Objects
				.equals(blobs, that.blobs) && Objects.equals(included, that.included);
	}

	@Override
	public int hashCode() {
		return Objects.hash(a2ml, axisPts, characteristics, compuMethods, compuTabs, compuVTabs, compuVTabRanges, frame,
				functions, groups, ifDatas, measurements, modCommon, modPar, recordLayouts, units, userRights,
				variantCoding, blobs, included);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.a2ml);
		Asap2FileIterator.addIfNotNull(subNodes, this.axisPts);
		Asap2FileIterator.addIfNotNull(subNodes, this.characteristics);
		Asap2FileIterator.addIfNotNull(subNodes, this.compuMethods);
		Asap2FileIterator.addIfNotNull(subNodes, this.compuTabs);
		Asap2FileIterator.addIfNotNull(subNodes, this.compuVTabs);
		Asap2FileIterator.addIfNotNull(subNodes, this.compuVTabRanges);
		Asap2FileIterator.addIfNotNull(subNodes, this.frame);
		Asap2FileIterator.addIfNotNull(subNodes, this.functions);
		Asap2FileIterator.addIfNotNull(subNodes, this.groups);
		Asap2FileIterator.addIfNotNull(subNodes, this.ifDatas);
		Asap2FileIterator.addIfNotNull(subNodes, this.measurements);
		Asap2FileIterator.addIfNotNull(subNodes, this.modCommon);
		Asap2FileIterator.addIfNotNull(subNodes, this.modPar);
		Asap2FileIterator.addIfNotNull(subNodes, this.recordLayouts);
		Asap2FileIterator.addIfNotNull(subNodes, this.units);
		Asap2FileIterator.addIfNotNull(subNodes, this.userRights);
		Asap2FileIterator.addIfNotNull(subNodes, this.variantCoding);
		Asap2FileIterator.addIfNotNull(subNodes, this.blobs);
		Asap2FileIterator.addIfNotNull(subNodes, this.included);
		return subNodes;
	}
}
