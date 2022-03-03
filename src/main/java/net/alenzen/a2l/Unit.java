package net.alenzen.a2l;

import java.io.IOException;
import java.util.Objects;


public class Unit extends A2LSerializer implements IA2LWriteable {
	private String name;
	private String longIdentifier;
	private String display;
	private Type type;

	// optional parameters
	private String unit_ref;
	private SiExponents siExponents;
	private UnitConversion unitConversion;

	public enum Type {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("UNIT", name, A2LWriter.toA2LString(longIdentifier), A2LWriter.toA2LString(display), type.name());
		writer.indent();
		
		if(unit_ref != null) {
			writer.writelnSpaced("REF_UNIT", unit_ref);
		}
		
		writer.write(siExponents);
		writer.write(unitConversion);
		
		writer.dedent();
		writer.writelnEnd("UNIT");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Unit unit = (Unit) o;
		return Objects.equals(name, unit.name) && Objects.equals(longIdentifier, unit.longIdentifier) && Objects
				.equals(display, unit.display) && type == unit.type && Objects.equals(unit_ref, unit.unit_ref)
				&& Objects.equals(siExponents, unit.siExponents) && Objects.equals(unitConversion, unit.unitConversion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, display, type, unit_ref, siExponents, unitConversion);
	}
}
