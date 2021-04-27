package tostimannetje.landleven.tileentity;

import java.util.Set;

import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntitySausagemachine extends TileEntityMachine{
	
	public TileEntitySausagemachine() {
		super();
		init();
	}
	
	public TileEntitySausagemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemBeef), new ItemStack(ModItems.itemSausageBeef));
		this.recipeList.put(new ItemStack(ModItems.itemPork), new ItemStack(ModItems.itemSausagePork));
		this.recipeList.put(new ItemStack(ModItems.itemTurkey), new ItemStack(ModItems.itemSausageTurkey));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {};
		
		setItemStackHandlers();
		setInput();
	}
	
}
