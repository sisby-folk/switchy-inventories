package folk.sisby.switchy_inventories.modules.client;

import com.mojang.datafixers.util.Pair;
import folk.sisby.switchy.client.api.SwitchyClientEvents;
import folk.sisby.switchy.client.api.SwitchySwitchScreenPosition;
import folk.sisby.switchy.client.api.module.SwitchyDisplayModule;
import folk.sisby.switchy.client.api.module.SwitchyDisplayModuleRegistry;
import folk.sisby.switchy_inventories.modules.InventoryModuleData;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.ItemComponent;
import io.wispforest.owo.ui.core.Component;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InventoryDisplayModule extends InventoryModuleData implements SwitchyDisplayModule, SwitchyClientEvents.Init {
	@Override
	public @Nullable Pair<Component, SwitchySwitchScreenPosition> getDisplayComponent() {
		if (inventory.isEmpty()) return null;
		DefaultedList<ItemStack> dList = DefaultedList.of();
		dList.addAll(inventory.armor.stream().filter(i -> !i.isEmpty()).toList());
		dList.addAll(inventory.offHand.stream().filter(i -> !i.isEmpty()).toList());
		dList.addAll(inventory.main.subList(0, PlayerInventory.getHotbarSize()).stream().filter(i -> !i.isEmpty()).toList());
		ItemComponent component = Components.item(Items.BUNDLE.getDefaultStack());
		component.tooltip(List.of(
				TooltipComponent.of(Text.translatable("switchy.inventories.module.inventories.tooltip", "Preset").asOrderedText()),
				TooltipComponent.of(new BundleTooltipData(dList, 0)
				)));
		return Pair.of(component, SwitchySwitchScreenPosition.SIDE_RIGHT);
	}

	@Override
	public void onInitialize() {
		SwitchyDisplayModuleRegistry.registerModule(ID, InventoryDisplayModule::new);
	}
}
