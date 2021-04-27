package tostimannetje.landleven.store;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;

public class StoreItem {

	public static final int WIDTH = 38;
	public static final int HEIGHT = 65;
	
	public String title;
	public Item item;
	public Block block;
	public ResourceLocation icon;
	public int price;
	
	public StoreItem(String title, Item item, String iconPath, int price) {
		this.title = title;
		this.item = item;
		this.icon = new ResourceLocation(Reference.MODID, iconPath);
		this.price = price;
	}
	
	public StoreItem(String title, Block block, String iconPath, int price) {
		this.title = title;
		this.block = block;
		this.icon = new ResourceLocation(Reference.MODID, iconPath);
		this.price = price;
	}
	
}
