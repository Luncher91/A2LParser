package net.alenzen.a2l;

import java.io.IOException;

public class A2mlVersion extends Version {

	public A2mlVersion() {
		super();
	}
	
	public A2mlVersion(long version, long upgradeVersion) {
		super(version, upgradeVersion);
	}

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnSpaced("A2ML_VERSION", Long.toString(this.getVersionNo()), Long.toString(this.getUpgradeNo()));
	}
}
