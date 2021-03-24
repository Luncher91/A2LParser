package net.alenzen.a2l;

public class FixNoAxisPtsXYZ45 implements IA2LDimensionWriteable {
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
}
