package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchySerializable;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;

public class InventoryModuleData implements SwitchySerializable {
	protected static final Identifier ID = new Identifier(SwitchyInventories.ID,  "inventories");

	public static final String KEY_INVENTORY_LIST = "inventory";

	protected final PlayerInventory inventory = new PlayerInventory(null);

	@Override
	public NbtCompound toNbt() {
		NbtCompound outNbt = new NbtCompound();
		outNbt.put(KEY_INVENTORY_LIST, inventory.writeNbt(new NbtList()));
		return outNbt;
	}

	@Override
	public void fillFromNbt(NbtCompound nbt) {
		this.inventory.readNbt(nbt.getList(KEY_INVENTORY_LIST, NbtElement.COMPOUND_TYPE));
	}
}
