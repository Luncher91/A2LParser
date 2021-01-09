package net.alenzen.a2l.enums;

import java.io.IOException;

import net.alenzen.a2l.A2LWriter;
import net.alenzen.a2l.IA2LWriteable;

public enum CalibrationAccess implements IA2LWriteable {
	CALIBRATION, NO_CALIBRATION, NOT_IN_MCD_SYSTEM, OFFLINE_CALIBRATION;

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("CALIBRATION_ACCESS", this.name());
	}
}
