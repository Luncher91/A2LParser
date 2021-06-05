package net.alenzen.a2l;

import java.util.Objects;


public class FixNoAxisPtsXYZ45 extends A2LSerializer implements IA2LDimensionWriteable {
	private long numberOfAxisPoints;

	public long getNumberOfAxisPoints() {
		return numberOfAxisPoints;
	}

	public void setNumberOfAxisPoints(long numberOfAxisPoints) {
		this.numberOfAxisPoints = numberOfAxisPoints;
	}

	@Override
	public IA2LWriteable getA2lWritable(String dimension) {
		return (A2LWriter writer) -> {
			writer.writelnSpaced("FIX_NO_AXIS_PTS_" + dimension, Long.toString(numberOfAxisPoints));
		};
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FixNoAxisPtsXYZ45 that = (FixNoAxisPtsXYZ45) o;
		return numberOfAxisPoints == that.numberOfAxisPoints;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numberOfAxisPoints);
	}
}
