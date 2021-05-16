package tostimannetje.landleven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.items.ItemBaseProduct;
import tostimannetje.landleven.tileentity.*;

public class RecipeHandler {

	public static HashMap<Class<? extends TileEntityProducer>, List<ItemBaseProduct>> inputs = new HashMap<Class<? extends TileEntityProducer>, List<ItemBaseProduct>>();
	public static HashMap<Class<? extends TileEntityProducer>, List<ItemBaseProduct>> extraInputs = new HashMap<Class<? extends TileEntityProducer>, List<ItemBaseProduct>>();
	public static HashMap<Class<? extends TileEntityProducer>, List<ItemBaseProduct>> outputs = new HashMap<Class<? extends TileEntityProducer>, List<ItemBaseProduct>>();
	
	public static void init() {
		//Cow
		inputs.put(TileEntityCow.class, Arrays.asList(ModItems.itemClover));
		outputs.put(TileEntityCow.class, Arrays.asList(ModItems.itemMilk));
		
		//Chicken
		inputs.put(TileEntityChicken.class, Arrays.asList(ModItems.itemCorn));
		outputs.put(TileEntityChicken.class, Arrays.asList(ModItems.itemEgg));
		
		//Ox
		inputs.put(TileEntityChicken.class, Arrays.asList(ModItems.itemGrass));
		outputs.put(TileEntityChicken.class, Arrays.asList(ModItems.itemBeef));
		
		//Buffalo
		inputs.put(TileEntityBuffalo.class, Arrays.asList(ModItems.itemCucumber));
		outputs.put(TileEntityBuffalo.class, Arrays.asList(ModItems.itemMilkBuffalo));
		
		//Goat
		inputs.put(TileEntityGoat.class, Arrays.asList(ModItems.itemGrass));
		outputs.put(TileEntityGoat.class, Arrays.asList(ModItems.itemMilkGoat));
		
		//White Horse
		inputs.put(TileEntityHorseWhite.class, Arrays.asList(ModItems.itemCarrot));
		outputs.put(TileEntityHorseWhite.class, Arrays.asList(ModItems.itemMilkHorse));
		
		//Pig
		inputs.put(TileEntityPig.class, Arrays.asList(ModItems.itemPotato));
		outputs.put(TileEntityPig.class, Arrays.asList(ModItems.itemPork));
		
		//Turkey
		inputs.put(TileEntityTurkey.class, Arrays.asList(ModItems.itemRice));
		outputs.put(TileEntityTurkey.class, Arrays.asList(ModItems.itemTurkey));
		
		//Duck
		inputs.put(TileEntityDuck.class, Arrays.asList(ModItems.itemWheat));
		outputs.put(TileEntityDuck.class, Arrays.asList(ModItems.itemEggDuck));
				
		//Wine machine
		inputs.put(TileEntityWinemachine.class, Arrays.asList(ModItems.itemGrape, ModItems.itemWhiteGrape, ModItems.itemChardonnay));
		outputs.put(TileEntityWinemachine.class, Arrays.asList(ModItems.itemWineGrape, ModItems.itemWineWhite, ModItems.itemWineChardonnay));
		
		//Cookie machine
		inputs.put(TileEntityCookiemachine.class, Arrays.asList(ModItems.itemGrape, ModItems.itemWhiteChocolate, ModItems.itemOats));
		extraInputs.put(TileEntityCookiemachine.class, Arrays.asList(ModItems.itemFlourWheat, ModItems.itemMilk));
		outputs.put(TileEntityCookiemachine.class, Arrays.asList(ModItems.itemCookieGrape, ModItems.itemCookieWhiteChocolate, ModItems.itemCookieOats));
		
		//Cheese machine
		inputs.put(TileEntityCheesemachine.class, Arrays.asList(ModItems.itemMilk, ModItems.itemMilkBuffalo, ModItems.itemMilkGoat));
		outputs.put(TileEntityCheesemachine.class, Arrays.asList(ModItems.itemCheese, ModItems.itemCheeseBuffalo, ModItems.itemCheeseGoat));
		
		//Mill
		inputs.put(TileEntityMill.class, Arrays.asList(ModItems.itemWheat, ModItems.itemOats, ModItems.itemCorn, ModItems.itemRye));
		outputs.put(TileEntityMill.class, Arrays.asList(ModItems.itemFlourWheat, ModItems.itemFlourOats, ModItems.itemFlourCorn, ModItems.itemFlourRye));
		
		//Pasta machine
		inputs.put(TileEntityPastamachine.class, Arrays.asList(ModItems.itemBasilicum, ModItems.itemCherryTomato, ModItems.itemCheese));
		extraInputs.put(TileEntityPastamachine.class, Arrays.asList(ModItems.itemFlourWheat, ModItems.itemEgg));
		outputs.put(TileEntityPastamachine.class, Arrays.asList(ModItems.itemPastaBasilicum, ModItems.itemPastaCherryTomato, ModItems.itemPastaCheese));
		
		//Juice machine
		inputs.put(TileEntityJuicemachine.class, Arrays.asList(ModItems.itemApple, ModItems.itemCherry, ModItems.itemGrape, ModItems.itemCarrot, ModItems.itemTomato, ModItems.itemOrange, ModItems.itemLime, ModItems.itemLemon));
		outputs.put(TileEntityJuicemachine.class, Arrays.asList(ModItems.itemJuiceApple, ModItems.itemJuiceCherry, ModItems.itemJuiceGrape, ModItems.itemJuiceCarrot, ModItems.itemJuiceTomato, ModItems.itemJuiceOrange, ModItems.itemJuiceLime, ModItems.itemJuiceLemon));		
				
		//Coffee machine
		inputs.put(TileEntityCoffeemachine.class, Arrays.asList(ModItems.itemMilk, ModItems.itemWhiteChocolate));
		extraInputs.put(TileEntityCoffeemachine.class, Arrays.asList(ModItems.itemCoffeeBean));
		outputs.put(TileEntityCoffeemachine.class, Arrays.asList(ModItems.itemCoffee, ModItems.itemCoffeeWhite));
		
		//Sausage machine
		inputs.put(TileEntitySausagemachine.class, Arrays.asList(ModItems.itemBeef, ModItems.itemPork, ModItems.itemTurkey));
		outputs.put(TileEntitySausagemachine.class, Arrays.asList(ModItems.itemSausageBeef, ModItems.itemSausagePork, ModItems.itemSausageTurkey));
		
		//Bread machine
		inputs.put(TileEntityBreadmachine.class, Arrays.asList(ModItems.itemFlourWheat, ModItems.itemFlourOats, ModItems.itemFlourCorn, ModItems.itemFlourRye));
		extraInputs.put(TileEntityBreadmachine.class, Arrays.asList(ModItems.itemMilk, ModItems.itemEgg));
		outputs.put(TileEntityBreadmachine.class, Arrays.asList(ModItems.itemBreadWheat, ModItems.itemBreadOats, ModItems.itemBreadCorn, ModItems.itemBreadRye));
		
		//Pie machine
		inputs.put(TileEntityPiemachine.class, Arrays.asList(ModItems.itemCherry, ModItems.itemChocolate, ModItems.itemAlmond, ModItems.itemBanana, ModItems.itemLemon, ModItems.itemCoconut));
		extraInputs.put(TileEntityPiemachine.class, Arrays.asList(ModItems.itemEgg, ModItems.itemFlourWheat));
		outputs.put(TileEntityPiemachine.class, Arrays.asList(ModItems.itemPieCherry, ModItems.itemPieChocolate, ModItems.itemPieAlmond, ModItems.itemPieBanana, ModItems.itemPieLemon, ModItems.itemPieCoconut));
	
		//Popcorn machine
		inputs.put(TileEntityPopcornmachine.class, Arrays.asList(ModItems.itemVanilla, ModItems.itemSugar, ModItems.itemChocolate));
		extraInputs.put(TileEntityPopcornmachine.class, Arrays.asList(ModItems.itemCorn));
		outputs.put(TileEntityPopcornmachine.class, Arrays.asList(ModItems.itemPopcornVanilla, ModItems.itemPopcornSugar, ModItems.itemPopcornChocolate));
		
		//Sugar machine
		inputs.put(TileEntitySugarmachine.class, Arrays.asList(ModItems.itemSugarcane, ModItems.itemMapleSyrup));
		outputs.put(TileEntitySugarmachine.class, Arrays.asList(ModItems.itemSugar, ModItems.itemSugarMaple));
		
		//Pancake machine
		inputs.put(TileEntityPancakemachine.class, Arrays.asList(ModItems.itemMapleSyrup, ModItems.itemChocolate));
		extraInputs.put(TileEntityPancakemachine.class, Arrays.asList(ModItems.itemFlourOats, ModItems.itemEggDuck));
		outputs.put(TileEntityPancakemachine.class, Arrays.asList(ModItems.itemPancakeMaple, ModItems.itemPancakeChocolate));
		
		//Cornflake machine
		inputs.put(TileEntityCornflakemachine.class, Arrays.asList(ModItems.itemGrape, ModItems.itemChocolate, ModItems.itemWalnut, ModItems.itemAlmond, ModItems.itemBanana));
		extraInputs.put(TileEntityCornflakemachine.class, Arrays.asList(ModItems.itemMilk, ModItems.itemCorn));
		outputs.put(TileEntityCornflakemachine.class, Arrays.asList(ModItems.itemCornflakeGrape, ModItems.itemCornflakeChocolate, ModItems.itemCornflakeWalnut, ModItems.itemCornflakeAlmond, ModItems.itemCornflakeBanana));
	
		Set<Class<? extends TileEntityProducer>> set = outputs.keySet();
		Iterator<Class<? extends TileEntityProducer>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Class<? extends TileEntityProducer> classname = iterator.next();
			List<ItemBaseProduct> inList = inputs.get(classname);
			List<ItemBaseProduct> extraList = extraInputs.get(classname);
			List<ItemBaseProduct> outList = outputs.get(classname);
			
			for(int j = 0; j < outList.size(); j++) {
				if(outList.get(j).ingredients.isEmpty()) {
					outList.get(j).ingredients = new ArrayList<>();
					outList.get(j).ingredients.add(inList.get(j));
					if(extraList != null) {
						outList.get(j).ingredients.addAll(extraList);
					}
				}
			}
		}
	}
	
	public static <T extends TileEntityProducer> List<ItemBaseProduct> getInputs(T producer) {
		return inputs.get(producer.getClass());
	}
	
	public static <T extends TileEntityProducer> List<ItemBaseProduct> getExtraInputs(T producer) {
		return extraInputs.get(producer.getClass());
	}
	
	public static <T extends TileEntityProducer> List<ItemBaseProduct> getOutputs(T producer) {
		return outputs.get(producer.getClass());
	}
}
