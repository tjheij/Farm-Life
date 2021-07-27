package tostimannetje.landleven;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import tostimannetje.landleven.entity.EntityTractor;
import tostimannetje.landleven.entity.render.RenderTractor;

public class RenderHandler {
	public static void registerEntityRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityTractor.class, RenderTractor::new);
	}
}
