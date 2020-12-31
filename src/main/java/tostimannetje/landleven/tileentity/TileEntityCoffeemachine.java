package tostimannetje.landleven.tileentity;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityCoffeemachine extends TileEntityMachine{
	
	public TileEntityCoffeemachine() {
		super();
		init();
	}
	
	public TileEntityCoffeemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemMilk), new ItemStack(ModItems.itemCoffee));
		this.recipeList.put(new ItemStack(ModItems.itemWhiteChocolate), new ItemStack(ModItems.itemCoffeeWhite));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemCoffeeBean)};
		
		setItemStackHandlers();
		setInput();
	}
	
}
