package net.alenzen.a2l;

import java.util.List;

public class CalibrationMethod {
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
}
