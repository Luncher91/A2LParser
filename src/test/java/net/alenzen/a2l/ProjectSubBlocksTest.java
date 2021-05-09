package net.alenzen.a2l;

import java.util.ArrayList;
import java.util.List;

public class ProjectSubBlocksTest {

	public static ProjectSubBlocks getPrefabRed() {
		ProjectSubBlocks psb = new ProjectSubBlocks();
		List<Module> ms = new ArrayList<Module>();
		Module m = new Module();
		m.setName("RedModule");
		ms.add(m);
		psb.setModules(ms);
		return psb;
	}

	public static ProjectSubBlocks getPrefabBlue() {
		ProjectSubBlocks psb = new ProjectSubBlocks();
		List<Module> ms = new ArrayList<Module>();
		Module m = new Module();
		m.setName("BlueModule");
		ms.add(m);
		psb.setModules(ms);
		return psb;
	}

}
