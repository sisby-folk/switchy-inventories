package folk.sisby.switchy_inventories.compat;

import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.data.EntitySlotLoader;
import folk.sisby.switchy.api.PresetModuleRegistry;
import folk.sisby.switchy.api.modules.CardinalSerializerCompat;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.util.Identifier;

public class TrinketsCompat {
	private static final Identifier ID = new Identifier(SwitchyInventories.ID, "trinkets");

	public static void touch() {
	}

	// Runs on touch() - but only once.
	static {
		PresetModuleRegistry.registerModule(ID, () -> new CardinalSerializerCompat<>(ID, TrinketsApi.TRINKET_COMPONENT, (c, p) -> {
			c.getInventory().clear();
			c.getGroups().clear();
			c.update();
		}, (c, p) -> EntitySlotLoader.INSTANCE.sync(p.getServer().getPlayerManager().getPlayerList()), false));

	}
}
