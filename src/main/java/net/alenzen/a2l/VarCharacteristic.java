package net.alenzen.a2l;

import java.io.IOException;

public class VarCharacteristic implements IA2LWriteable {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("VAR_CHARACTERISTIC", name);
		writer.indent();

		if (criterions != null) {
			for (String s : criterions) {
				writer.writeln(s);
			}
		}

		writer.write(addresses);

		writer.dedent();
		writer.writelnEnd("VAR_CHARACTERISTIC");
	}
}
