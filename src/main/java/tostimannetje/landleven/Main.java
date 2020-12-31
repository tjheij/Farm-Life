package tostimannetje.landleven;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import tostimannetje.landleven.init.GuiHandler;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.proxy.CommonProxy;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	@SidedProxy(serverSide = "tostimannetje.landleven.proxy.CommonProxy", clientSide = "tostimannetje.landleven.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Instance
	public static Main instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.preInitRegistries();

		NetworkHandler.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RegistryHandler.initRegistries();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries();
	}
}
