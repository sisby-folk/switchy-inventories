package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchySerializable;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class GameModeModuleData implements SwitchySerializable {
	public static final String KEY_GAMEMODE_ID = "gameModeId";

	protected static final Identifier ID = new Identifier(SwitchyInventories.ID, "game_mode");
	protected int gameModeId;

	@Override
	public NbtCompound toNbt() {
		NbtCompound outNbt = new NbtCompound();
		outNbt.putInt(KEY_GAMEMODE_ID, gameModeId);
		return outNbt;
	}

	@Override
	public void fillFromNbt(NbtCompound nbt) {
		gameModeId = nbt.getInt(KEY_GAMEMODE_ID);
	}
}
