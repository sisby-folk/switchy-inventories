package folk.sisby.switchy_inventories;

import folk.sisby.switchy.client.api.SwitchyClientEvents;
import folk.sisby.switchy_inventories.modules.client.TrinketsClientModule;
import org.quiltmc.loader.api.QuiltLoader;

public class SwitchyInventoriesClient implements SwitchyClientEvents.Init {
	@Override
	public void onInitialize() {
		if (QuiltLoader.isModLoaded("trinkets")) {
			TrinketsClientModule.touch();
		}
	}
}
