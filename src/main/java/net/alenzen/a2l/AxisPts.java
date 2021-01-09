package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.CalibrationAccess;
import net.alenzen.a2l.enums.Deposit;
import net.alenzen.a2l.enums.Monotony;

public class AxisPts implements IA2LWriteable {
	private String name;
	private String longIdentifier;
	private long address;
	private String inputQuantitiy;
	private String deposit;
	private double maxDiff;
	private String conversion;
	private long maxAxisPoints;
	private double lowerLimit;
	private double upperLimit;

	private List<Annotation> notes = new ArrayList<Annotation>();
	private ByteOrder byteorder;
	private CalibrationAccess access;
	private Deposit axisPointDeposit;
	private String displayIdentifier;
	private Long ecuAddressExtension;
	private ExtendedLimits extendedLimits;
	private String format;
	private FunctionList functions;
	private boolean guardRails = false;
	private List<IfData> ifData;
	private Monotony monotony;
	private String physUnit;
	private boolean readOnly = false;
	private String memorySegment;
	private Double stepSize;
	private String symbolLink;

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

	public long getAddress() {
		return address;
	}

	public void setAddress(long address) {
		this.address = address;
	}

	public String getInputQuantitiy() {
		return inputQuantitiy;
	}

	public void setInputQuantitiy(String inputQuantitiy) {
		this.inputQuantitiy = inputQuantitiy;
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

	public long getMaxAxisPoints() {
		return maxAxisPoints;
	}

	public void setMaxAxisPoints(long maxAxisPoints) {
		this.maxAxisPoints = maxAxisPoints;
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

	public Deposit getAxisPointDeposit() {
		return axisPointDeposit;
	}

	public void setAxisPointDeposit(Deposit axisPointDeposit) {
		this.axisPointDeposit = axisPointDeposit;
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

	public Monotony getMonotony() {
		return monotony;
	}

	public void setMonotony(Monotony monotony) {
		this.monotony = monotony;
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

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("AXIS_PTS", 
				name, 
				A2LWriter.toA2LString(longIdentifier), 
				Long.toString(address),
				inputQuantitiy,
				deposit,
				Double.toString(maxDiff),
				conversion,
				Long.toString(maxAxisPoints),
				Double.toString(lowerLimit),
				Double.toString(upperLimit));
		writer.indent();
		
		writer.write(notes);
		writer.write(byteorder);
		writer.write(access);
		
		if(axisPointDeposit != null) {
			writer.write(axisPointDeposit);
		}
		
		if(displayIdentifier != null) {
			writer.writelnSpaced("DISPLAY_IDENTIFIER", displayIdentifier);
		}
		
		if(ecuAddressExtension != null) {
			writer.writelnSpaced("ECU_ADDRESS_EXTENSION", ecuAddressExtension.toString());
		}
		
		writer.write(extendedLimits);
		
		if(format != null ) {
			writer.writelnSpaced("FORMAT", A2LWriter.toA2LString(format));
		}
		
		writer.write(functions);
		
		if(guardRails) {
			writer.write("GUARD_RAILS");
		}
		
		writer.write(ifData);
		writer.write(monotony);
		
		if(physUnit != null) {
			writer.writelnSpaced("PHYS_UNIT", A2LWriter.toA2LString(physUnit));
		}
		
		if(readOnly) {
			writer.write("READ_ONLY");
		}
		
		if(memorySegment != null) {
			writer.writelnSpaced("REF_MEMORY_SEGMENT", memorySegment);
		}
		
		if(stepSize != null) {
			writer.writelnSpaced("STEP_SIZE", stepSize.toString());
		}
		
		if(symbolLink != null) {
			writer.writelnSpaced("SYMBOL_LINK", symbolLink);
		}
		
		writer.dedent();
		writer.writelnEnd("AXIS_PTS");
	}
}
