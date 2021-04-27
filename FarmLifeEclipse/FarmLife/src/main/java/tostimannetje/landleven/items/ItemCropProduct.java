package tostimannetje.landleven.items;

import tostimannetje.landleven.init.ModItems;

public class ItemCropProduct extends ItemBaseProduct{

	public ItemCropProduct(String name, int sellValue, boolean edible) {
		super(name, sellValue, edible);
		setCreativeTab(ModItems.farm_life_crops);
	}
}
