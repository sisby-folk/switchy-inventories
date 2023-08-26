package folk.sisby.switchy_inventories;

import folk.sisby.switchy.client.api.SwitchyClientEvents;
import folk.sisby.switchy_inventories.modules.client.TrinketsClientModule;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class SwitchyInventoriesClient implements SwitchyClientEvents.Init {
	public static final String ID = "switchy_inventories_client";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().isModLoaded("trinkets")) {
			TrinketsClientModule.touch();
		}
	}
}
