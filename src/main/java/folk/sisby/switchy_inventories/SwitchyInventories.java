package folk.sisby.switchy_inventories;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy_inventories.modules.TrinketsCompat;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")
public class SwitchyInventories implements SwitchyEvents.Init {
	public static final String ID = "switchy_inventories";
	public static final Logger LOGGER = LoggerFactory.getLogger(ID);

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().isModLoaded("trinkets")) {
			TrinketsCompat.touch();
		}
	}
}
