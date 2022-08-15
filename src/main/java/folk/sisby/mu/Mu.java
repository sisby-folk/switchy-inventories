package folk.sisby.mu;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mu implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Mu");

	@Override
	public void onInitialize(ModContainer mod) {

		LOGGER.info("Mu Initialized!");
	}
}
