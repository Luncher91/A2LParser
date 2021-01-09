package net.alenzen.a2l;

import java.io.IOException;

public class Asap2Version extends Version {

	public Asap2Version() {
		super();
	}

	public Asap2Version(long versionNumber, long upgradeNumber) {
		super(versionNumber, upgradeNumber);
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("ASAP2_VERSION", Long.toString(this.getVersionNo()), Long.toString(this.getUpgradeNo()));
	}

}
