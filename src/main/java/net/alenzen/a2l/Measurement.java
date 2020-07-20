package net.alenzen.a2l;

import java.util.List;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.DataType;

public class Measurement {
	private String name;
	private String longIdentifier;
	private DataType datatype;
	private String conversion;
	private long resolution;
	private double accuracy;
	private double lowerLimit;
	private double upperLimit;

	// optional parameters
	private List<Annotation> annotations;
	private Long arraySize;
	private Long bitMask;
	private BitOperation bitOperation;
	private ByteOrder byteorder;
	private boolean discrete;
	private String displayIdentifier;
	private Long ecuAddress;
	private Long ecuAddressExtension;
	private Long errorMask;
	private String format;
	private FunctionList functionList;
	private List<IfData> ifDatas;
	private LayoutIndexMode layout;
	private MatrixDim matrixDim;
	private MaxRefresh maxRefresh;
	private String physUnit;
	private boolean readWrite;
	private String memorySegment;
	private SymbolLink symbolLink;
	private Virtual virtual;

	enum LayoutIndexMode {
		ROW_DIR, COLUMN_DIR
	}

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

	public DataType getDatatype() {
		return datatype;
	}

	public void setDatatype(DataType datatype) {
		this.datatype = datatype;
	}

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	public long getResolution() {
		return resolution;
	}

	public void setResolution(long resolution) {
		this.resolution = resolution;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
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

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public Long getArraySize() {
		return arraySize;
	}

	public void setArraySize(Long arraySize) {
		this.arraySize = arraySize;
	}

	public Long getBitMask() {
		return bitMask;
	}

	public void setBitMask(Long bitMask) {
		this.bitMask = bitMask;
	}

	public BitOperation getBitOperation() {
		return bitOperation;
	}

	public void setBitOperation(BitOperation bitOperation) {
		this.bitOperation = bitOperation;
	}

	public ByteOrder getByteorder() {
		return byteorder;
	}

	public void setByteorder(ByteOrder byteorder) {
		this.byteorder = byteorder;
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

	public Long getEcuAddress() {
		return ecuAddress;
	}

	public void setEcuAddress(Long ecuAddress) {
		this.ecuAddress = ecuAddress;
	}

	public Long getEcuAddressExtension() {
		return ecuAddressExtension;
	}

	public void setEcuAddressExtension(Long ecuAddressExtension) {
		this.ecuAddressExtension = ecuAddressExtension;
	}

	public Long getErrorMask() {
		return errorMask;
	}

	public void setErrorMask(Long errorMask) {
		this.errorMask = errorMask;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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

	public LayoutIndexMode getLayout() {
		return layout;
	}

	public void setLayout(LayoutIndexMode layout) {
		this.layout = layout;
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

	public String getPhysUnit() {
		return physUnit;
	}

	public void setPhysUnit(String physUnit) {
		this.physUnit = physUnit;
	}

	public boolean isReadWrite() {
		return readWrite;
	}

	public void setReadWrite(boolean readWrite) {
		this.readWrite = readWrite;
	}

	public String getMemorySegment() {
		return memorySegment;
	}

	public void setMemorySegment(String memorySegment) {
		this.memorySegment = memorySegment;
	}

	public SymbolLink getSymbolLink() {
		return symbolLink;
	}

	public void setSymbolLink(SymbolLink symbolLink) {
		this.symbolLink = symbolLink;
	}

	public Virtual getVirtual() {
		return virtual;
	}

	public void setVirtual(Virtual virtual) {
		this.virtual = virtual;
	}
}
