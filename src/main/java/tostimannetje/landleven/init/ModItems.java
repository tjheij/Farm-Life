package tostimannetje.landleven.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.items.ItemAnimalProduct;
import tostimannetje.landleven.items.ItemBase;
import tostimannetje.landleven.items.ItemChip;
import tostimannetje.landleven.items.ItemCropProduct;
import tostimannetje.landleven.items.ItemProduct;
import tostimannetje.landleven.items.ItemQuestbook;
import tostimannetje.landleven.items.ItemSeed;
import tostimannetje.landleven.items.ItemStartingPouch;
import tostimannetje.landleven.items.ItemTreeProduct;

@Mod.EventBusSubscriber(modid=Reference.MODID)
public class ModItems {

	public static final List<ItemSeed> SEEDS = new ArrayList<ItemSeed>();
	public static final List<ItemChip> CHIPS = new ArrayList<ItemChip>();
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final CreativeTabs farm_life_crops = (new CreativeTabs(Reference.MODID + "crops") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(itemClover);
		}	
	});
	public static final CreativeTabs farm_life_trees = (new CreativeTabs(Reference.MODID + "trees") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Item.getItemFromBlock(ModBlocks.saplingApple));
		}	
	});
	public static final CreativeTabs farm_life_products = (new CreativeTabs(Reference.MODID + "products") {
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(itemWineGrape);
		}	
	});
	
	public static ItemBase itemInspector = new ItemBase("item_inspector");
	public static ItemBase itemFruitBasket = new ItemBase("item_fruitbasket");
	public static ItemQuestbook itemQuestbook = new ItemQuestbook("item_questbook");
	public static ItemStartingPouch itemStartingPouch = new ItemStartingPouch("item_startingpouch");
	
	public static ItemCropProduct itemWheat = new ItemCropProduct("item_wheat", 48);  
	public static ItemCropProduct itemClover = new ItemCropProduct("item_clover", 20);  
	public static ItemCropProduct itemGrape = new ItemCropProduct("item_grape", 116);   
	public static ItemCropProduct itemCorn = new ItemCropProduct("item_corn", 80);    
	public static ItemCropProduct itemLavender = new ItemCropProduct("item_lavender", 51);
	public static ItemCropProduct itemGrass = new ItemCropProduct("item_grass", 17);
	public static ItemCropProduct itemOats = new ItemCropProduct("item_oats", 47);
	public static ItemCropProduct itemCucumber = new ItemCropProduct("item_cucumber", 45);
	public static ItemCropProduct itemChardonnay = new ItemCropProduct("item_chardonnay", 68);
	public static ItemCropProduct itemTomato = new ItemCropProduct("item_tomato", 37);
	public static ItemCropProduct itemCarrot = new ItemCropProduct("item_carrot", 84);
	public static ItemCropProduct itemCoffeeBean = new ItemCropProduct("item_coffeebean", 117);
	public static ItemCropProduct itemPurpleRose = new ItemCropProduct("item_purplerose", 52);
	public static ItemCropProduct itemCactus = new ItemCropProduct("item_cactus", 69);
	public static ItemCropProduct itemVanilla = new ItemCropProduct("item_vanilla", 41);
	public static ItemCropProduct itemCherryTomato = new ItemCropProduct("item_cherrytomato", 50);
	public static ItemCropProduct itemBasilicum = new ItemCropProduct("item_basilicum", 38);
	public static ItemCropProduct itemRye = new ItemCropProduct("item_rye", 68);
	public static ItemCropProduct itemWhiteGrape = new ItemCropProduct("item_whitegrape", 43);
	public static ItemCropProduct itemRice = new ItemCropProduct("item_rice", 110);
	public static ItemCropProduct itemPotato = new ItemCropProduct("item_potato", 161);
	public static ItemCropProduct itemSugarcane = new ItemCropProduct("item_sugarcane", 120);
	
	public static ItemTreeProduct itemApple = new ItemTreeProduct("item_apple", 15);
	public static ItemTreeProduct itemCherry = new ItemTreeProduct("item_cherry", 20);
	public static ItemTreeProduct itemWhiteChocolate = new ItemTreeProduct("item_whitechocolate", 18);
	public static ItemTreeProduct itemMapleSyrup = new ItemTreeProduct("item_maplesyrup", 17);
	public static ItemTreeProduct itemChocolate = new ItemTreeProduct("item_chocolate", 55);
	public static ItemTreeProduct itemLychee = new ItemTreeProduct("item_lychee", 18);
	public static ItemTreeProduct itemOrange = new ItemTreeProduct("item_orange", 28);
	public static ItemTreeProduct itemWalnut = new ItemTreeProduct("item_walnut", 13);
	public static ItemTreeProduct itemBanana = new ItemTreeProduct("item_banana", 35);
	public static ItemTreeProduct itemLime = new ItemTreeProduct("item_lime", 15);
	public static ItemTreeProduct itemLemon = new ItemTreeProduct("item_lemon", 30);
	public static ItemTreeProduct itemAlmond = new ItemTreeProduct("item_almond", 25);
	public static ItemTreeProduct itemCoconut = new ItemTreeProduct("item_coconut", 30);
	
	public static ItemSeed itemSeedsWheat = new ItemSeed("itemseeds_wheat", ModBlocks.cropWheat, 42);
	public static ItemSeed itemSeedsClover = new ItemSeed("itemseeds_clover", ModBlocks.cropClover, 19);
	public static ItemSeed itemSeedsGrape = new ItemSeed("itemseeds_grape", ModBlocks.cropGrape, 112);      
	public static ItemSeed itemSeedsCorn = new ItemSeed("itemseeds_corn", ModBlocks.cropCorn, 76);        
	public static ItemSeed itemSeedsLavender = new ItemSeed("itemseeds_lavender", ModBlocks.cropLavender, 50);        
	public static ItemSeed itemSeedsGrass = new ItemSeed("itemseeds_grass", ModBlocks.cropGrass, 15);    
	public static ItemSeed itemSeedsOats = new ItemSeed("itemseeds_oats", ModBlocks.cropOats, 43);
	public static ItemSeed itemSeedsCucumber = new ItemSeed("itemseeds_cucumber", ModBlocks.cropCucumber, 42);
	public static ItemSeed itemSeedsChardonnay = new ItemSeed("itemseeds_chardonnay", ModBlocks.cropChardonnay, 60);
	public static ItemSeed itemSeedsTomato = new ItemSeed("itemseeds_tomato", ModBlocks.cropTomato, 26);
	public static ItemSeed itemSeedsCarrot = new ItemSeed("itemseeds_carrot", ModBlocks.cropCarrot, 75);
	public static ItemSeed itemSeedsCoffeeBean = new ItemSeed("itemseeds_coffeebean", ModBlocks.cropCoffeeBean, 110);
	public static ItemSeed itemSeedsPurpleRose = new ItemSeed("itemseeds_purplerose", ModBlocks.cropPurpleRose, 45);
	public static ItemSeed itemSeedsCactus = new ItemSeed("itemseeds_cactus", ModBlocks.cropCactus, 60);
	public static ItemSeed itemSeedsVanilla = new ItemSeed("itemseeds_vanilla", ModBlocks.cropVanilla, 34);
	public static ItemSeed itemSeedsCherryTomato = new ItemSeed("itemseeds_cherrytomato", ModBlocks.cropCherryTomato, 40);
	public static ItemSeed itemSeedsBasilicum = new ItemSeed("itemseeds_basilicum", ModBlocks.cropBasilicum, 30);
	public static ItemSeed itemSeedsRye = new ItemSeed("itemseeds_rye", ModBlocks.cropRye, 54);
	public static ItemSeed itemSeedsWhiteGrape = new ItemSeed("itemseeds_whitegrape", ModBlocks.cropWhiteGrape, 65);
	public static ItemSeed itemSeedsRice = new ItemSeed("itemseeds_rice", ModBlocks.cropRice, 105);
	public static ItemSeed itemSeedsPotato = new ItemSeed("itemseeds_potato", ModBlocks.cropPotato, 150);
	public static ItemSeed itemSeedsSugarcane = new ItemSeed("itemseeds_sugarcane", ModBlocks.cropSugarcane, 110);
	
	public static ItemAnimalProduct itemMilk = new ItemAnimalProduct("itemanimal_milk", 23);
	public static ItemAnimalProduct itemEgg = new ItemAnimalProduct("itemanimal_egg", 102);
	public static ItemAnimalProduct itemBeef = new ItemAnimalProduct("itemanimal_beef", 20);
	public static ItemAnimalProduct itemMilkBuffalo = new ItemAnimalProduct("itemanimal_milkbuffalo", 49);
	public static ItemAnimalProduct itemMilkGoat = new ItemAnimalProduct("itemanimal_milkgoat", 37);
	public static ItemAnimalProduct itemMilkHorse = new ItemAnimalProduct("itemanimal_milkhorse", 105);
	public static ItemAnimalProduct itemTurkey = new ItemAnimalProduct("itemanimal_turkey", 135);
	public static ItemAnimalProduct itemPork = new ItemAnimalProduct("itemanimal_pork", 115);
	public static ItemAnimalProduct itemEggDuck = new ItemAnimalProduct("itemanimal_eggduck", 76);
	
	public static ItemProduct itemCheese = new ItemProduct("itemmachine_cheese", 28);
	public static ItemProduct itemCheeseBuffalo = new ItemProduct("itemmachine_cheesebuffalo", 55);
	public static ItemProduct itemCheeseGoat = new ItemProduct("itemmachine_cheesegoat", 47);
	public static ItemProduct itemWineGrape = new ItemProduct("itemmachine_winegrape", 124);
	public static ItemProduct itemWineChardonnay = new ItemProduct("itemmachine_winechardonnay", 81);
	public static ItemProduct itemWineWhite = new ItemProduct("itemmachine_winewhite", 86);
	public static ItemProduct itemFlourWheat = new ItemProduct("itemmachine_flourwheat", 62);
	public static ItemProduct itemFlourOats = new ItemProduct("itemmachine_flouroats", 63);
	public static ItemProduct itemFlourCorn = new ItemProduct("itemmachine_flourcorn", 100);
	public static ItemProduct itemFlourRye = new ItemProduct("itemmachine_flourrye", 84);
	public static ItemProduct itemPastaBasilicum = new ItemProduct("itemmachine_pastabasilicum", 249);
	public static ItemProduct itemPastaCherryTomato = new ItemProduct("itemmachine_pastacherrytomato", 263);
	public static ItemProduct itemPastaCheese = new ItemProduct("itemmachine_pastacheese", 250);
	public static ItemProduct itemCookieGrape = new ItemProduct("itemmachine_cookiegrape", 253);
	public static ItemProduct itemCookieWhiteChocolate = new ItemProduct("itemmachine_cookiewhitechocolate", 160);
	public static ItemProduct itemCookieOats = new ItemProduct("itemmachine_cookieoats", 177);
	public static ItemProduct itemJuiceApple = new ItemProduct("itemmachine_juiceapple", 23);
	public static ItemProduct itemJuiceCherry = new ItemProduct("itemmachine_juicecherry", 40);
	public static ItemProduct itemJuiceGrape = new ItemProduct("itemmachine_juicegrape", 127);
	public static ItemProduct itemJuiceCarrot = new ItemProduct("itemmachine_juicecarrot", 92);
	public static ItemProduct itemJuiceTomato = new ItemProduct("itemmachine_juicetomato", 52);
	public static ItemProduct itemJuiceOrange = new ItemProduct("itemmachine_juiceorange", 51);
	public static ItemProduct itemJuiceLime = new ItemProduct("itemmachine_juicelime", 22);
	public static ItemProduct itemJuiceLemon = new ItemProduct("itemmachine_juicelemon", 38);
	public static ItemProduct itemCoffee = new ItemProduct("itemmachine_coffee", 177);
	public static ItemProduct itemCoffeeWhite = new ItemProduct("itemmachine_coffeewhite", 173);
	public static ItemProduct itemSausageBeef = new ItemProduct("itemmachine_sausagebeef", 24);
	public static ItemProduct itemSausagePork = new ItemProduct("itemmachine_sausagepork", 201);
	public static ItemProduct itemSausageTurkey = new ItemProduct("itemmachine_sausageturkey", 170);
	public static ItemProduct itemBreadWheat = new ItemProduct("itemmachine_breadwheat", 248);
	public static ItemProduct itemBreadOats = new ItemProduct("itemmachine_breadoats", 240);
	public static ItemProduct itemBreadCorn = new ItemProduct("itemmachine_breadcorn", 265);
	public static ItemProduct itemBreadRye = new ItemProduct("itemmachine_breadrye", 276);
	public static ItemProduct itemPieCherry = new ItemProduct("itemmachine_piecherry", 283);
	public static ItemProduct itemPieChocolate = new ItemProduct("itemmachine_piechocolate", 324);
	public static ItemProduct itemPieAlmond = new ItemProduct("itemmachine_piealmond", 324);
	public static ItemProduct itemPieBanana = new ItemProduct("itemmachine_piebanana", 299);
	public static ItemProduct itemPieLemon = new ItemProduct("itemmachine_pielemon", 345);
	public static ItemProduct itemPieCoconut = new ItemProduct("itemmachine_piecoconut", 329);
	public static ItemProduct itemPopcornVanilla = new ItemProduct("itemmachine_popcornvanilla", 172);
	public static ItemProduct itemPopcornSugar = new ItemProduct("itemmachine_popcornsugar", 272);
	public static ItemProduct itemPopcornChocolate = new ItemProduct("itemmachine_popcornchocolate", 202);
	public static ItemProduct itemSugar = new ItemProduct("itemmachine_sugar", 138);
	public static ItemProduct itemSugarMaple = new ItemProduct("itemmachine_sugarmaple", 29);
	public static ItemProduct itemPancakeMaple = new ItemProduct("itemmachine_pancakemaple", 176);
	public static ItemProduct itemPancakeChocolate = new ItemProduct("itemmachine_pancakechocolate", 215);
	public static ItemProduct itemCornflakeGrape = new ItemProduct("itemmachine_cornflakegrape", 259);
	public static ItemProduct itemCornflakeChocolate = new ItemProduct("itemmachine_cornflakechocolate", 210);
	public static ItemProduct itemCornflakeWalnut = new ItemProduct("itemmachine_cornflakewalnut", 184);
	public static ItemProduct itemCornflakeAlmond = new ItemProduct("itemmachine_cornflakealmond", 195);
	public static ItemProduct itemCornflakeBanana = new ItemProduct("itemmachine_cornflakebanana", 229);
	
	public static ItemChip itemChipWinemachine = new ItemChip("itemchip_winemachine", 800);
	public static ItemChip itemChipCookiemachine = new ItemChip("itemchip_cookiemachine", 1200);
	public static ItemChip itemChipCheesemachine = new ItemChip("itemchip_cheesemachine", 500);
	public static ItemChip itemChipMill = new ItemChip("itemchip_mill", 700);
	public static ItemChip itemChipPastamachine = new ItemChip("itemchip_pastamachine", 1000);
	public static ItemChip itemChipJuicemachine = new ItemChip("itemchip_juicemachine", 900);
	public static ItemChip itemChipCoffeemachine = new ItemChip("itemchip_coffeemachine", 1100);
	public static ItemChip itemChipSausagemachine = new ItemChip("itemchip_sausagemachine", 800);
	public static ItemChip itemChipBreadmachine = new ItemChip("itemchip_breadmachine", 600);
	public static ItemChip itemChipPiemachine = new ItemChip("itemchip_piemachine", 500);
	public static ItemChip itemChipPopcornmachine = new ItemChip("itemchip_popcornmachine", 900);
	public static ItemChip itemChipSugarmachine = new ItemChip("itemchip_sugarmachine", 1000);
	public static ItemChip itemChipPancakemachine = new ItemChip("itemchip_pancakemachine", 1000);
	public static ItemChip itemChipCornflakemachine = new ItemChip("itemchip_cornflakemachine", 1200);
	
	public static void initProducts(){
		ITEMS.add(itemWheat);
		ITEMS.add(itemClover);
		ITEMS.add(itemGrape);
		ITEMS.add(itemCorn);
		ITEMS.add(itemLavender);
		ITEMS.add(itemGrass);
		ITEMS.add(itemOats);
		ITEMS.add(itemCucumber);
		ITEMS.add(itemChardonnay);
		ITEMS.add(itemTomato);
		ITEMS.add(itemCarrot);
		ITEMS.add(itemCoffeeBean);
		ITEMS.add(itemPurpleRose);
		ITEMS.add(itemCactus);
		ITEMS.add(itemVanilla);
		ITEMS.add(itemCherryTomato);
		ITEMS.add(itemBasilicum);
		ITEMS.add(itemRye);
		ITEMS.add(itemWhiteGrape);
		ITEMS.add(itemRice);
		ITEMS.add(itemPotato);
		ITEMS.add(itemSugarcane);
		
		ITEMS.add(itemApple);
		ITEMS.add(itemCherry);
		ITEMS.add(itemWhiteChocolate);
		ITEMS.add(itemMapleSyrup);
		ITEMS.add(itemChocolate);
		ITEMS.add(itemLychee);
		ITEMS.add(itemOrange);
		ITEMS.add(itemWalnut);
		ITEMS.add(itemBanana);
		ITEMS.add(itemLime);
		ITEMS.add(itemLemon);
		ITEMS.add(itemAlmond);
		ITEMS.add(itemCoconut);
	}
	
	
	public static void init(){
		ITEMS.add(itemInspector);
		ITEMS.add(itemFruitBasket);
		ITEMS.add(itemQuestbook);
		ITEMS.add(itemStartingPouch);
		
		SEEDS.add(itemSeedsWheat);
		SEEDS.add(itemSeedsClover);
		SEEDS.add(itemSeedsGrape);
		SEEDS.add(itemSeedsCorn);
		SEEDS.add(itemSeedsLavender);
		SEEDS.add(itemSeedsGrass);
		SEEDS.add(itemSeedsOats);
		SEEDS.add(itemSeedsCucumber);
		SEEDS.add(itemSeedsChardonnay);
		SEEDS.add(itemSeedsTomato);
		SEEDS.add(itemSeedsCarrot);
		SEEDS.add(itemSeedsCoffeeBean);
		SEEDS.add(itemSeedsPurpleRose);
		SEEDS.add(itemSeedsCactus);
		SEEDS.add(itemSeedsVanilla);
		SEEDS.add(itemSeedsCherryTomato);
		SEEDS.add(itemSeedsBasilicum);
		SEEDS.add(itemSeedsRye);
		SEEDS.add(itemSeedsWhiteGrape);
		SEEDS.add(itemSeedsRice);
		SEEDS.add(itemSeedsPotato);
		SEEDS.add(itemSeedsSugarcane);
		
		for(ItemSeed seed : SEEDS) {
			ITEMS.add(seed);
		}
		
		ITEMS.add(itemMilk);
		ITEMS.add(itemEgg);
		ITEMS.add(itemBeef);
		ITEMS.add(itemMilkBuffalo);
		ITEMS.add(itemMilkGoat);
		ITEMS.add(itemMilkHorse);
		ITEMS.add(itemTurkey);
		ITEMS.add(itemPork);
		ITEMS.add(itemEggDuck);
		
		ITEMS.add(itemCheese);
		ITEMS.add(itemCheeseBuffalo);
		ITEMS.add(itemCheeseGoat);
		ITEMS.add(itemWineGrape);
		ITEMS.add(itemWineChardonnay);
		ITEMS.add(itemWineWhite);
		ITEMS.add(itemFlourWheat);
		ITEMS.add(itemFlourOats);
		ITEMS.add(itemFlourCorn);
		ITEMS.add(itemFlourRye);
		ITEMS.add(itemPastaBasilicum);
		ITEMS.add(itemPastaCherryTomato);
		ITEMS.add(itemPastaCheese);
		ITEMS.add(itemCookieGrape);
		ITEMS.add(itemCookieWhiteChocolate);
		ITEMS.add(itemCookieOats);
		ITEMS.add(itemJuiceApple);
		ITEMS.add(itemJuiceCherry);
		ITEMS.add(itemJuiceGrape);
		ITEMS.add(itemJuiceCarrot);
		ITEMS.add(itemJuiceTomato);
		ITEMS.add(itemJuiceOrange);
		ITEMS.add(itemJuiceLime);
		ITEMS.add(itemJuiceLemon);
		ITEMS.add(itemCoffee);
		ITEMS.add(itemCoffeeWhite);
		ITEMS.add(itemSausageBeef);
		ITEMS.add(itemSausagePork);
		ITEMS.add(itemSausageTurkey);
		ITEMS.add(itemBreadWheat);
		ITEMS.add(itemBreadOats);
		ITEMS.add(itemBreadCorn);
		ITEMS.add(itemBreadRye);
		ITEMS.add(itemPieCherry);
		ITEMS.add(itemPieChocolate);
		ITEMS.add(itemPieAlmond);
		ITEMS.add(itemPieBanana);
		ITEMS.add(itemPieLemon);
		ITEMS.add(itemPieCoconut);
		ITEMS.add(itemPopcornVanilla);
		ITEMS.add(itemPopcornSugar);
		ITEMS.add(itemPopcornChocolate);
		ITEMS.add(itemSugar);
		ITEMS.add(itemSugarMaple);
		ITEMS.add(itemPancakeMaple);
		ITEMS.add(itemPancakeChocolate);
		ITEMS.add(itemCornflakeGrape);
		ITEMS.add(itemCornflakeChocolate);
		ITEMS.add(itemCornflakeWalnut);
		ITEMS.add(itemCornflakeAlmond);
		ITEMS.add(itemCornflakeBanana);
		
		CHIPS.add(itemChipCheesemachine);
		CHIPS.add(itemChipWinemachine);
		CHIPS.add(itemChipMill);
		CHIPS.add(itemChipCookiemachine);
		CHIPS.add(itemChipPastamachine);
		CHIPS.add(itemChipJuicemachine);
		CHIPS.add(itemChipCoffeemachine);
		CHIPS.add(itemChipSausagemachine);
		CHIPS.add(itemChipBreadmachine);
		CHIPS.add(itemChipPiemachine);
		CHIPS.add(itemChipPopcornmachine);
		CHIPS.add(itemChipSugarmachine);
		CHIPS.add(itemChipPancakemachine);
		CHIPS.add(itemChipCornflakemachine);
		
		for(ItemChip chip : CHIPS) {
			ITEMS.add(chip);
		}
	}
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
		for(Item item : ITEMS) {
			event.getRegistry().register(item);
		}
    }
}
