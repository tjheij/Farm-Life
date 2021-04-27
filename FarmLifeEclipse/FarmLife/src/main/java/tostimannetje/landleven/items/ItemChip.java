package tostimannetje.landleven.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class ItemChip extends ItemBase implements IHasPrice{
	
	private int price;
	
	public ItemChip(String name, int price) {
		super(name);
		this.price = price;
	}
	
	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public Item getItem() {
		return this;
	}

	@Override
	public Block getBlock() {
		return null;
	}
}
