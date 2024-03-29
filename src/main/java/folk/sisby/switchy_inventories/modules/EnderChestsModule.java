package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import folk.sisby.switchy.util.Feedback;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class EnderChestsModule extends EnderChestsModuleData implements SwitchyModule, SwitchyModuleTransferable, SwitchyEvents.Init {
	@Override
	public void updateFromPlayer(ServerPlayerEntity player, @Nullable String nextPreset) {
		inventory.readNbtList(player.getEnderChestInventory().toNbtList());
	}

	@Override
	public void applyToPlayer(ServerPlayerEntity player) {
		player.getEnderChestInventory().readNbtList(inventory.toNbtList());
	}

	@Override
	public void onInitialize() {
		SwitchyModuleRegistry.registerModule(ID, EnderChestsModule::new, new SwitchyModuleInfo(
				false,
				SwitchyModuleEditable.OPERATOR,
				Feedback.translatable("switchy.modules.switchy_inventories.ender_chests.description"))
				.withDescriptionWhenEnabled(Feedback.translatable("switchy.modules.switchy_inventories.ender_chests.enabled"))
				.withDescriptionWhenDisabled(Feedback.translatable("switchy.modules.switchy_inventories.ender_chests.disabled"))
				.withDeletionWarning(Feedback.translatable("switchy.modules.switchy_inventories.ender_chests.warning"))
		);
	}
}
