package folk.sisby.switchy_inventories.modules;

import folk.sisby.switchy.api.ModuleImportable;
import folk.sisby.switchy.api.PresetModule;
import folk.sisby.switchy.api.PresetModuleRegistry;
import folk.sisby.switchy.api.SwitchyModInitializer;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class EnderChestModule implements PresetModule, SwitchyModInitializer {
	private static final Identifier ID = new Identifier(SwitchyInventories.ID,  "ender_chests");

	public static final String KEY_INVENTORY_LIST = "inventory";

	private final EnderChestInventory inventory = new EnderChestInventory();

	@Override
	public void updateFromPlayer(PlayerEntity player, @Nullable String nextPreset) {
		this.inventory.readNbtList(player.getEnderChestInventory().toNbtList());
	}

	@Override
	public void applyToPlayer(PlayerEntity player) {
		player.getEnderChestInventory().readNbtList(this.inventory.toNbtList());
	}

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

	@Override
	public void initializeSwitchyCompat() {
		PresetModuleRegistry.registerModule(ID, EnderChestModule::new, false, ModuleImportable.OPERATOR, Set.of(), Text.translatable("commands.switchy_inventories.module.warn.inventories"));
	}
}
