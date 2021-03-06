package tostimannetje.landleven;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import tostimannetje.landleven.init.GuiHandler;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.init.ModTrees;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestStorage;
import tostimannetje.landleven.questing.Quests;
import tostimannetje.landleven.store.Store;

@EventBusSubscriber
public class RegistryHandler {

	public static void preInitRegistries() {
		ModItems.initProducts();
		ModBlocks.init();
		ModTrees.init();
		ModItems.init();
		Store.storeInit();
	}

	public static void initRegistries() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
		SoundsHandler.registerSounds();
	}
	
	public static void postInitRegistries() {
		CapabilityManager.INSTANCE.register(IQuest.class, new QuestStorage(), Quests::new);
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
}
