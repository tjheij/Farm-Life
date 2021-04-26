package tostimannetje.landleven.tileentity;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityPastamachine extends TileEntityMachine{
	
	public TileEntityPastamachine() {
		super();
		init();
	}
	
	public TileEntityPastamachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemBasilicum), new ItemStack(ModItems.itemPastaBasilicum));
		this.recipeList.put(new ItemStack(ModItems.itemCherryTomato), new ItemStack(ModItems.itemPastaCherryTomato));
		this.recipeList.put(new ItemStack(ModItems.itemCheese), new ItemStack(ModItems.itemPastaCheese));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemFlourWheat), new ItemStack(ModItems.itemEgg)};
		
		setItemStackHandlers();
		setInput();
	}
}
