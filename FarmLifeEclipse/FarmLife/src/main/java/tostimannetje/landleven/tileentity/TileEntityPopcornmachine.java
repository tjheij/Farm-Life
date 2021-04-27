package tostimannetje.landleven.tileentity;

import java.util.Set;

import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityPopcornmachine extends TileEntityMachine{
	
	public TileEntityPopcornmachine() {
		super();
		init();
	}
	
	public TileEntityPopcornmachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemVanilla), new ItemStack(ModItems.itemPopcornVanilla));
		this.recipeList.put(new ItemStack(ModItems.itemSugar), new ItemStack(ModItems.itemPopcornSugar));
		this.recipeList.put(new ItemStack(ModItems.itemChocolate), new ItemStack(ModItems.itemPopcornChocolate));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemCorn)};
		
		setItemStackHandlers();
		setInput();
	}
	
}
