package tostimannetje.landleven.tileentity;

import java.util.Set;

import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityWinemachine extends TileEntityMachine{

	public TileEntityWinemachine() {
		super();
		init();
	}
	
	public TileEntityWinemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemGrape), new ItemStack(ModItems.itemWineGrape));
		this.recipeList.put(new ItemStack(ModItems.itemChardonnay), new ItemStack(ModItems.itemWineChardonnay));
		this.recipeList.put(new ItemStack(ModItems.itemWhiteGrape), new ItemStack(ModItems.itemWineWhite));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {};
		
		setItemStackHandlers();
		setInput();
	}
}
