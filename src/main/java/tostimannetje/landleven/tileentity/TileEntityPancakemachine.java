package tostimannetje.landleven.tileentity;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityPancakemachine extends TileEntityMachine{
	
	public TileEntityPancakemachine() {
		super();
		init();
	}
	
	public TileEntityPancakemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemMapleSyrup), new ItemStack(ModItems.itemPancakeMaple));
		this.recipeList.put(new ItemStack(ModItems.itemChocolate), new ItemStack(ModItems.itemPancakeChocolate));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemFlourOats), new ItemStack(ModItems.itemEggDuck)};
		
		setItemStackHandlers();
		setInput();
	}
	
}
