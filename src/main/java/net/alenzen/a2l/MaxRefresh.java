package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class MaxRefresh extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MaxRefresh that = (MaxRefresh) o;
		return scalingUnit == that.scalingUnit && rate == that.rate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(scalingUnit, rate);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		return null;
	}
}
