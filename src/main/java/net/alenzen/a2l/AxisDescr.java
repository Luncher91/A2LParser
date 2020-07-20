package net.alenzen.a2l;

import java.util.List;

import net.alenzen.a2l.enums.ByteOrder;
import net.alenzen.a2l.enums.Deposit;
import net.alenzen.a2l.enums.Monotony;

public class AxisDescr {
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
}
