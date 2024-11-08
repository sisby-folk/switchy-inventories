package folk.sisby.switchy_inventories.modules.client;

import com.mojang.datafixers.util.Pair;
import folk.sisby.switchy.client.api.SwitchyClientEvents;
import folk.sisby.switchy.client.api.module.SwitchyClientModule;
import folk.sisby.switchy.client.api.module.SwitchyClientModuleRegistry;
import folk.sisby.switchy.ui.api.SwitchyUIPosition;
import folk.sisby.switchy.ui.api.module.SwitchyUIModule;
import folk.sisby.switchy.util.Feedback;
import folk.sisby.switchy_inventories.modules.EnderChestsModuleData;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.ItemComponent;
import io.wispforest.owo.ui.core.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.BundleTooltipData;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderChestsClientModule extends EnderChestsModuleData implements SwitchyClientModule, SwitchyUIModule, SwitchyClientEvents.Init {
	@Override
	public @Nullable Pair<Component, SwitchyUIPosition> getPreviewComponent(String presetName) {
		if (inventory.isEmpty()) return null;
		DefaultedList<ItemStack> dList = DefaultedList.of();
		EnderChestInventory displayInventory = new EnderChestInventory();
		displayInventory.readNbtList(inventory, MinecraftClient.getInstance().player.getRegistryManager());
		dList.addAll(displayInventory.heldStacks.stream().filter(i -> !i.isEmpty()).toList());
		ItemComponent component = Components.item(Items.ENDER_CHEST.getDefaultStack());
		component.tooltip(List.of(
			TooltipComponent.of(Feedback.translatable("switchy.modules.switchy_inventories.ender_chests.preview.tooltip", presetName).asOrderedText()),
			TooltipComponent.of(new BundleTooltipData(new BundleContentsComponent(dList))
			)));
		return Pair.of(component, SwitchyUIPosition.GRID_RIGHT);
	}

	@Override
	public void onInitialize() {
		SwitchyClientModuleRegistry.registerModule(ID, EnderChestsClientModule::new);
	}
}
