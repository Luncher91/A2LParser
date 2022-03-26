package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CalibrationMethod extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
	private String name;
	private long version;

	// optional parameters
	private List<CalibrationHandle> calibrationHandle;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public List<CalibrationHandle> getCalibrationHandle() {
		return calibrationHandle;
	}

	public void setCalibrationHandles(List<CalibrationHandle> calibrationHandle) {
		this.calibrationHandle = calibrationHandle;
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("CALIBRATION_METHOD", A2LWriter.toA2LString(name), Long.toString(version));
		writer.indent();

		writer.write(calibrationHandle);

		writer.dedent();
		writer.writelnEnd("CALIBRATION_METHOD");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CalibrationMethod that = (CalibrationMethod) o;
		return version == that.version && Objects.equals(name, that.name) && Objects
				.equals(calibrationHandle, that.calibrationHandle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, version, calibrationHandle);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.calibrationHandle);
		return subNodes;
	}
}
