package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.alenzen.a2l.enums.ConversionType;
import net.alenzen.a2l.indexes.ReferenceResolve;

public class CompuMethod extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	private String name;
	private String longIdentifier;
	private ConversionType conversionType;
	private String format;
	private String unit;

	// optional parameters
	private Coeffs coeffs;
	private CoeffsLinear coeffsLinear;
	private String compuTab_ref;
	private Formula formula;
	private String unit_ref;
	private String conversionTable_ref;
	
	@JsonIgnore
	@ReferenceResolve(ref = "compuTab_ref", index = "compuTabs")
	private CompuTab compuTab;
	
	@JsonIgnore
	@ReferenceResolve(ref = "compuTab_ref", index = "compuVTabs")
	private CompuVTab compuVTab;
	
	@JsonIgnore
	@ReferenceResolve(ref = "compuTab_ref", index = "compuVTabRanges")
	private CompuVTabRange compuVTabRange;
	
	@JsonIgnore
	@ReferenceResolve(ref = "conversionTable_ref", index = "compuVTab")
	private CompuVTab conversionTableCompuVTab;
	
	@JsonIgnore
	@ReferenceResolve(ref = "conversionTable_ref", index = "compuVTabRanges")
	private CompuVTabRange conversionTableCompuVTabRange;

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

	public CoeffsLinear getCoeffsLinear() {
		return coeffsLinear;
	}

	public void setCoeffsLinear(CoeffsLinear coeffsLienar) {
		this.coeffsLinear = coeffsLienar;
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("COMPU_METHOD", name, A2LWriter.toA2LString(longIdentifier), conversionType.name(), A2LWriter.toA2LString(format), A2LWriter.toA2LString(unit));
		writer.indent();
		
		writer.write(coeffs);
		writer.write(coeffsLinear);
		
		if(compuTab_ref != null) {
			writer.writelnSpaced("COMPU_TAB_REF", compuTab_ref);
		}
		
		writer.write(formula);
		
		if(unit_ref != null) {
			writer.writelnSpaced("REF_UNIT", unit_ref);
		}
		
		if(conversionTable_ref != null) {
			writer.writelnSpaced("STATUS_STRING_REF", conversionTable_ref);
		}
		
		writer.dedent();
		writer.writelnEnd("COMPU_METHOD");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CompuMethod that = (CompuMethod) o;
		return Objects.equals(name, that.name) && Objects.equals(longIdentifier, that.longIdentifier)
				&& conversionType == that.conversionType && Objects.equals(format, that.format) && Objects
				.equals(unit, that.unit) && Objects.equals(coeffs, that.coeffs) && Objects
				.equals(coeffsLinear, that.coeffsLinear) && Objects.equals(compuTab_ref, that.compuTab_ref) && Objects
				.equals(formula, that.formula) && Objects.equals(unit_ref, that.unit_ref) && Objects
				.equals(conversionTable_ref, that.conversionTable_ref);
	}

	@Override
	public int hashCode() {
		return Objects
				.hash(name, longIdentifier, conversionType, format, unit, coeffs, coeffsLinear, compuTab_ref, formula,
						unit_ref, conversionTable_ref);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.coeffs);
		Asap2FileIterator.addIfNotNull(subNodes, this.coeffsLinear);
		Asap2FileIterator.addIfNotNull(subNodes, this.formula);
		return subNodes;
	}

	public CompuTab getCompuTab() {
		return compuTab;
	}

	public void setCompuTab(CompuTab compuTab) {
		this.compuTab = compuTab;
	}

	public CompuVTab getCompuVTab() {
		return compuVTab;
	}

	public void setCompuVTab(CompuVTab compuVTab) {
		this.compuVTab = compuVTab;
	}

	public CompuVTabRange getCompuVTabRange() {
		return compuVTabRange;
	}

	public void setCompuVTabRange(CompuVTabRange compuVTabRange) {
		this.compuVTabRange = compuVTabRange;
	}

	public CompuVTab getConversionTableCompuVTab() {
		return conversionTableCompuVTab;
	}

	public void setConversionTableCompuVTab(CompuVTab conversionTableCompuVTab) {
		this.conversionTableCompuVTab = conversionTableCompuVTab;
	}

	public CompuVTabRange getConversionTableCompuVTabRange() {
		return conversionTableCompuVTabRange;
	}

	public void setConversionTableCompuVTabRange(CompuVTabRange conversionTableCompuVTabRange) {
		this.conversionTableCompuVTabRange = conversionTableCompuVTabRange;
	}
}
