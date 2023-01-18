package folk.sisby.switchy_inventories.compat;

import folk.sisby.switchy.api.ModuleImportable;
import folk.sisby.switchy.api.PresetModule;
import folk.sisby.switchy.api.PresetModuleRegistry;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class InventoryCompat implements PresetModule {
	private static final Identifier ID = new Identifier(SwitchyInventories.ID,  "inventories");

	public static final String KEY_INVENTORY_LIST = "inventory";

	private final PlayerInventory inventory = new PlayerInventory(null);

	@Override
	public void updateFromPlayer(PlayerEntity player, @Nullable String nextPreset) {
		this.inventory.clone(player.getInventory());
	}

	@Override
	public void applyToPlayer(PlayerEntity player) {
		player.getInventory().clone(this.inventory);
	}

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

	public static void touch() {
	}

	// Runs on touch() - but only once.
	static {
		PresetModuleRegistry.registerModule(ID, InventoryCompat::new, false, ModuleImportable.OPERATOR, Set.of(), new TranslatableText("commands.switchy_inventories.module.warn.inventories"));
	}
}
