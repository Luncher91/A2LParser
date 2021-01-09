package net.alenzen.a2l;

public abstract class Version implements IA2LWriteable {
	private long versionNo;
	private long upgradeNo;

	public Version() {}
	
	public Version(long versionNumber, long upgradeNumber) {
		this.versionNo = versionNumber;
		this.upgradeNo = upgradeNumber;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
	}

	public long getUpgradeNo() {
		return upgradeNo;
	}

	public void setUpgradeNo(long upgradeNo) {
		this.upgradeNo = upgradeNo;
	}
}
