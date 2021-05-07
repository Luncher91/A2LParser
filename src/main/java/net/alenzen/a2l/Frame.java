package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class Frame implements IA2LWriteable {
	private String name;
	private String longIdentifier;
	private long scalingUnit;
	private long rate;

	// optional parameters
	private IdentReferenceList frameMeasurements;
	private List<IfData> ifDatas;

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

	public IdentReferenceList getFrameMeasurements() {
		return frameMeasurements;
	}

	public void setFrameMeasurements(IdentReferenceList frameMeasurements) {
		this.frameMeasurements = frameMeasurements;
	}

	public List<IfData> getIfDatas() {
		return ifDatas;
	}

	public void setIfDatas(List<IfData> ifDatas) {
		this.ifDatas = ifDatas;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("FRAME", name, A2LWriter.toA2LString(longIdentifier), Long.toString(scalingUnit), Long.toString(rate));
		writer.indent();

		if(frameMeasurements != null && frameMeasurements.size() > 0) {
			frameMeasurements.toA2l(writer, "FRAME_MEASUREMENT");
		}
		writer.write(ifDatas);
		
		writer.dedent();
		writer.writelnEnd("FRAME");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Frame frame = (Frame) o;
		return scalingUnit == frame.scalingUnit && rate == frame.rate && Objects.equals(name, frame.name) && Objects
				.equals(longIdentifier, frame.longIdentifier) && Objects
				.equals(frameMeasurements, frame.frameMeasurements) && Objects.equals(ifDatas, frame.ifDatas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, longIdentifier, scalingUnit, rate, frameMeasurements, ifDatas);
	}
}
