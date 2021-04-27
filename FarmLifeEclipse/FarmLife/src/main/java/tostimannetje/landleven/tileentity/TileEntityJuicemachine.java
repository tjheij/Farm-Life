package tostimannetje.landleven.tileentity;

import java.util.Set;

import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityJuicemachine extends TileEntityMachine{
	
	public TileEntityJuicemachine() {
		super();
		init();
	}
	
	public TileEntityJuicemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemApple), new ItemStack(ModItems.itemJuiceApple));
		this.recipeList.put(new ItemStack(ModItems.itemCherry), new ItemStack(ModItems.itemJuiceCherry));
		this.recipeList.put(new ItemStack(ModItems.itemGrape), new ItemStack(ModItems.itemJuiceGrape));
		this.recipeList.put(new ItemStack(ModItems.itemCarrot), new ItemStack(ModItems.itemJuiceCarrot));
		this.recipeList.put(new ItemStack(ModItems.itemTomato), new ItemStack(ModItems.itemJuiceTomato));
		this.recipeList.put(new ItemStack(ModItems.itemOrange), new ItemStack(ModItems.itemJuiceOrange));
		this.recipeList.put(new ItemStack(ModItems.itemLime), new ItemStack(ModItems.itemJuiceLime));
		this.recipeList.put(new ItemStack(ModItems.itemLemon), new ItemStack(ModItems.itemJuiceLemon));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {};
		
		setItemStackHandlers();
		setInput();
	}
}
