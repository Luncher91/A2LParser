package net.alenzen.a2l;

public class VarCharacteristic {
	private String name;

	// optional parameters
	private IdentReferenceList criterions;
	private VarAddress addresses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IdentReferenceList getCriterions() {
		return criterions;
	}

	public void setCriterions(IdentReferenceList criterions) {
		this.criterions = criterions;
	}

	public VarAddress getAddresses() {
		return addresses;
	}

	public void setAddresses(VarAddress addresses) {
		this.addresses = addresses;
	}
}
