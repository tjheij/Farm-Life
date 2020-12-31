package tostimannetje.landleven.items;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import tostimannetje.landleven.blocks.BlockCrop;
import tostimannetje.landleven.init.ModItems;

public class ItemSeed extends ItemSeeds implements IHasPrice{
	
	private int price;
	
	public ItemSeed(String name, BlockCrop crop, int price) {
		super(crop, Blocks.FARMLAND);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.farm_life_crops);
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
}
