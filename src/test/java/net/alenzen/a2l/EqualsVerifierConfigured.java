package net.alenzen.a2l;

import nl.jqno.equalsverifier.ConfiguredEqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;

public class EqualsVerifierConfigured {
	private static ConfiguredEqualsVerifier INSTANCE = null;

	public static ConfiguredEqualsVerifier getEqualsVerifier() {
		if (INSTANCE == null) {
			INSTANCE = EqualsVerifier.simple()
					.withPrefabValues(AnnotationText.class, AnnotationTextTest.getPrefabRed(),
							AnnotationTextTest.getPrefabBlue())
					.withPrefabValues(Virtual.class, VirtualTest.getPrefabRed(), VirtualTest.getPrefabBlue())
					.withPrefabValues(FunctionList.class, FunctionListTest.getPrefabRed(),
							FunctionListTest.getPrefabBlue())
					.withPrefabValues(IdentReferenceList.class, IdentReferenceListTest.getPrefabRed(),
							IdentReferenceListTest.getPrefabBlue())
					.withPrefabValues(VarAddress.class, VarAddressTest.getPrefabRed(), VarAddressTest.getPrefabBlue())
					.withPrefabValues(ModuleSubBlocks.class, ModuleSubBlocksTest.getPrefabRed(),
							ModuleSubBlocksTest.getPrefabBlue())
					.withPrefabValues(ProjectSubBlocks.class, ProjectSubBlocksTest.getPrefabRed(),
							ProjectSubBlocksTest.getPrefabBlue())
					.withPrefabValues(AxisDescr.class, AxisDescrTest.getPrefabRed(), AxisDescrTest.getPrefabBlue());
		}

		return INSTANCE;
	}
}
