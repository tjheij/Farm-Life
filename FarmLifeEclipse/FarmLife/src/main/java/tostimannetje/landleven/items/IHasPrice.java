package tostimannetje.landleven.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface IHasPrice {
	
	public Item getItem();
	
	public Block getBlock();
	
	public String getUnlocalizedName();
	
	public int getPrice();
}
