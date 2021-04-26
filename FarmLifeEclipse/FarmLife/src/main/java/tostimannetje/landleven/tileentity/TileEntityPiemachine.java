package tostimannetje.landleven.tileentity;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityPiemachine extends TileEntityMachine{
	
	public TileEntityPiemachine() {
		super();
		init();
	}
	
	public TileEntityPiemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemCherry), new ItemStack(ModItems.itemPieCherry));
		this.recipeList.put(new ItemStack(ModItems.itemChocolate), new ItemStack(ModItems.itemPieChocolate));
		this.recipeList.put(new ItemStack(ModItems.itemAlmond), new ItemStack(ModItems.itemPieAlmond));
		this.recipeList.put(new ItemStack(ModItems.itemBanana), new ItemStack(ModItems.itemPieBanana));
		this.recipeList.put(new ItemStack(ModItems.itemLemon), new ItemStack(ModItems.itemPieLemon));
		this.recipeList.put(new ItemStack(ModItems.itemCoconut), new ItemStack(ModItems.itemPieCoconut));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemEgg), new ItemStack(ModItems.itemFlourWheat)};
		
		setItemStackHandlers();
		setInput();
	}
	
}
