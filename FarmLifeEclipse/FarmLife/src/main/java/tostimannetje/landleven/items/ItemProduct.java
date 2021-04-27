package tostimannetje.landleven.items;

import tostimannetje.landleven.init.ModItems;

public class ItemProduct extends ItemBaseProduct{

	public ItemProduct(String name, int sellValue, boolean edible) {
		super(name, sellValue, edible);
		setCreativeTab(ModItems.farm_life_products);
	}
}
