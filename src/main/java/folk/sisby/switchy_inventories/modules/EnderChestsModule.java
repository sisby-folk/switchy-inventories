package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
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
				Text.translatable("switchy.inventories.module.ender_chests.description"))
				.withDescriptionWhenEnabled(Text.translatable("switchy.inventories.module.ender_chests.enabled"))
				.withDescriptionWhenDisabled(Text.translatable("switchy.inventories.module.ender_chests.disabled"))
				.withDeletionWarning(Text.translatable("switchy.inventories.module.ender_chests.warning"))
		);
	}
}
