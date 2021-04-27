package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.init.ModItems;

public class TileEntityPig extends TileEntityAnimal{

	public TileEntityPig() {
		super();
		init();
	}
	
	public TileEntityPig(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemPotato;
		this.outputItem = ModItems.itemPork;
		
		this.setItemStackHandlers();
	}
	
}
