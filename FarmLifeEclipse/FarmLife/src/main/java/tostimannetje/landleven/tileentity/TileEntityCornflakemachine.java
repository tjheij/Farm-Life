package tostimannetje.landleven.tileentity;

import java.util.Set;

import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityCornflakemachine extends TileEntityMachine{
	
	public TileEntityCornflakemachine() {
		super();
		init();
	}
	
	public TileEntityCornflakemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemFlourWheat), new ItemStack(ModItems.itemBreadWheat));
		this.recipeList.put(new ItemStack(ModItems.itemFlourOats), new ItemStack(ModItems.itemBreadOats));
		this.recipeList.put(new ItemStack(ModItems.itemFlourCorn), new ItemStack(ModItems.itemBreadCorn));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemMilk), new ItemStack(ModItems.itemCorn)};
		
		setItemStackHandlers();
		setInput();
	}
	
}
