package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchyEvents;
import folk.sisby.switchy.api.module.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class ExperienceModule extends ExperienceModuleData implements SwitchyModule, SwitchyModuleClientable, SwitchyEvents.Init {
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
	public NbtCompound toClientNbt() {
		return toNbt();
	}

	@Override
	public void onInitialize() {
		SwitchyModuleRegistry.registerModule(ID, ExperienceModule::new, new SwitchyModuleInfo(
				false,
				SwitchyModuleEditable.OPERATOR,
				Text.literal("switchy.inventories.module.experience.description"))
				.withDescriptionWhenEnabled(Text.translatable("switchy.inventories.module.experience.description"))
				.withDescriptionWhenDisabled(Text.translatable("switchy.inventories.module.experience.disabled"))
				.withDeletionWarning(Text.translatable("switchy.inventories.module.experience.warning"))
		);
	}
}
