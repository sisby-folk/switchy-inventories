package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.ModuleImportable;
import folk.sisby.switchy.api.PresetModule;
import folk.sisby.switchy.api.PresetModuleRegistry;
import folk.sisby.switchy.api.SwitchyModInitializer;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class ExperienceModule implements PresetModule, SwitchyModInitializer {
	private static final Identifier ID = new Identifier(SwitchyInventories.ID,  "experience");

	public static final String KEY_EXPERIENCE_LEVEL = "experienceLevel";
	public static final String KEY_EXPERIENCE_PROGRESS = "experienceProgress";

	private int experienceLevel;
	private float experienceProgress;

	@Override
	public void updateFromPlayer(PlayerEntity player, @Nullable String nextPreset) {
		experienceLevel = player.experienceLevel;
		experienceProgress = player.experienceProgress;
	}

	@Override
	public void applyToPlayer(PlayerEntity player) {
		player.experienceLevel = experienceLevel;
		player.experienceProgress = experienceProgress;
		player.addExperience(0);
	}

	@Override
	public NbtCompound toNbt() {
		NbtCompound outNbt = new NbtCompound();
		outNbt.putInt(KEY_EXPERIENCE_LEVEL, experienceLevel);
		outNbt.putFloat(KEY_EXPERIENCE_PROGRESS, experienceProgress);
		return outNbt;
	}

	@Override
	public void fillFromNbt(NbtCompound nbt) {
		experienceLevel = nbt.getInt(KEY_EXPERIENCE_LEVEL);
		experienceProgress = nbt.getFloat(KEY_EXPERIENCE_PROGRESS);
	}

	@Override
	public void initializeSwitchyCompat() {
		PresetModuleRegistry.registerModule(ID, ExperienceModule::new, false, ModuleImportable.OPERATOR, Set.of(), Text.translatable("commands.switchy_inventories.module.warn.experience"));
	}
}
