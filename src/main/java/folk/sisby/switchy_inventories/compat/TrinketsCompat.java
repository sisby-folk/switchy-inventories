package folk.sisby.switchy_inventories.compat;

import dev.emi.trinkets.api.LivingEntityTrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import folk.sisby.switchy.api.PresetModuleRegistry;
import folk.sisby.switchy.api.modules.CardinalSerializerCompat;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.util.Identifier;

public class TrinketsCompat {
	private static final Identifier ID = new Identifier(SwitchyInventories.ID,  "trinkets");
	public static void touch() {
	}

	// Runs on touch() - but only once.
	static {
		PresetModuleRegistry.registerModule(ID, () -> new CardinalSerializerCompat<>(TrinketsApi.TRINKET_COMPONENT, ID, () -> new LivingEntityTrinketComponent(null)));
	}
}
