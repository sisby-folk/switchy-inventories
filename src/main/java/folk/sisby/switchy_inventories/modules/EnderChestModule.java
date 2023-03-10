package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class EnderChestModule extends EnderChestModuleData implements SwitchyModule, SwitchyModuleClientable, SwitchyEvents.Init {
	@Override
	public void updateFromPlayer(ServerPlayerEntity player, @Nullable String nextPreset) {
		inventory.readNbtList(player.getEnderChestInventory().toNbtList());
	}

	@Override
	public void applyToPlayer(ServerPlayerEntity player) {
		player.getEnderChestInventory().readNbtList(inventory.toNbtList());
	}

	@Override
	public NbtCompound toClientNbt() {
		return toNbt();
	}

	@Override
	public void onInitialize() {
		SwitchyModuleRegistry.registerModule(ID, EnderChestModule::new, new SwitchyModuleInfo(
				false,
				SwitchyModuleEditable.OPERATOR,
				Text.literal("switchy.inventories.module.ender_chest.description"))
				.withDescriptionWhenEnabled(Text.translatable("switchy.inventories.module.ender_chest.description"))
				.withDescriptionWhenDisabled(Text.translatable("switchy.inventories.module.ender_chest.disabled"))
				.withDeletionWarning(Text.translatable("switchy.inventories.module.ender_chest.warning"))
		);
	}
}
