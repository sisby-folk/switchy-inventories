package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import folk.sisby.switchy.util.Feedback;
import net.minecraft.server.network.ServerPlayerEntity;
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
				Feedback.translatable("switchy.modules.switchy_inventories.inventories.description"))
				.withDescriptionWhenEnabled(Feedback.translatable("switchy.modules.switchy_inventories.inventories.enabled"))
				.withDescriptionWhenDisabled(Feedback.translatable("switchy.modules.switchy_inventories.inventories.disabled"))
				.withDeletionWarning(Feedback.translatable("switchy.modules.switchy_inventories.inventories.warning"))
		);
	}
}
