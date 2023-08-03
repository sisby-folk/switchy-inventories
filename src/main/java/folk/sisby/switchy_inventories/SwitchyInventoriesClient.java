package folk.sisby.switchy_inventories;

import folk.sisby.switchy.client.api.SwitchyClientEvents;
import folk.sisby.switchy_inventories.modules.client.TrinketsClientModule;
import org.quiltmc.loader.api.QuiltLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwitchyInventoriesClient implements SwitchyClientEvents.Init {
	public static final String ID = "switchy_inventories_client";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		if (QuiltLoader.isModLoaded("trinkets")) {
			TrinketsClientModule.touch();
		}
	}
}
