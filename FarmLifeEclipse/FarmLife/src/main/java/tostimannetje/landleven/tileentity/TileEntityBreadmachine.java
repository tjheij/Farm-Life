package tostimannetje.landleven.tileentity;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityBreadmachine extends TileEntityMachine{
	
	public TileEntityBreadmachine() {
		super();
		init();
	}
	
	public TileEntityBreadmachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemFlourWheat), new ItemStack(ModItems.itemBreadWheat));
		this.recipeList.put(new ItemStack(ModItems.itemFlourOats), new ItemStack(ModItems.itemBreadOats));
		this.recipeList.put(new ItemStack(ModItems.itemFlourCorn), new ItemStack(ModItems.itemBreadCorn));
		this.recipeList.put(new ItemStack(ModItems.itemFlourRye), new ItemStack(ModItems.itemBreadRye));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemMilk), new ItemStack(ModItems.itemEgg)};
		
		setItemStackHandlers();
		setInput();
	}
	
}
