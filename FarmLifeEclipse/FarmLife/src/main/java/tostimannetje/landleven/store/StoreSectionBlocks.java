package tostimannetje.landleven.store;

import java.util.List;
import tostimannetje.landleven.items.IHasPrice;

public class StoreSectionBlocks extends StoreSection{
	
	public StoreSectionBlocks(String name, List<IHasPrice> blocks, String icon) {
		super(name, icon);
		for(int i = 0; i < blocks.size(); i++) {
			addToStore(blocks.get(i));
		}
	}
	
	public void addToStore(IHasPrice block) {
		String name = block.getUnlocalizedName();
		StoreItem storeItem = new StoreItem(name, block.getBlock(),"textures/items/"+name.substring(5)+".png", block.getPrice());
		sectionItems.add(storeItem);
	}
}
