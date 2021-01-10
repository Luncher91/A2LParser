package net.alenzen.a2l;

import java.io.IOException;
import java.util.List;

public class UserRights implements IA2LWriteable {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("USER_RIGHTS", userLevelId);
		writer.indent();
		
		if(readOnly) {
			writer.writeln("READ_ONLY");
		}
		
		if(groups != null) {
			for(IdentReferenceList g : groups) {
				if(g != null) {
					g.toA2lAsBlock(writer, "REF_GROUP");
				}
			}
		}
		
		writer.dedent();
		writer.writelnEnd("USER_RIGHTS");
	}
}
