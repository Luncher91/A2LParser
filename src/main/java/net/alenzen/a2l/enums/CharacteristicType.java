package net.alenzen.a2l.enums;

public enum CharacteristicType {
	ASCII, CURVE, MAP, CUBOID, CUBE_4, CUBE_5, VAL_BLK, VALUE;
	
	@Override
	public String toString() {
		return this.name();
	}
}
