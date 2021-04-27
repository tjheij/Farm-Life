package tostimannetje.landleven.store;

import java.util.List;
import tostimannetje.landleven.items.IHasPrice;

public class StoreSectionItems extends StoreSection{
	
	public StoreSectionItems(String name, List<IHasPrice> items, String icon) {
		super(name, icon);
		for(int i = 0; i < items.size(); i++) {
			addToStore(items.get(i));
		}
	}
	
	public void addToStore(IHasPrice item) {
		String name = item.getUnlocalizedName();
		StoreItem storeItem = new StoreItem(name, item.getItem(),"textures/items/"+name.substring(5)+".png", item.getPrice());
		sectionItems.add(storeItem);
	}
}
