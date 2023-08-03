package folk.sisby.switchy_inventories.modules.client;

import com.mojang.datafixers.util.Pair;
import folk.sisby.switchy.api.modules.CardinalSerializerData;
import folk.sisby.switchy.client.api.module.SwitchyClientModule;
import folk.sisby.switchy.client.api.module.SwitchyClientModuleRegistry;
import folk.sisby.switchy.ui.api.SwitchyUIPosition;
import folk.sisby.switchy.ui.api.module.SwitchyUIModule;
import folk.sisby.switchy_inventories.SwitchyInventories;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.ItemComponent;
import io.wispforest.owo.ui.core.Component;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.item.BundleTooltipData;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TrinketsClientModule extends CardinalSerializerData implements SwitchyClientModule, SwitchyUIModule {
	public static final Identifier ID = new Identifier(SwitchyInventories.ID, "trinkets");

	static {
		SwitchyClientModuleRegistry.registerModule(ID, ExperienceClientModule::new);
	}
	public static void touch() {}

	@Override
	public @Nullable Pair<Component, SwitchyUIPosition> getPreviewComponent(String presetName) {
		DefaultedList<ItemStack> stacks = DefaultedList.of();
		for (String groupKey : moduleNbt.getKeys()) {
			NbtCompound groupCompound = moduleNbt.getCompound(groupKey);
			for (String slotKey : groupCompound.getKeys()) {
				NbtCompound slotCompound = moduleNbt.getCompound(slotKey);
				slotCompound.getList("Items", NbtElement.COMPOUND_TYPE).forEach(itemElement -> {
					ItemStack stack = ItemStack.fromNbt((NbtCompound) itemElement);
					if (!stack.isEmpty()) stacks.add(stack);
				});
			}
		}
		if (stacks.isEmpty()) return null;
		ItemComponent component = Components.item(Items.ELYTRA.getDefaultStack());
		component.tooltip(List.of(
			TooltipComponent.of(Text.translatable("switchy.modules.switchy_inventories.trinkets.preview.tooltip", presetName).asOrderedText()),
			TooltipComponent.of(new BundleTooltipData(stacks, 0)
		)));
		return new Pair<>(component, SwitchyUIPosition.GRID_RIGHT);
	}
}
