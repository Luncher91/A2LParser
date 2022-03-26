package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class VarCharacteristic extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		VarCharacteristic that = (VarCharacteristic) o;
		return Objects.equals(name, that.name) && Objects.equals(criterions, that.criterions) && Objects
				.equals(addresses, that.addresses);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, criterions, addresses);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		List<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.criterions);
		Asap2FileIterator.addIfNotNull(subNodes, this.addresses);
		return subNodes;
	}
}
