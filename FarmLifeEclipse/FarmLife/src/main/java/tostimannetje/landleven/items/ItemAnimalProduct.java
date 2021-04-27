package tostimannetje.landleven.items;

import tostimannetje.landleven.init.ModItems;

public class ItemAnimalProduct extends ItemBaseProduct{
	
	public ItemAnimalProduct(String name, int sellValue, boolean edible) {
		super(name, sellValue, edible);
		setCreativeTab(ModItems.farm_life_trees);
	}
}
