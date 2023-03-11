package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.SwitchySerializable;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

public class EnderChestsModuleData implements SwitchySerializable {
	public static final String KEY_INVENTORY_LIST = "inventory";
	protected static final Identifier ID = new Identifier(SwitchyInventories.ID, "ender_chests");
	protected final EnderChestInventory inventory = new EnderChestInventory();

	@Override
	public NbtCompound toNbt() {
		NbtCompound outNbt = new NbtCompound();
		outNbt.put(KEY_INVENTORY_LIST, inventory.toNbtList());
		return outNbt;
	}

	@Override
	public void fillFromNbt(NbtCompound nbt) {
		this.inventory.readNbtList(nbt.getList(KEY_INVENTORY_LIST, NbtElement.COMPOUND_TYPE));
	}
}
