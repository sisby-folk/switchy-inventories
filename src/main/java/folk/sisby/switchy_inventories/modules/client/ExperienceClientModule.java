package folk.sisby.switchy_inventories.modules.client;

import com.mojang.datafixers.util.Pair;
import folk.sisby.switchy.client.api.SwitchyClientEvents;
import folk.sisby.switchy.client.api.module.SwitchyClientModule;
import folk.sisby.switchy.client.api.module.SwitchyClientModuleRegistry;
import folk.sisby.switchy.ui.api.SwitchyUIPosition;
import folk.sisby.switchy.ui.api.module.SwitchyUIModule;
import folk.sisby.switchy_inventories.modules.ExperienceModuleData;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.core.Component;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

public class ExperienceClientModule extends ExperienceModuleData implements SwitchyClientModule, SwitchyUIModule, SwitchyClientEvents.Init {
	@Override
	public @Nullable Pair<Component, SwitchyUIPosition> getPreviewComponent(String presetName) {
		return Pair.of(
				Components.label(
								Text.literal("Lv. " + experienceLevel)
										.setStyle(Style.EMPTY.withColor(Formatting.GREEN)))
						.tooltip(Text.translatable("switchy.modules.switchy_inventories.experience.preview.tooltip", String.valueOf((int) (experienceProgress * 100)))),
				SwitchyUIPosition.SIDE_RIGHT);
	}

	@Override
	public void onInitialize() {
		SwitchyClientModuleRegistry.registerModule(ID, ExperienceClientModule::new);
	}
}
