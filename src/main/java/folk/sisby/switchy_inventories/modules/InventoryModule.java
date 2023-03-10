package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class InventoryModule extends InventoryModuleData implements SwitchyModule, SwitchyModuleClientable, SwitchyEvents.Init {
	@Override
	public void updateFromPlayer(ServerPlayerEntity player, @Nullable String nextPreset) {
		this.inventory.clone(player.getInventory());
	}

	@Override
	public void applyToPlayer(ServerPlayerEntity player) {
		player.getInventory().clone(this.inventory);
	}

	@Override
	public NbtCompound toClientNbt() {
		return toNbt();
	}

	@Override
	public void onInitialize() {
		SwitchyModuleRegistry.registerModule(ID, InventoryModule::new, new SwitchyModuleInfo(
				false,
				SwitchyModuleEditable.OPERATOR,
				Text.literal("switchy.inventories.module.inventories.description"))
				.withDescriptionWhenEnabled(Text.translatable("switchy.inventories.module.inventories.description"))
				.withDescriptionWhenDisabled(Text.translatable("switchy.inventories.module.inventories.disabled"))
				.withDeletionWarning(Text.translatable("switchy.inventories.module.inventories.warning"))
		);
	}
}
