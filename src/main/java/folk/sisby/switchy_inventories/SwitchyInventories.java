package folk.sisby.switchy_inventories;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy_inventories.modules.TrinketsCompat;
import org.quiltmc.loader.api.QuiltLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SwitchyInventories implements SwitchyEvents.Init {
	public static final String ID = "switchy_inventories";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		if (QuiltLoader.isModLoaded("trinkets")) {
			TrinketsCompat.touch();
		}
	}
}
