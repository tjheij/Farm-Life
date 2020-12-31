package tostimannetje.landleven.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;

public class ItemBase extends Item{

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.llproducts);
	}
	
}
