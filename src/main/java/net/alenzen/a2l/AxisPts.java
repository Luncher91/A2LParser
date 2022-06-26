package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.CalibrationAccess;
import net.alenzen.a2l.enums.Deposit;
import net.alenzen.a2l.enums.Monotony;

public class AxisPts extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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
	private SymbolLink symbolLink;

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

	public SymbolLink getSymbolLink() {
		return symbolLink;
	}

	public void setSymbolLink(SymbolLink symbolLink) {
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
		writer.writelnBeginSpaced("AXIS_PTS", name, A2LWriter.toA2LString(longIdentifier),
				"0x" + Long.toHexString(address), inputQuantitiy, deposit, Double.toString(maxDiff), conversion,
				Long.toString(maxAxisPoints), Double.toString(lowerLimit), Double.toString(upperLimit));
		writer.indent();

		writer.write(notes);
		writer.write(byteorder);
		writer.write(access);

		if (axisPointDeposit != null) {
			writer.write(axisPointDeposit);
		}

		if (displayIdentifier != null) {
			writer.writelnSpaced("DISPLAY_IDENTIFIER", displayIdentifier);
		}

		if (ecuAddressExtension != null) {
			writer.writelnSpaced("ECU_ADDRESS_EXTENSION", ecuAddressExtension.toString());
		}

		writer.write(extendedLimits);

		if (format != null) {
			writer.writelnSpaced("FORMAT", A2LWriter.toA2LString(format));
		}

		writer.write(functions);

		if (guardRails) {
			writer.write("GUARD_RAILS");
		}

		writer.write(ifData);
		writer.write(monotony);

		if (physUnit != null) {
			writer.writelnSpaced("PHYS_UNIT", A2LWriter.toA2LString(physUnit));
		}

		if (readOnly) {
			writer.write("READ_ONLY");
		}

		if (memorySegment != null) {
			writer.writelnSpaced("REF_MEMORY_SEGMENT", memorySegment);
		}

		if (stepSize != null) {
			writer.writelnSpaced("STEP_SIZE", stepSize.toString());
		}

		writer.write(symbolLink);

		writer.dedent();
		writer.writelnEnd("AXIS_PTS");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AxisPts axisPts = (AxisPts) o;
		return address == axisPts.address && Double.compare(axisPts.maxDiff, maxDiff) == 0
				&& maxAxisPoints == axisPts.maxAxisPoints && Double.compare(axisPts.lowerLimit, lowerLimit) == 0
				&& Double.compare(axisPts.upperLimit, upperLimit) == 0 && guardRails == axisPts.guardRails
				&& readOnly == axisPts.readOnly && Objects.equals(name, axisPts.name)
				&& Objects.equals(longIdentifier, axisPts.longIdentifier)
				&& Objects.equals(inputQuantitiy, axisPts.inputQuantitiy) && Objects.equals(deposit, axisPts.deposit)
				&& Objects.equals(conversion, axisPts.conversion) && Objects.equals(notes, axisPts.notes)
				&& byteorder == axisPts.byteorder && access == axisPts.access
				&& axisPointDeposit == axisPts.axisPointDeposit
				&& Objects.equals(displayIdentifier, axisPts.displayIdentifier)
				&& Objects.equals(ecuAddressExtension, axisPts.ecuAddressExtension)
				&& Objects.equals(extendedLimits, axisPts.extendedLimits) && Objects.equals(format, axisPts.format)
				&& Objects.equals(functions, axisPts.functions) && Objects.equals(ifData, axisPts.ifData)
				&& monotony == axisPts.monotony && Objects.equals(physUnit, axisPts.physUnit)
				&& Objects.equals(memorySegment, axisPts.memorySegment) && Objects.equals(stepSize, axisPts.stepSize)
				&& Objects.equals(symbolLink, axisPts.symbolLink);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, address, inputQuantitiy, deposit, maxDiff, conversion, maxAxisPoints,
				lowerLimit, upperLimit, notes, byteorder, access, axisPointDeposit, displayIdentifier,
				ecuAddressExtension, extendedLimits, format, functions, guardRails, ifData, monotony, physUnit,
				readOnly, memorySegment, stepSize, symbolLink);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		ArrayList<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.notes);
		Asap2FileIterator.addIfNotNull(subNodes, this.extendedLimits);
		Asap2FileIterator.addIfNotNull(subNodes, this.functions);
		Asap2FileIterator.addIfNotNull(subNodes, this.ifData);
		Asap2FileIterator.addIfNotNull(subNodes, this.symbolLink);
		return subNodes;
	}
}
