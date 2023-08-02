package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class InventoriesModule extends InventoriesModuleData implements SwitchyModule, SwitchyModuleTransferable, SwitchyEvents.Init {
	@Override
	public void updateFromPlayer(ServerPlayerEntity player, @Nullable String nextPreset) {
		this.inventory.clone(player.getInventory());
	}

	@Override
	public void applyToPlayer(ServerPlayerEntity player) {
		player.getInventory().clone(this.inventory);
	}

	@Override
	public void onInitialize() {
		SwitchyModuleRegistry.registerModule(ID, InventoriesModule::new, new SwitchyModuleInfo(
				false,
				SwitchyModuleEditable.OPERATOR,
				Text.translatable("switchy.modules.switchy_inventories.inventories.description"))
				.withDescriptionWhenEnabled(Text.translatable("switchy.modules.switchy_inventories.inventories.enabled"))
				.withDescriptionWhenDisabled(Text.translatable("switchy.modules.switchy_inventories.inventories.disabled"))
				.withDeletionWarning(Text.translatable("switchy.modules.switchy_inventories.inventories.warning"))
		);
	}
}
