package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.Deposit;
import net.alenzen.a2l.enums.Monotony;

public class AxisDescr extends A2LSerializer implements IA2LWriteable {
	private Attribute attribute;
	private String inputQuantity;
	private String conversion;
	private long maxAxisPoints;
	private double lowerLimit;
	private double upperLimit;

	// optional parameters
	private List<Annotation> annotations;
	private String axisPoints_ref;
	private ByteOrder byteorder;
	private String curveAxis_ref;
	private Deposit deposit;
	private ExtendedLimits extendedLimits;
	private FixAxisPar fixAxisPar;
	private FixAxisParDist fixAxisParDist;
	private FixAxisParList fixAxisParList;
	private String format;
	private Double maxGrad;
	private Monotony monotony;
	private String physUnit;
	private boolean readOnly;
	private Double stepSize;

	enum Attribute {
		CURVE_AXIS, COM_AXIS, FIX_AXIS, RES_AXIS, STD_AXIS
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public String getInputQuantity() {
		return inputQuantity;
	}

	public void setInputQuantity(String inputQuantity) {
		this.inputQuantity = inputQuantity;
	}

	public String getConversion() {
		return conversion;
	}

	public void setConversion(String conversion) {
		this.conversion = conversion;
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

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
	}

	public String getAxisPoints_ref() {
		return axisPoints_ref;
	}

	public void setAxisPoints_ref(String axisPoints_ref) {
		this.axisPoints_ref = axisPoints_ref;
	}

	public ByteOrder getByteorder() {
		return byteorder;
	}

	public void setByteorder(ByteOrder byteorder) {
		this.byteorder = byteorder;
	}

	public String getCurveAxis_ref() {
		return curveAxis_ref;
	}

	public void setCurveAxis_ref(String curveAxis_ref) {
		this.curveAxis_ref = curveAxis_ref;
	}

	public Deposit getDeposit() {
		return deposit;
	}

	public void setDeposit(Deposit deposit) {
		this.deposit = deposit;
	}

	public ExtendedLimits getExtendedLimits() {
		return extendedLimits;
	}

	public void setExtendedLimits(ExtendedLimits extendedLimits) {
		this.extendedLimits = extendedLimits;
	}

	public FixAxisPar getFixAxisPar() {
		return fixAxisPar;
	}

	public void setFixAxisPar(FixAxisPar fixAxisPar) {
		this.fixAxisPar = fixAxisPar;
	}

	public FixAxisParDist getFixAxisParDist() {
		return fixAxisParDist;
	}

	public void setFixAxisParDist(FixAxisParDist fixAxisParDist) {
		this.fixAxisParDist = fixAxisParDist;
	}

	public FixAxisParList getFixAxisParList() {
		return fixAxisParList;
	}

	public void setFixAxisParList(FixAxisParList fixAxisParList) {
		this.fixAxisParList = fixAxisParList;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Double getMaxGrad() {
		return maxGrad;
	}

	public void setMaxGrad(Double maxGrad) {
		this.maxGrad = maxGrad;
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

	public Double getStepSize() {
		return stepSize;
	}

	public void setStepSize(Double stepSize) {
		this.stepSize = stepSize;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("AXIS_DESCR", attribute.name(), inputQuantity, conversion,
				Long.toString(maxAxisPoints), Double.toString(lowerLimit), Double.toString(upperLimit));
		writer.indent();

		writer.write(annotations);

		if (axisPoints_ref != null) {
			writer.writelnSpaced("AXIS_PTS_REF", axisPoints_ref);
		}

		writer.write(byteorder);

		if (curveAxis_ref != null) {
			writer.writelnSpaced("CURVE_AXIS_REF", curveAxis_ref);
		}

		writer.write(deposit);
		writer.write(extendedLimits);
		writer.write(fixAxisPar);
		writer.write(fixAxisParDist);
		writer.write(fixAxisParList);

		if (format != null) {
			writer.writelnSpaced("FORMAT", A2LWriter.toA2LString(format));
		}

		if (maxGrad != null) {
			writer.writelnSpaced("MAX_GRAD", Double.toString(maxGrad));
		}

		writer.write(monotony);

		if (physUnit != null) {
			writer.writelnSpaced("PHYS_UNIT", A2LWriter.toA2LString(physUnit));
		}

		if (readOnly) {
			writer.writeln("READ_ONLY");
		}

		if (stepSize != null) {
			writer.writelnSpaced("STEP_SIZE", Double.toString(stepSize));
		}

		writer.dedent();
		writer.writelnEnd("AXIS_DESCR");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AxisDescr axisDescr = (AxisDescr) o;
		return maxAxisPoints == axisDescr.maxAxisPoints && Double.compare(axisDescr.lowerLimit, lowerLimit) == 0
				&& Double.compare(axisDescr.upperLimit, upperLimit) == 0 && readOnly == axisDescr.readOnly
				&& attribute == axisDescr.attribute && Objects.equals(inputQuantity, axisDescr.inputQuantity) && Objects
				.equals(conversion, axisDescr.conversion) && Objects.equals(annotations, axisDescr.annotations)
				&& Objects.equals(axisPoints_ref, axisDescr.axisPoints_ref) && byteorder == axisDescr.byteorder
				&& Objects.equals(curveAxis_ref, axisDescr.curveAxis_ref) && deposit == axisDescr.deposit && Objects
				.equals(extendedLimits, axisDescr.extendedLimits) && Objects.equals(fixAxisPar, axisDescr.fixAxisPar)
				&& Objects.equals(fixAxisParDist, axisDescr.fixAxisParDist) && Objects
				.equals(fixAxisParList, axisDescr.fixAxisParList) && Objects.equals(format, axisDescr.format) && Objects
				.equals(maxGrad, axisDescr.maxGrad) && monotony == axisDescr.monotony && Objects
				.equals(physUnit, axisDescr.physUnit) && Objects.equals(stepSize, axisDescr.stepSize);
	}

	@Override
	public int hashCode() {
		return Objects.hash(attribute, inputQuantity, conversion, maxAxisPoints, lowerLimit, upperLimit, annotations,
				axisPoints_ref, byteorder, curveAxis_ref, deposit, extendedLimits, fixAxisPar, fixAxisParDist,
				fixAxisParList, format, maxGrad, monotony, physUnit, readOnly, stepSize);
	}
}
