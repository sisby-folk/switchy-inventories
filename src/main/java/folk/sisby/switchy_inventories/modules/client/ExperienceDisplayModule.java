package folk.sisby.switchy_inventories.modules.client;

import com.mojang.datafixers.util.Pair;
import folk.sisby.switchy.client.api.SwitchyClientEvents;
import folk.sisby.switchy.client.api.SwitchySwitchScreenPosition;
import folk.sisby.switchy.client.api.module.SwitchyDisplayModule;
import folk.sisby.switchy.client.api.module.SwitchyDisplayModuleRegistry;
import folk.sisby.switchy_inventories.modules.ExperienceModuleData;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.core.Component;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

public class ExperienceDisplayModule extends ExperienceModuleData implements SwitchyDisplayModule, SwitchyClientEvents.Init {
	@Override
	public @Nullable Pair<Component, SwitchySwitchScreenPosition> getDisplayComponent() {
		return Pair.of(
				Components.label(
								Text.literal("Lv. " + experienceLevel)
										.setStyle(Style.EMPTY.withColor(Formatting.GREEN)))
						.tooltip(Text.translatable("switchy.inventories.module.experience.tooltip", String.valueOf((int) (experienceProgress * 100)))),
				SwitchySwitchScreenPosition.SIDE_RIGHT);
	}

	@Override
	public void onInitialize() {
		SwitchyDisplayModuleRegistry.registerModule(ID, ExperienceDisplayModule::new);
	}
}
