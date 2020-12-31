package tostimannetje.landleven.tileentity;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityCheesemachine extends TileEntityMachine{
	
	public TileEntityCheesemachine() {
		super();
		init();
	}
	
	public TileEntityCheesemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemMilk), new ItemStack(ModItems.itemCheese));
		this.recipeList.put(new ItemStack(ModItems.itemMilkBuffalo), new ItemStack(ModItems.itemCheeseBuffalo));
		this.recipeList.put(new ItemStack(ModItems.itemMilkGoat), new ItemStack(ModItems.itemCheeseGoat));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {};
		
		setItemStackHandlers();
		setInput();
	}
	
}
