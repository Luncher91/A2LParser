package net.alenzen.a2l;

import java.util.List;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.CalibrationAccess;
import net.alenzen.a2l.enums.CharacteristicType;

public class Characteristic {
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
	private String symbolLink;
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

	public String getSymbolLink() {
		return symbolLink;
	}

	public void setSymbolLink(String symbolLink) {
		this.symbolLink = symbolLink;
	}

	public VirtualCharacteristic getVirtualCharacteristic() {
		return virtualCharacteristic;
	}

	public void setVirtualCharacteristic(VirtualCharacteristic virtualCharacteristic) {
		this.virtualCharacteristic = virtualCharacteristic;
	}
}
