package tostimannetje.landleven.store;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.items.IHasPrice;
import tostimannetje.landleven.items.ItemSeed;

public class StoreSectionBlocks<T extends Block & IHasPrice> extends StoreSection{
	
	public StoreSectionBlocks(String name, List<T> blocks, String icon) {
		super(name, icon);
		for(int i = 0; i < blocks.size(); i++) {
			addToStore(blocks.get(i));
		}
	}
	
	public void addToStore(T block) {
		String name = block.getUnlocalizedName();
		StoreItem storeItem = new StoreItem(name, block,"textures/items/"+name.substring(5)+".png", block.getPrice());
		sectionItems.add(storeItem);
	}
}
