package net.alenzen.a2l;

public class Unit {
	private String name;
	private String longIdentifier;
	private String display;
	private Type type;
	
	// optional parameters
	private String unit_ref;
	private SiExponents siExponents;
	private UnitConversion unitConversion;
	
	enum Type {
		DERIVED, EXTENDED_SI
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

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getUnit_ref() {
		return unit_ref;
	}

	public void setUnit_ref(String unit_ref) {
		this.unit_ref = unit_ref;
	}

	public SiExponents getSiExponents() {
		return siExponents;
	}

	public void setSiExponents(SiExponents siExponents) {
		this.siExponents = siExponents;
	}

	public UnitConversion getUnitConversion() {
		return unitConversion;
	}

	public void setUnitConversion(UnitConversion unitConversion) {
		this.unitConversion = unitConversion;
	}
}
