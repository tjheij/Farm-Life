package tostimannetje.landleven.store;

import java.util.ArrayList;
import java.util.List;

import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;

public class Store {

	private static List<StoreSection> storeSections = new ArrayList<StoreSection>();
	
	public Store() {}
	
	public static void storeInit() {
		StoreSection seedSection = new StoreSectionItems("Seeds", ModItems.SEEDS, "textures/items/itemseeds_clover.png");
		storeSections.add(seedSection);
		
		StoreSection treeSection = new StoreSectionBlocks("Trees", ModBlocks.SAPLINGS, "textures/blocks/trees/apple/sapling_apple.png");
		storeSections.add(treeSection);
		
		StoreSection animalSection = new StoreSectionBlocks("Animals", ModBlocks.ANIMALS, "textures/items/itemanimal_milk.png");
		storeSections.add(animalSection);
		
		StoreSection chipSection = new StoreSectionItems("Chips", ModItems.CHIPS, "textures/items/itemchip_cheesemachine.png");
		storeSections.add(chipSection);
	}
	
	public static List<StoreSection> getStoreSections() {
		return storeSections;
	}
	
	public static int getPageCount() {
		int total = 0;
		for(StoreSection section : storeSections) {
			total += section.getSectionPageCount();
		}
		return total;
	}
}
