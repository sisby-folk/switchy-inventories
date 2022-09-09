package folk.sisby.switchy_inventories;

import folk.sisby.switchy_inventories.compat.EnderChestCompat;
import folk.sisby.switchy_inventories.compat.InventoryCompat;
import folk.sisby.switchy_inventories.compat.TrinketsCompat;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.QuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwitchyInventories implements ModInitializer {
	public static final String ID = "switchy_inventories";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize(ModContainer mod) {
		InventoryCompat.touch();
		EnderChestCompat.touch();
		if (QuiltLoader.isModLoaded("trinkets")) {
			TrinketsCompat.touch();
		}
	}
}
