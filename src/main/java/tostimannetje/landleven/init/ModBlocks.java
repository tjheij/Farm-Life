package tostimannetje.landleven.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tostimannetje.landleven.EnumHandler;
import tostimannetje.landleven.IHasModel;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.blocks.BlockAnimal;
import tostimannetje.landleven.blocks.BlockBase;
import tostimannetje.landleven.blocks.BlockCrop;
import tostimannetje.landleven.blocks.BlockMarket;
import tostimannetje.landleven.blocks.BlockSapling;
import tostimannetje.landleven.blocks.BlockStore;
import tostimannetje.landleven.blocks.BlockTreeLeaf;
import tostimannetje.landleven.blocks.BlockTreeLog;
import tostimannetje.landleven.blocks.animals.BlockAnimalBuffalo;
import tostimannetje.landleven.blocks.animals.BlockAnimalChicken;
import tostimannetje.landleven.blocks.animals.BlockAnimalCow;
import tostimannetje.landleven.blocks.animals.BlockAnimalDuck;
import tostimannetje.landleven.blocks.animals.BlockAnimalGoat;
import tostimannetje.landleven.blocks.animals.BlockAnimalHorseWhite;
import tostimannetje.landleven.blocks.animals.BlockAnimalOx;
import tostimannetje.landleven.blocks.animals.BlockAnimalPig;
import tostimannetje.landleven.blocks.animals.BlockAnimalTurkey;
import tostimannetje.landleven.blocks.machines.BlockBreadmachine;
import tostimannetje.landleven.blocks.machines.BlockCheesemachine;
import tostimannetje.landleven.blocks.machines.BlockCoffeemachine;
import tostimannetje.landleven.blocks.machines.BlockCookiemachine;
import tostimannetje.landleven.blocks.machines.BlockCornflakemachine;
import tostimannetje.landleven.blocks.machines.BlockJuicemachine;
import tostimannetje.landleven.blocks.machines.BlockMill;
import tostimannetje.landleven.blocks.machines.BlockPancakemachine;
import tostimannetje.landleven.blocks.machines.BlockPastamachine;
import tostimannetje.landleven.blocks.machines.BlockPiemachine;
import tostimannetje.landleven.blocks.machines.BlockPopcornmachine;
import tostimannetje.landleven.blocks.machines.BlockSausagemachine;
import tostimannetje.landleven.blocks.machines.BlockSugarmachine;
import tostimannetje.landleven.blocks.machines.BlockWinemachine;
import tostimannetje.landleven.tileentity.TileEntityAnimal;
import tostimannetje.landleven.tileentity.TileEntityBreadmachine;
import tostimannetje.landleven.tileentity.TileEntityBuffalo;
import tostimannetje.landleven.tileentity.TileEntityCheesemachine;
import tostimannetje.landleven.tileentity.TileEntityChicken;
import tostimannetje.landleven.tileentity.TileEntityCoffeemachine;
import tostimannetje.landleven.tileentity.TileEntityCookiemachine;
import tostimannetje.landleven.tileentity.TileEntityCornflakemachine;
import tostimannetje.landleven.tileentity.TileEntityCow;
import tostimannetje.landleven.tileentity.TileEntityDuck;
import tostimannetje.landleven.tileentity.TileEntityGoat;
import tostimannetje.landleven.tileentity.TileEntityHorseWhite;
import tostimannetje.landleven.tileentity.TileEntityJuicemachine;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntityMarket;
import tostimannetje.landleven.tileentity.TileEntityMill;
import tostimannetje.landleven.tileentity.TileEntityOx;
import tostimannetje.landleven.tileentity.TileEntityPancakemachine;
import tostimannetje.landleven.tileentity.TileEntityPastamachine;
import tostimannetje.landleven.tileentity.TileEntityPiemachine;
import tostimannetje.landleven.tileentity.TileEntityPig;
import tostimannetje.landleven.tileentity.TileEntityPopcornmachine;
import tostimannetje.landleven.tileentity.TileEntitySausagemachine;
import tostimannetje.landleven.tileentity.TileEntityStore;
import tostimannetje.landleven.tileentity.TileEntitySugarmachine;
import tostimannetje.landleven.tileentity.TileEntityTurkey;
import tostimannetje.landleven.tileentity.TileEntityWinemachine;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final List<Block> CROPS = new ArrayList<Block>();
	public static final List<BlockAnimal> ANIMALS = new ArrayList<BlockAnimal>();
	public static final List<BlockSapling> SAPLINGS = new ArrayList<BlockSapling>();

	public static final CreativeTabs llmachines = (new CreativeTabs(Reference.MODID + "machines") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(ModBlocks.wineMachine));
		}
	});
	
	public static BlockCrop cropWheat = new BlockCrop("crop_wheat", ModItems.itemWheat);
	public static BlockCrop cropClover = new BlockCrop("crop_clover", ModItems.itemClover);
	public static BlockCrop cropGrape = new BlockCrop("crop_grape", ModItems.itemGrape);
	public static BlockCrop cropCorn = new BlockCrop("crop_corn", ModItems.itemCorn);
	public static BlockCrop cropLavender = new BlockCrop("crop_lavender", ModItems.itemLavender);
	public static BlockCrop cropGrass = new BlockCrop("crop_grass", ModItems.itemGrass);
	public static BlockCrop cropOats = new BlockCrop("crop_oats", ModItems.itemOats);
	public static BlockCrop cropCucumber = new BlockCrop("crop_cucumber", ModItems.itemCucumber);
	public static BlockCrop cropChardonnay = new BlockCrop("crop_chardonnay", ModItems.itemChardonnay);
	public static BlockCrop cropTomato = new BlockCrop("crop_tomato", ModItems.itemTomato);
	public static BlockCrop cropCarrot = new BlockCrop("crop_carrot", ModItems.itemCarrot);
	public static BlockCrop cropCoffeeBean = new BlockCrop("crop_coffeebean", ModItems.itemCoffeeBean);
	public static BlockCrop cropPurpleRose = new BlockCrop("crop_purplerose", ModItems.itemPurpleRose);
	public static BlockCrop cropCactus = new BlockCrop("crop_cactus", ModItems.itemCactus);
	public static BlockCrop cropVanilla = new BlockCrop("crop_vanilla", ModItems.itemVanilla);
	public static BlockCrop cropCherryTomato = new BlockCrop("crop_cherrytomato", ModItems.itemCherryTomato);
	public static BlockCrop cropBasilicum = new BlockCrop("crop_basilicum", ModItems.itemBasilicum);
	public static BlockCrop cropRye = new BlockCrop("crop_rye", ModItems.itemRye);
	public static BlockCrop cropWhiteGrape = new BlockCrop("crop_whitegrape", ModItems.itemWhiteGrape);
	public static BlockCrop cropRice = new BlockCrop("crop_rice", ModItems.itemRice);
	public static BlockCrop cropPotato = new BlockCrop("crop_potato", ModItems.itemPotato);
	public static BlockCrop cropSugarcane = new BlockCrop("crop_sugarcane", ModItems.itemSugarcane);
	
	public static BlockSapling saplingApple = new BlockSapling("sapling_apple", 250);
	public static BlockTreeLeaf leafApple = new BlockTreeLeaf("leaf_apple");
	public static BlockTreeLog logApple = new BlockTreeLog("log_apple", ModItems.itemApple);
	public static BlockSapling saplingCherry = new BlockSapling("sapling_cherry", 300);
	public static BlockTreeLeaf leafCherry = new BlockTreeLeaf("leaf_cherry");
	public static BlockTreeLog logCherry = new BlockTreeLog("log_cherry", ModItems.itemCherry);
	public static BlockSapling saplingWhiteChocolate = new BlockSapling("sapling_whitechocolate", 800);
	public static BlockTreeLeaf leafWhiteChocolate = new BlockTreeLeaf("leaf_whitechocolate");
	public static BlockTreeLog logWhiteChocolate = new BlockTreeLog("log_whitechocolate", ModItems.itemWhiteChocolate);
	public static BlockSapling saplingMaple = new BlockSapling("sapling_maple", 600);
	public static BlockTreeLeaf leafMaple = new BlockTreeLeaf("leaf_maple");
	public static BlockTreeLog logMaple = new BlockTreeLog("log_maple", ModItems.itemMapleSyrup);
	public static BlockSapling saplingChocolate = new BlockSapling("sapling_chocolate", 900);
	public static BlockTreeLeaf leafChocolate = new BlockTreeLeaf("leaf_chocolate");
	public static BlockTreeLog logChocolate = new BlockTreeLog("log_chocolate", ModItems.itemChocolate);
	public static BlockSapling saplingLychee = new BlockSapling("sapling_lychee", 500);
	public static BlockTreeLeaf leafLychee = new BlockTreeLeaf("leaf_lychee");
	public static BlockTreeLog logLychee = new BlockTreeLog("log_lychee", ModItems.itemLychee);
	public static BlockSapling saplingOrange = new BlockSapling("sapling_orange", 1200);
	public static BlockTreeLeaf leafOrange = new BlockTreeLeaf("leaf_orange");
	public static BlockTreeLog logOrange = new BlockTreeLog("log_orange", ModItems.itemOrange);
	public static BlockSapling saplingWalnut = new BlockSapling("sapling_walnut", 1600);
	public static BlockTreeLeaf leafWalnut = new BlockTreeLeaf("leaf_walnut");
	public static BlockTreeLog logWalnut = new BlockTreeLog("log_walnut", ModItems.itemWalnut);
	public static BlockSapling saplingBanana = new BlockSapling("sapling_banana", 2100);
	public static BlockTreeLeaf leafBanana = new BlockTreeLeaf("leaf_banana");
	public static BlockTreeLog logBanana = new BlockTreeLog("log_banana", ModItems.itemBanana);
	public static BlockSapling saplingLime = new BlockSapling("sapling_lime", 1200);
	public static BlockTreeLeaf leafLime = new BlockTreeLeaf("leaf_lime");
	public static BlockTreeLog logLime = new BlockTreeLog("log_lime", ModItems.itemLime);
	public static BlockSapling saplingLemon = new BlockSapling("sapling_lemon", 1000);
	public static BlockTreeLeaf leafLemon = new BlockTreeLeaf("leaf_lemon");
	public static BlockTreeLog logLemon = new BlockTreeLog("log_lemon", ModItems.itemLemon);
	public static BlockSapling saplingAlmond = new BlockSapling("sapling_almond", 500);
	public static BlockTreeLeaf leafAlmond = new BlockTreeLeaf("leaf_almond");
	public static BlockTreeLog logAlmond = new BlockTreeLog("log_almond", ModItems.itemAlmond);
	public static BlockSapling saplingCoconut = new BlockSapling("sapling_coconut", 1500);
	public static BlockTreeLeaf leafCoconut = new BlockTreeLeaf("leaf_coconut");
	public static BlockTreeLog logCoconut = new BlockTreeLog("log_coconut", ModItems.itemCoconut);
	
	public static BlockStore blockStore = new BlockStore("store_block", Material.ROCK, 0);
	public static BlockMarket blockMarket = new BlockMarket("market_block", Material.ROCK, 1);
	public static BlockWinemachine wineMachine = new BlockWinemachine("machine_wine", 2);
	public static BlockCookiemachine cookieMachine = new BlockCookiemachine("machine_cookie", 3);
	public static BlockCheesemachine cheeseMachine = new BlockCheesemachine("machine_cheese", 4);
	public static BlockMill buildingMill = new BlockMill("building_mill", 5);
	public static BlockPastamachine pastaMachine = new BlockPastamachine("machine_pasta", 6);
	public static BlockJuicemachine juiceMachine = new BlockJuicemachine("machine_juice", 7);
	public static BlockCoffeemachine coffeeMachine = new BlockCoffeemachine("machine_coffee", 8);
	public static BlockSausagemachine sausageMachine = new BlockSausagemachine("machine_sausage", 9);
	public static BlockBreadmachine breadMachine = new BlockBreadmachine("machine_bread", 10);
	public static BlockPiemachine pieMachine = new BlockPiemachine("machine_pie", 11);
	public static BlockPopcornmachine popcornMachine = new BlockPopcornmachine("machine_popcorn", 12);
	public static BlockSugarmachine sugarMachine = new BlockSugarmachine("machine_sugar", 13);
	public static BlockPancakemachine pancakeMachine = new BlockPancakemachine("machine_pancake", 14);
	public static BlockCornflakemachine cornflakeMachine = new BlockCornflakemachine("machine_cornflake", 15);
	
	public static BlockAnimalCow animalCow = new BlockAnimalCow("animal_cow", 1001, 250);
	public static BlockAnimalChicken animalChicken = new BlockAnimalChicken("animal_chicken", 1002, 350);
	public static BlockAnimalOx animalOx = new BlockAnimalOx("animal_ox", 1003, 280);
	public static BlockAnimalBuffalo animalBuffalo = new BlockAnimalBuffalo("animal_buffalo", 1001, 300);
	public static BlockAnimalGoat animalGoat = new BlockAnimalGoat("animal_goat", 1001, 200);
	public static BlockAnimalHorseWhite animalHorseWhite = new BlockAnimalHorseWhite("animal_horsewhite", 1004, 280);
	public static BlockAnimalPig animalPig = new BlockAnimalPig("animal_pig", 1005, 400);
	public static BlockAnimalTurkey animalTurkey = new BlockAnimalTurkey("animal_turkey", 1006, 260);
	public static BlockAnimalDuck animalDuck = new BlockAnimalDuck("animal_duck", 1001, 370);
	
	public static void init() {
		CROPS.add(cropWheat);
		CROPS.add(cropClover);
		CROPS.add(cropGrape);
		CROPS.add(cropCorn);
		CROPS.add(cropLavender);
		CROPS.add(cropGrass);
		CROPS.add(cropOats);
		CROPS.add(cropCucumber);
		CROPS.add(cropChardonnay);
		CROPS.add(cropTomato);
		CROPS.add(cropCarrot);
		CROPS.add(cropCoffeeBean);
		CROPS.add(cropPurpleRose);
		CROPS.add(cropCactus);
		CROPS.add(cropVanilla);
		CROPS.add(cropCherryTomato);
		CROPS.add(cropBasilicum);
		CROPS.add(cropRye);
		CROPS.add(cropWhiteGrape);
		CROPS.add(cropRice);
		CROPS.add(cropPotato);
		CROPS.add(cropSugarcane);

		BLOCKS.add(logApple);
		BLOCKS.add(leafApple);
		SAPLINGS.add(saplingApple);
		BLOCKS.add(logCherry);
		BLOCKS.add(leafCherry);
		SAPLINGS.add(saplingCherry);
		BLOCKS.add(logWhiteChocolate);
		BLOCKS.add(leafWhiteChocolate);
		SAPLINGS.add(saplingWhiteChocolate);
		BLOCKS.add(logMaple);
		BLOCKS.add(leafMaple);
		SAPLINGS.add(saplingMaple);
		BLOCKS.add(logChocolate);
		BLOCKS.add(leafChocolate);
		SAPLINGS.add(saplingChocolate);
		BLOCKS.add(logLychee);
		BLOCKS.add(leafLychee);
		SAPLINGS.add(saplingLychee);
		BLOCKS.add(logOrange);
		BLOCKS.add(leafOrange);
		SAPLINGS.add(saplingOrange);
		BLOCKS.add(logWalnut);
		BLOCKS.add(leafWalnut);
		SAPLINGS.add(saplingWalnut);
		BLOCKS.add(logBanana);
		BLOCKS.add(leafBanana);
		SAPLINGS.add(saplingBanana);
		BLOCKS.add(logLime);
		BLOCKS.add(leafLime);
		SAPLINGS.add(saplingLime);
		BLOCKS.add(logLemon);
		BLOCKS.add(leafLemon);
		SAPLINGS.add(saplingLemon);
		BLOCKS.add(logAlmond);
		BLOCKS.add(leafAlmond);
		SAPLINGS.add(saplingAlmond);
		BLOCKS.add(logCoconut);
		BLOCKS.add(leafCoconut);
		SAPLINGS.add(saplingCoconut);
		
		for(Block sapling : SAPLINGS) {
			BLOCKS.add(sapling);
		}
		
		ANIMALS.add(animalCow);
		ANIMALS.add(animalChicken);
		ANIMALS.add(animalOx);
		ANIMALS.add(animalBuffalo);
		ANIMALS.add(animalGoat);
		ANIMALS.add(animalHorseWhite);
		ANIMALS.add(animalPig);
		ANIMALS.add(animalTurkey);
		ANIMALS.add(animalDuck);
		
		for(Block animal : ANIMALS) {
			BLOCKS.add(animal);
		}
		
		BLOCKS.add(blockStore);
		BLOCKS.add(blockMarket);
		BLOCKS.add(cookieMachine);
		BLOCKS.add(wineMachine);
		BLOCKS.add(cheeseMachine);
		BLOCKS.add(pastaMachine);
		BLOCKS.add(juiceMachine);
		BLOCKS.add(coffeeMachine);
		BLOCKS.add(sausageMachine);
		BLOCKS.add(breadMachine);
		BLOCKS.add(pieMachine);
		BLOCKS.add(popcornMachine);
		BLOCKS.add(sugarMachine);
		BLOCKS.add(pancakeMachine);
		BLOCKS.add(cornflakeMachine);
		
		BLOCKS.add(buildingMill);
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Block block : ModBlocks.BLOCKS) {
			event.getRegistry().register(block);
		}

		for (Block block : ModBlocks.CROPS) {
			event.getRegistry().register(block);
		}
		
		GameRegistry.registerTileEntity(TileEntityStore.class, new ResourceLocation(Reference.MODID, "tileEntityStore"));
		GameRegistry.registerTileEntity(TileEntityMarket.class, new ResourceLocation(Reference.MODID, "tileEntityMarket"));
		GameRegistry.registerTileEntity(TileEntityCookiemachine.class, new ResourceLocation(Reference.MODID, "tileEntityCookiemachine"));
		GameRegistry.registerTileEntity(TileEntityWinemachine.class, new ResourceLocation(Reference.MODID, "tileEntityWinemachine"));
		GameRegistry.registerTileEntity(TileEntityCheesemachine.class, new ResourceLocation(Reference.MODID, "tileEntityCheesemachine"));
		GameRegistry.registerTileEntity(TileEntityPastamachine.class, new ResourceLocation(Reference.MODID, "tileEntityPastamachine"));
		GameRegistry.registerTileEntity(TileEntityJuicemachine.class, new ResourceLocation(Reference.MODID, "tileEntityJuicemachine"));
		GameRegistry.registerTileEntity(TileEntityCoffeemachine.class, new ResourceLocation(Reference.MODID, "tileEntityCoffeemachine"));
		GameRegistry.registerTileEntity(TileEntitySausagemachine.class, new ResourceLocation(Reference.MODID, "tileEntitySausagemachine"));
		GameRegistry.registerTileEntity(TileEntityBreadmachine.class, new ResourceLocation(Reference.MODID, "tileEntityBreadmachine"));
		GameRegistry.registerTileEntity(TileEntityPiemachine.class, new ResourceLocation(Reference.MODID, "tileEntityPiemachine"));
		GameRegistry.registerTileEntity(TileEntityPopcornmachine.class, new ResourceLocation(Reference.MODID, "tileEntityPopcornmachine"));
		GameRegistry.registerTileEntity(TileEntitySugarmachine.class, new ResourceLocation(Reference.MODID, "tileEntitySugarmachine"));
		GameRegistry.registerTileEntity(TileEntityPancakemachine.class, new ResourceLocation(Reference.MODID, "tileEntityPancakemachine"));
		GameRegistry.registerTileEntity(TileEntityCornflakemachine.class, new ResourceLocation(Reference.MODID, "tileEntityCornflakemachine"));
		GameRegistry.registerTileEntity(TileEntityMill.class, new ResourceLocation(Reference.MODID, "tileEntityMill"));
		GameRegistry.registerTileEntity(TileEntityAnimal.class, new ResourceLocation(Reference.MODID, "tileEntityAnimal"));
		GameRegistry.registerTileEntity(TileEntityCow.class, new ResourceLocation(Reference.MODID, "tileEntityCow"));
		GameRegistry.registerTileEntity(TileEntityChicken.class, new ResourceLocation(Reference.MODID, "tileEntityChicken"));
		GameRegistry.registerTileEntity(TileEntityOx.class, new ResourceLocation(Reference.MODID, "tileEntityOx"));
		GameRegistry.registerTileEntity(TileEntityBuffalo.class, new ResourceLocation(Reference.MODID, "tileEntityBuffalo"));
		GameRegistry.registerTileEntity(TileEntityGoat.class, new ResourceLocation(Reference.MODID, "tileEntityGoat"));
		GameRegistry.registerTileEntity(TileEntityHorseWhite.class, new ResourceLocation(Reference.MODID, "tileEntityHorseWhite"));
		GameRegistry.registerTileEntity(TileEntityPig.class, new ResourceLocation(Reference.MODID, "tileEntityPig"));
		GameRegistry.registerTileEntity(TileEntityTurkey.class, new ResourceLocation(Reference.MODID, "tileEntityTurkey"));
		GameRegistry.registerTileEntity(TileEntityDuck.class, new ResourceLocation(Reference.MODID, "tileEntityDuck"));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Block block : ModBlocks.BLOCKS) {
			event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
		}
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (Block block : ModBlocks.BLOCKS) {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
		}

		for (Item item : ModItems.ITEMS) {
			ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
	}
}
