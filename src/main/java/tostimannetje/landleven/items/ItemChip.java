package tostimannetje.landleven.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import tostimannetje.landleven.blocks.BlockCrop;
import tostimannetje.landleven.init.ModItems;

public class ItemChip extends ItemBase implements IHasPrice{
	
	private int price;
	
	public ItemChip(String name, int price) {
		super(name);
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}
