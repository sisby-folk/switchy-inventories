package folk.sisby.switchy_inventories.compat;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import dev.emi.trinkets.data.EntitySlotLoader;
import folk.sisby.switchy.api.PresetModule;
import folk.sisby.switchy.api.PresetModuleRegistry;
import folk.sisby.switchy.api.modules.CardinalSerializerCompat;
import folk.sisby.switchy_inventories.SwitchyInventories;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class TrinketsCompat implements PresetModule {
	private static final Identifier ID = new Identifier(SwitchyInventories.ID, "trinkets");
	private static final boolean isDefault = false;

	private NbtCompound inventory = new NbtCompound();

	@Override
	public void updateFromPlayer(PlayerEntity player) {
		this.inventory = new NbtCompound();
		TrinketsApi.TRINKET_COMPONENT.get(player).writeToNbt(this.inventory);
	}

	@Override
	public void applyToPlayer(PlayerEntity player) {
		TrinketComponent component = TrinketsApi.TRINKET_COMPONENT.get(player);
		// Recreate post-constructor state
		component.getInventory().clear();
		component.getGroups().clear();
		component.update();
		// Read from NBT
		component.readFromNbt(inventory);
		// Sync
		EntitySlotLoader.INSTANCE.sync(player.getServer().getPlayerManager().getPlayerList());
	}

	@Override
	public NbtCompound toNbt() {
		return inventory;
	}

	@Override
	public void fillFromNbt(NbtCompound nbt) {
		this.inventory = nbt;
	}

	@Override
	public Identifier getId() {
		return ID;
	}

	@Override
	public boolean isDefault() {
		return isDefault;
	}

	@Override
	public String getDisableConfirmation() {
		return "WARNING: All items not currently present in your inventory will be IMMEDIATELY lost.";
	}

	public static void touch() {

	}

	// Runs on touch() - but only once.
	static {
		PresetModuleRegistry.registerModule(ID, TrinketsCompat::new);
	}
}
