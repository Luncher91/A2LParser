package net.alenzen.a2l;

import java.util.List;

public class CalibrationHandle {
	private List<Long> handles;

	// optional parameters
	private String calibrationText;

	public List<Long> getHandles() {
		return handles;
	}

	public void setHandles(List<Long> handles) {
		this.handles = handles;
	}

	public String getCalibrationText() {
		return calibrationText;
	}

	public void setCalibrationText(String calibrationText) {
		this.calibrationText = calibrationText;
	}
}
