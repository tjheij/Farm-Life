package tostimannetje.landleven;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
	public static SoundEvent questCompleted;
	
	public static void registerSounds() {
		questCompleted = registerSound("quest_completed");
	}
	
	private static SoundEvent registerSound(String name) {
		ResourceLocation loc = new ResourceLocation(Reference.MODID, name);
		SoundEvent e = new SoundEvent(loc);
		e.setRegistryName(name);
		ForgeRegistries.SOUND_EVENTS.register(e);
		return e;
	}
	
	
}
