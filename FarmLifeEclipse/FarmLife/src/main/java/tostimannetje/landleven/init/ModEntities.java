package tostimannetje.landleven.init;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.registries.IForgeRegistry;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.entity.EntityTractor;

public class ModEntities {
	
	public static EntityEntry ENTITY_TRACTOR;

	private static final ModEntities instance = new ModEntities();
	
	public static void init(){
		ENTITY_TRACTOR = EntityEntryBuilder.create().entity(EntityTractor.class).id(Reference.MODID + ":" + "tractor", 0).name("tractor").tracker(80, 10, true).build();
		
		MinecraftForge.EVENT_BUS.register(instance);
	}
	
	@SubscribeEvent
	public void registerEntity(RegistryEvent.Register<EntityEntry> event){
		IForgeRegistry<EntityEntry> registry = event.getRegistry();
		
		registry.register(ENTITY_TRACTOR);
	}
}
