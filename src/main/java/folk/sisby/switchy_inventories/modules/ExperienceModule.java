package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ExperienceModule extends ExperienceModuleData implements SwitchyModule, SwitchyModuleTransferable, SwitchyEvents.Init {
	@Override
	public void updateFromPlayer(ServerPlayerEntity player, @Nullable String nextPreset) {
		experienceLevel = player.experienceLevel;
		experienceProgress = player.experienceProgress;
	}

	@Override
	public void applyToPlayer(ServerPlayerEntity player) {
		player.experienceLevel = experienceLevel;
		player.experienceProgress = experienceProgress;
		player.addExperience(0);
	}

	@Override
	public void onInitialize() {
		SwitchyModuleRegistry.registerModule(ID, ExperienceModule::new, new SwitchyModuleInfo(
				false,
				SwitchyModuleEditable.OPERATOR,
				Text.translatable("switchy.inventories.module.experience.description"))
				.withDescriptionWhenEnabled(Text.translatable("switchy.inventories.module.experience.enabled"))
				.withDescriptionWhenDisabled(Text.translatable("switchy.inventories.module.experience.disabled"))
				.withDeletionWarning(Text.translatable("switchy.inventories.module.experience.warning"))
		);
	}
}
