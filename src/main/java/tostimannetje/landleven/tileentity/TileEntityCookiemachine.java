package tostimannetje.landleven.tileentity;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tostimannetje.landleven.init.ModItems;

public class TileEntityCookiemachine extends TileEntityMachine{
	
	public TileEntityCookiemachine() {
		super();
		init();
	}
	
	public TileEntityCookiemachine(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemGrape), new ItemStack(ModItems.itemCookieGrape));
		this.recipeList.put(new ItemStack(ModItems.itemWhiteChocolate), new ItemStack(ModItems.itemCookieWhiteChocolate));
		this.recipeList.put(new ItemStack(ModItems.itemOats), new ItemStack(ModItems.itemCookieOats));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {new ItemStack(ModItems.itemFlourWheat), new ItemStack(ModItems.itemMilk)};
		
		setItemStackHandlers();
		setInput();
	}
}
