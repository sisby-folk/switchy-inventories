package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchySerializable;
import folk.sisby.switchy.util.Feedback;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

public class InventoriesModuleData implements SwitchySerializable {
	public static final String KEY_INVENTORY_LIST = "inventory";
	protected static final Identifier ID = Feedback.identifier(SwitchyInventories.ID, "inventories");
	protected final NbtList inventory = new NbtList();

	@Override
	public NbtCompound toNbt() {
		NbtCompound outNbt = new NbtCompound();
		outNbt.put(KEY_INVENTORY_LIST, inventory);
		return outNbt;
	}

	@Override
	public void fillFromNbt(NbtCompound nbt) {
		this.inventory.clear();
		this.inventory.addAll(nbt.getList(KEY_INVENTORY_LIST, NbtElement.COMPOUND_TYPE));
	}
}
