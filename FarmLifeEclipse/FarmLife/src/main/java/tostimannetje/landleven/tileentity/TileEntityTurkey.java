package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.init.ModItems;

public class TileEntityTurkey extends TileEntityAnimal{

	public TileEntityTurkey() {
		super();
		init();
	}
	
	public TileEntityTurkey(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemRice;
		this.outputItem = ModItems.itemTurkey;
		
		this.setItemStackHandlers();
	}
	
}
