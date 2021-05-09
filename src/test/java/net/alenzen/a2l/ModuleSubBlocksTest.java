package net.alenzen.a2l;

public class ModuleSubBlocksTest {

	public static ModuleSubBlocks getPrefabRed() {
		ModuleSubBlocks msb = new ModuleSubBlocks();
		ModCommon mc = new ModCommon();
		mc.setComment("Red");
		msb.setModCommon(mc);
		return msb;
	}

	public static ModuleSubBlocks getPrefabBlue() {
		ModuleSubBlocks msb = new ModuleSubBlocks();
		ModCommon mc = new ModCommon();
		mc.setComment("Blue");
		msb.setModCommon(mc);
		return msb;
	}

}
