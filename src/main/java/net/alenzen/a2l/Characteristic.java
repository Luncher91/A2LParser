package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.CalibrationAccess;
import net.alenzen.a2l.enums.CharacteristicType;

public class Characteristic extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	private String name;
	private String longIdentifier;
	private CharacteristicType type;
	private long address;
	private String deposit;
	private double maxDiff;
	private String conversion;
	private double lowerLimit;
	private double upperLimit;

	private List<Annotation> notes;
	private List<AxisDescr> axisDescriptions;
	private Long bitmask;
	private ByteOrder byteorder;
	private CalibrationAccess access;
	private String comparisonQuantityMeasurment;
	private DependentCharacteristic dependetCharacteristic;
	private boolean discrete = false;
	private String displayIdentifier;
	private Long ecuAddressExtension;
	private ExtendedLimits extendedLimits;
	private String format;
	private FunctionList functions;
	private boolean guardRails = false;
	private List<IfData> ifData;
	private IdentReferenceList mapList;
	private MatrixDim matrixDim;
	private MaxRefresh maxRefresh;
	private Long number;
	private String physUnit;
	private boolean readOnly = false;
	private String memorySegment;
	private Double stepSize;
	private SymbolLink symbolLink;
	private VirtualCharacteristic virtualCharacteristic;

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

	public CharacteristicType getType() {
		return type;
	}

	public void setType(CharacteristicType type) {
		this.type = type;
	}

	public long getAddress() {
		return address;
	}

	public void setAddress(long address) {
		this.address = address;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public double getMaxDiff() {
		return maxDiff;
	}

	public void setMaxDiff(double maxDiff) {
		this.maxDiff = maxDiff;
	}

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public List<Annotation> getNotes() {
		return notes;
	}

	public void setNotes(List<Annotation> notes) {
		this.notes = notes;
	}

	public List<AxisDescr> getAxisDescriptions() {
		return axisDescriptions;
	}

	public void setAxisDescriptions(List<AxisDescr> axisDescriptions) {
		this.axisDescriptions = axisDescriptions;
	}

	public Long getBitmask() {
		return bitmask;
	}

	public void setBitmask(Long bitmask) {
		this.bitmask = bitmask;
	}

	public ByteOrder getByteorder() {
		return byteorder;
	}

	public void setByteorder(ByteOrder byteorder) {
		this.byteorder = byteorder;
	}

	public CalibrationAccess getAccess() {
		return access;
	}

	public void setAccess(CalibrationAccess access) {
		this.access = access;
	}

	public String getComparisonQuantityMeasurment() {
		return comparisonQuantityMeasurment;
	}

	public void setComparisonQuantityMeasurment(String comparisonQuantityMeasurment) {
		this.comparisonQuantityMeasurment = comparisonQuantityMeasurment;
	}

	public DependentCharacteristic getDependetCharacteristic() {
		return dependetCharacteristic;
	}

	public void setDependetCharacteristic(DependentCharacteristic dependetCharacteristic) {
		this.dependetCharacteristic = dependetCharacteristic;
	}

	public boolean isDiscrete() {
		return discrete;
	}

	public void setDiscrete(boolean discrete) {
		this.discrete = discrete;
	}

	public String getDisplayIdentifier() {
		return displayIdentifier;
	}

	public void setDisplayIdentifier(String displayIdentifier) {
		this.displayIdentifier = displayIdentifier;
	}

	public Long getEcuAddressExtension() {
		return ecuAddressExtension;
	}

	public void setEcuAddressExtension(Long ecuAddressExtension) {
		this.ecuAddressExtension = ecuAddressExtension;
	}

	public ExtendedLimits getExtendedLimits() {
		return extendedLimits;
	}

	public void setExtendedLimits(ExtendedLimits extendedLimits) {
		this.extendedLimits = extendedLimits;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public FunctionList getFunctions() {
		return functions;
	}

	public void setFunctions(FunctionList functions) {
		this.functions = functions;
	}

	public boolean isGuardRails() {
		return guardRails;
	}

	public void setGuardRails(boolean guardRails) {
		this.guardRails = guardRails;
	}

	public List<IfData> getIfData() {
		return ifData;
	}

	public void setIfData(List<IfData> ifData) {
		this.ifData = ifData;
	}

	public IdentReferenceList getMapList() {
		return mapList;
	}

	public void setMapList(IdentReferenceList mapList) {
		this.mapList = mapList;
	}

	public MatrixDim getMatrixDim() {
		return matrixDim;
	}

	public void setMatrixDim(MatrixDim matrixDim) {
		this.matrixDim = matrixDim;
	}

	public MaxRefresh getMaxRefresh() {
		return maxRefresh;
	}

	public void setMaxRefresh(MaxRefresh maxRefresh) {
		this.maxRefresh = maxRefresh;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getPhysUnit() {
		return physUnit;
	}

	public void setPhysUnit(String physUnit) {
		this.physUnit = physUnit;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getMemorySegment() {
		return memorySegment;
	}

	public void setMemorySegment(String memorySegment) {
		this.memorySegment = memorySegment;
	}

	public Double getStepSize() {
		return stepSize;
	}

	public void setStepSize(Double stepSize) {
		this.stepSize = stepSize;
	}

	public SymbolLink getSymbolLink() {
		return symbolLink;
	}

	public void setSymbolLink(SymbolLink symbolLink) {
		this.symbolLink = symbolLink;
	}

	public VirtualCharacteristic getVirtualCharacteristic() {
		return virtualCharacteristic;
	}

	public void setVirtualCharacteristic(VirtualCharacteristic virtualCharacteristic) {
		this.virtualCharacteristic = virtualCharacteristic;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("CHARACTERISTIC", name, A2LWriter.toA2LString(longIdentifier), type.toString(),
				"0x" + Long.toHexString(address), deposit, Double.toString(maxDiff), conversion,
				Double.toString(lowerLimit), Double.toString(upperLimit));
		writer.indent();

		writer.write(notes);
		writer.write(axisDescriptions);

		if (bitmask != null) {
			writer.writelnSpaced("BIT_MASK", "0x" + Long.toHexString(bitmask));
		}

		writer.write(byteorder);
		writer.write(access);

		if (comparisonQuantityMeasurment != null) {
			writer.writelnSpaced("COMPARISON_QUANTITY", comparisonQuantityMeasurment);
		}

		writer.write(dependetCharacteristic);

		if (discrete) {
			writer.writeln("DISCRETE");
		}

		if (displayIdentifier != null) {
			writer.writelnSpaced("DISPLAY_IDENTIFIER", displayIdentifier);
		}

		if (ecuAddressExtension != null) {
			writer.writelnSpaced("ECU_ADDRESS_EXTENSION", "0x" + Long.toHexString(ecuAddressExtension));
		}

		writer.write(extendedLimits);

		if (format != null) {
			writer.writelnSpaced("FORMAT", A2LWriter.toA2LString(format));
		}

		writer.write(functions);

		if (guardRails) {
			writer.writeln("GUARD_RAILS");
		}

		writer.write(ifData);

		if (mapList != null && mapList.size() > 0) {
			mapList.toA2lAsBlock(writer, "MAP_LIST");
		}

		writer.write(matrixDim);
		writer.write(maxRefresh);

		if (number != null) {
			writer.writelnSpaced("NUMBER", number.toString());
		}

		if (physUnit != null) {
			writer.writelnSpaced("PHYS_UNIT", A2LWriter.toA2LString(physUnit));
		}

		if (readOnly) {
			writer.writeln("READ_ONLY");
		}

		if (memorySegment != null) {
			writer.writelnSpaced("REF_MEMORY_SEGMENT", memorySegment);
		}

		if (stepSize != null) {
			writer.writelnSpaced("STEP_SIZE", Double.toString(stepSize));
		}

		writer.write(symbolLink);
		writer.write(virtualCharacteristic);

		writer.dedent();
		writer.writelnEnd("CHARACTERISTIC");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Characteristic that = (Characteristic) o;
		return address == that.address && Double.compare(that.maxDiff, maxDiff) == 0
				&& Double.compare(that.lowerLimit, lowerLimit) == 0 && Double.compare(that.upperLimit, upperLimit) == 0
				&& discrete == that.discrete && guardRails == that.guardRails && readOnly == that.readOnly
				&& Objects.equals(name, that.name) && Objects.equals(longIdentifier, that.longIdentifier)
				&& type == that.type && Objects.equals(deposit, that.deposit)
				&& Objects.equals(conversion, that.conversion) && Objects.equals(notes, that.notes)
				&& Objects.equals(axisDescriptions, that.axisDescriptions) && Objects.equals(bitmask, that.bitmask)
				&& byteorder == that.byteorder && access == that.access
				&& Objects.equals(comparisonQuantityMeasurment, that.comparisonQuantityMeasurment)
				&& Objects.equals(dependetCharacteristic, that.dependetCharacteristic)
				&& Objects.equals(displayIdentifier, that.displayIdentifier)
				&& Objects.equals(ecuAddressExtension, that.ecuAddressExtension)
				&& Objects.equals(extendedLimits, that.extendedLimits) && Objects.equals(format, that.format)
				&& Objects.equals(functions, that.functions) && Objects.equals(ifData, that.ifData)
				&& Objects.equals(mapList, that.mapList) && Objects.equals(matrixDim, that.matrixDim)
				&& Objects.equals(maxRefresh, that.maxRefresh) && Objects.equals(number, that.number)
				&& Objects.equals(physUnit, that.physUnit) && Objects.equals(memorySegment, that.memorySegment)
				&& Objects.equals(stepSize, that.stepSize) && Objects.equals(symbolLink, that.symbolLink)
				&& Objects.equals(virtualCharacteristic, that.virtualCharacteristic);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, type, address, deposit, maxDiff, conversion, lowerLimit, upperLimit,
				notes, axisDescriptions, bitmask, byteorder, access, comparisonQuantityMeasurment,
				dependetCharacteristic, discrete, displayIdentifier, ecuAddressExtension, extendedLimits, format,
				functions, guardRails, ifData, mapList, matrixDim, maxRefresh, number, physUnit, readOnly,
				memorySegment, stepSize, symbolLink, virtualCharacteristic);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.extendedLimits);
		Asap2FileIterator.addIfNotNull(subNodes, this.axisDescriptions);
		Asap2FileIterator.addIfNotNull(subNodes, this.ifData);
		Asap2FileIterator.addIfNotNull(subNodes, this.notes);
		Asap2FileIterator.addIfNotNull(subNodes, this.functions);
		Asap2FileIterator.addIfNotNull(subNodes, this.mapList);
		Asap2FileIterator.addIfNotNull(subNodes, this.dependetCharacteristic);
		Asap2FileIterator.addIfNotNull(subNodes, this.matrixDim);
		Asap2FileIterator.addIfNotNull(subNodes, this.maxRefresh);
		Asap2FileIterator.addIfNotNull(subNodes, this.symbolLink);
		Asap2FileIterator.addIfNotNull(subNodes, this.virtualCharacteristic);
		return subNodes;
	}
}
