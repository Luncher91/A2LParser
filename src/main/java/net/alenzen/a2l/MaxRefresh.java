package net.alenzen.a2l;

import java.io.IOException;

public class MaxRefresh implements IA2LWriteable {
	private long scalingUnit;
	private long rate;

	public long getScalingUnit() {
		return scalingUnit;
	}

	public void setScalingUnit(long scalingUnit) {
		this.scalingUnit = scalingUnit;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("MAX_REFRESH", Long.toString(scalingUnit), Long.toString(rate));
	}
}
