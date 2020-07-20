package net.alenzen.a2l;

import java.util.List;

public class UserRights {
	private String userLevelId;

	// optional parameters
	private boolean readOnly;
	private List<IdentReferenceList> groups;

	public String getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(String userLevelId) {
		this.userLevelId = userLevelId;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public List<IdentReferenceList> getGroups() {
		return groups;
	}

	public void setGroups(List<IdentReferenceList> groups) {
		this.groups = groups;
	}
}
