package net.alenzen.a2l;

import java.util.Objects;


public abstract class Version extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Version version = (Version) o;
		return versionNo == version.versionNo && upgradeNo == version.upgradeNo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(versionNo, upgradeNo);
	}
}
