package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.SwitchyModule;
import folk.sisby.switchy.api.module.SwitchyModuleEditable;
import folk.sisby.switchy.api.module.SwitchyModuleInfo;
import folk.sisby.switchy.api.module.SwitchyModuleRegistry;
import folk.sisby.switchy.api.module.SwitchyModuleTransferable;
import folk.sisby.switchy.util.Feedback;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class InventoriesModule extends InventoriesModuleData implements SwitchyModule, SwitchyModuleTransferable, SwitchyEvents.Init {
	@Override
	public void updateFromPlayer(ServerPlayerEntity player, @Nullable String nextPreset) {
		this.inventory.clear();
		this.inventory.addAll(player.getInventory().writeNbt(new NbtList()));
	}

	@Override
	public void applyToPlayer(ServerPlayerEntity player) {
		player.getInventory().readNbt(this.inventory);
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
