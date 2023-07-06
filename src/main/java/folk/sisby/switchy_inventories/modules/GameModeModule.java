package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;

public class GameModeModule extends GameModeModuleData implements SwitchyModule, SwitchyModuleTransferable, SwitchyEvents.Init {
	@Override
	public void updateFromPlayer(ServerPlayerEntity player, @Nullable String nextPreset) {
		gameModeId = player.interactionManager.getGameMode().getId();
	}

	@Override
	public void applyToPlayer(ServerPlayerEntity player) {
		player.changeGameMode(GameMode.byId(gameModeId));
	}

	@Override
	public void onInitialize() {
		SwitchyModuleRegistry.registerModule(ID, GameModeModule::new, new SwitchyModuleInfo(
			false,
			SwitchyModuleEditable.OPERATOR,
			Text.translatable("switchy.inventories.module.game_mode.description"))
			.withDescriptionWhenEnabled(Text.translatable("switchy.inventories.module.game_mode.enabled"))
			.withDescriptionWhenDisabled(Text.translatable("switchy.inventories.module.game_mode.disabled"))
			.withDeletionWarning(Text.translatable("switchy.inventories.module.game_mode.warning"))
		);
	}
}
