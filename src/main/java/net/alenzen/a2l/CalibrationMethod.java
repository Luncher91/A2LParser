package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

public class CalibrationMethod implements IA2LWriteable {
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
}
