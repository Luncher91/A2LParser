package net.alenzen.a2l;

import net.alenzen.a2l.enums.ConversionType;

public class CompuMethod {
	private String name;
	private String longIdentifier;
	private ConversionType conversionType;
	private String format;
	private String unit;

	// optional parameters
	private Coeffs coeffs;
	private CoeffsLinear coeffsLienar;
	private String compuTab_ref;
	private Formula formula;
	private String unit_ref;
	private String conversionTable_ref;

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

	public ConversionType getConversionType() {
		return conversionType;
	}

	public void setConversionType(ConversionType conversionType) {
		this.conversionType = conversionType;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Coeffs getCoeffs() {
		return coeffs;
	}

	public void setCoeffs(Coeffs coeffs) {
		this.coeffs = coeffs;
	}

	public CoeffsLinear getCoeffsLienar() {
		return coeffsLienar;
	}

	public void setCoeffsLienar(CoeffsLinear coeffsLienar) {
		this.coeffsLienar = coeffsLienar;
	}

	public String getCompuTab_ref() {
		return compuTab_ref;
	}

	public void setCompuTab_ref(String compuTab_ref) {
		this.compuTab_ref = compuTab_ref;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public String getUnit_ref() {
		return unit_ref;
	}

	public void setUnit_ref(String unit_ref) {
		this.unit_ref = unit_ref;
	}

	public String getConversionTable_ref() {
		return conversionTable_ref;
	}

	public void setConversionTable_ref(String conversionTable_ref) {
		this.conversionTable_ref = conversionTable_ref;
	}
}
