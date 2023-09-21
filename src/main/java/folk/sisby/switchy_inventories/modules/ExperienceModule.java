package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import folk.sisby.switchy.util.Feedback;
import net.minecraft.server.network.ServerPlayerEntity;
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
				Feedback.translatable("switchy.modules.switchy_inventories.experience.description"))
				.withDescriptionWhenEnabled(Feedback.translatable("switchy.modules.switchy_inventories.experience.enabled"))
				.withDescriptionWhenDisabled(Feedback.translatable("switchy.modules.switchy_inventories.experience.disabled"))
				.withDeletionWarning(Feedback.translatable("switchy.modules.switchy_inventories.experience.warning"))
		);
	}
}
