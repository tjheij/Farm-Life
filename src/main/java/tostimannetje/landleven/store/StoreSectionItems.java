package tostimannetje.landleven.store;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.items.IHasPrice;
import tostimannetje.landleven.items.ItemSeed;

public class StoreSectionItems<T extends Item & IHasPrice> extends StoreSection{
	
	public StoreSectionItems(String name, List<T> items, String icon) {
		super(name, icon);
		for(int i = 0; i < items.size(); i++) {
			addToStore(items.get(i));
		}
	}
	
	public void addToStore(T item) {
		String name = item.getUnlocalizedName();
		StoreItem storeItem = new StoreItem(name, item,"textures/items/"+name.substring(5)+".png", item.getPrice());
		sectionItems.add(storeItem);
	}
}
