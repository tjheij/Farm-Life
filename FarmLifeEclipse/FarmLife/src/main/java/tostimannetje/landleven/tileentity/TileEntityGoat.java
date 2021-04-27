package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.init.ModItems;

public class TileEntityGoat extends TileEntityAnimal{

	public TileEntityGoat() {
		super();
		init();
	}
	
	public TileEntityGoat(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemGrass;
		this.outputItem = ModItems.itemMilkGoat;
		
		this.setItemStackHandlers();
	}
	
}
