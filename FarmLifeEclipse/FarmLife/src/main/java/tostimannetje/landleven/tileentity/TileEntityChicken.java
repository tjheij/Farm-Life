package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.init.ModItems;

public class TileEntityChicken extends TileEntityAnimal{

	public TileEntityChicken() {
		super();
		init();
	}
	
	public TileEntityChicken(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemCorn;
		this.outputItem = ModItems.itemEgg;
		
		this.setItemStackHandlers();
	}
	
}
