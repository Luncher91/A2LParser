package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.DataType;

public class Measurement implements IA2LWriteable {
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

	enum LayoutIndexMode implements IA2LWriteable {
		ROW_DIR, COLUMN_DIR;

		@Override
		public void writeTo(A2LWriter writer) throws IOException {
			writer.writelnSpaced("LAYOUT", this.name());
		}
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("MEASUREMENT", name, A2LWriter.toA2LString(longIdentifier), datatype.name(),
				conversion, Long.toString(resolution), Double.toString(accuracy), Double.toString(lowerLimit),
				Double.toString(upperLimit));
		writer.indent();

		writer.write(annotations);
		
		if(arraySize != null) {
			writer.writelnSpaced("ARRAY_SIZE", Long.toString(arraySize));
		}
		
		if(bitMask != null) {
			writer.writelnSpaced("BIT_MASK", "0x" + Long.toHexString(bitMask));
		}
		
		writer.write(bitOperation);
		writer.write(byteorder);
		
		if(discrete) {
			writer.writeln("DISCRETE");
		}
		
		if(displayIdentifier != null) {
			writer.writelnSpaced("DISPLAY_IDENTIFIER", displayIdentifier);
		}
		
		if(ecuAddress != null) {
			writer.writelnSpaced("ECU_ADDRESS", "0x" + Long.toHexString(ecuAddress));
		}
		
		if(ecuAddressExtension != null) {
			writer.writelnSpaced("ECU_ADDRESS_EXTENSION", "0x" + Long.toHexString(ecuAddressExtension));
		}
		
		if(errorMask != null) {
			writer.writelnSpaced("ERROR_MASK", "0x" + Long.toHexString(errorMask));
		}
		
		if(format != null) {
			writer.writelnSpaced("FORMAT", A2LWriter.toA2LString(format));
		}
		
		writer.write(functionList);
		writer.write(ifDatas);
		writer.write(layout);
		writer.write(matrixDim);
		writer.write(maxRefresh);
		
		if(physUnit != null) {
			writer.writelnSpaced("PHYS_UNIT", A2LWriter.toA2LString(physUnit));
		}
		
		if(readWrite) {
			writer.writeln("READ_WRITE");
		}
		
		if(memorySegment != null) {
			writer.writelnSpaced("REF_MEMORY_SEGMENT", memorySegment);
		}
		
		writer.write(symbolLink);
		writer.write(virtual);
		
		writer.dedent();
		writer.writelnEnd("MEASUREMENT");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Measurement that = (Measurement) o;
		return resolution == that.resolution && Double.compare(that.accuracy, accuracy) == 0
				&& Double.compare(that.lowerLimit, lowerLimit) == 0 && Double.compare(that.upperLimit, upperLimit) == 0
				&& discrete == that.discrete && readWrite == that.readWrite && Objects.equals(name, that.name)
				&& Objects.equals(longIdentifier, that.longIdentifier) && datatype == that.datatype && Objects
				.equals(conversion, that.conversion) && Objects.equals(annotations, that.annotations) && Objects
				.equals(arraySize, that.arraySize) && Objects.equals(bitMask, that.bitMask) && Objects
				.equals(bitOperation, that.bitOperation) && byteorder == that.byteorder && Objects
				.equals(displayIdentifier, that.displayIdentifier) && Objects.equals(ecuAddress, that.ecuAddress)
				&& Objects.equals(ecuAddressExtension, that.ecuAddressExtension) && Objects
				.equals(errorMask, that.errorMask) && Objects.equals(format, that.format) && Objects
				.equals(functionList, that.functionList) && Objects.equals(ifDatas, that.ifDatas)
				&& layout == that.layout && Objects.equals(matrixDim, that.matrixDim) && Objects
				.equals(maxRefresh, that.maxRefresh) && Objects.equals(physUnit, that.physUnit) && Objects
				.equals(memorySegment, that.memorySegment) && Objects.equals(symbolLink, that.symbolLink) && Objects
				.equals(virtual, that.virtual);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, datatype, conversion, resolution, accuracy, lowerLimit, upperLimit,
				annotations, arraySize, bitMask, bitOperation, byteorder, discrete, displayIdentifier, ecuAddress,
				ecuAddressExtension, errorMask, format, functionList, ifDatas, layout, matrixDim, maxRefresh, physUnit,
				readWrite, memorySegment, symbolLink, virtual);
	}
}
