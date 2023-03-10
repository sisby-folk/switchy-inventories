package folk.sisby.switchy_inventories.modules;

import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.data.EntitySlotLoader;
import folk.sisby.switchy.api.module.SwitchyModuleEditable;
import folk.sisby.switchy.api.module.SwitchyModuleInfo;
import folk.sisby.switchy.api.module.SwitchyModuleRegistry;
import folk.sisby.switchy.api.modules.CardinalSerializerModule;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class TrinketsCompat {
	private static final Identifier ID = new Identifier(SwitchyInventories.ID, "trinkets");

	public static void touch() {
	}

	static {
		SwitchyModuleRegistry.registerModule(ID, () -> CardinalSerializerModule.from(TrinketsApi.TRINKET_COMPONENT, (k, p) -> {
					k.get(p).getInventory().clear();
					k.get(p).getGroups().clear();
					k.get(p).update();
				}, (k, p) -> EntitySlotLoader.SERVER.sync(List.of(p))), new SwitchyModuleInfo(
						false,
						SwitchyModuleEditable.OPERATOR,
						Text.literal("switchy.inventories.module.trinkets.description"))
						.withDescriptionWhenEnabled(Text.translatable("switchy.inventories.module.trinkets.description"))
						.withDescriptionWhenDisabled(Text.translatable("switchy.inventories.module.trinkets.disabled"))
						.withDeletionWarning(Text.translatable("switchy.inventories.module.trinkets.warning"))
		);

	}
}
