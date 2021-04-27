package tostimannetje.landleven.tileentity;

import java.util.Set;

import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntitySugarmachine extends TileEntityMachine{
	
	public TileEntitySugarmachine() {
		super();
		init();
	}
	
	public TileEntitySugarmachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemSugarcane), new ItemStack(ModItems.itemSugar));
		this.recipeList.put(new ItemStack(ModItems.itemMapleSyrup), new ItemStack(ModItems.itemSugarMaple));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {};
		
		setItemStackHandlers();
		setInput();
	}
	
}
