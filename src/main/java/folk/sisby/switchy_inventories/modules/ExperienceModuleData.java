package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchySerializable;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ExperienceModuleData implements SwitchySerializable {
	public static final String KEY_EXPERIENCE_LEVEL = "experienceLevel";
	public static final String KEY_EXPERIENCE_PROGRESS = "experienceProgress";
	protected static final Identifier ID = new Identifier(SwitchyInventories.ID, "experience");
	protected int experienceLevel;
	protected float experienceProgress;

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
}
