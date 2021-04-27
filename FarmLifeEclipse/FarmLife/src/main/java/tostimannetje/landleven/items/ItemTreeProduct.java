package tostimannetje.landleven.items;

import tostimannetje.landleven.init.ModItems;

public class ItemTreeProduct extends ItemBaseProduct{
	
	public ItemTreeProduct(String name, int sellValue, boolean edible) {
		super(name, sellValue, edible);
		setCreativeTab(ModItems.farm_life_trees);
	}
}
