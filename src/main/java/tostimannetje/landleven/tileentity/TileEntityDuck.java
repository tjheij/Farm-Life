package tostimannetje.landleven.tileentity;

import java.util.Set;

import tostimannetje.landleven.init.ModItems;

public class TileEntityDuck extends TileEntityAnimal{

	public TileEntityDuck() {
		super();
		init();
	}
	
	public TileEntityDuck(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemWheat;
		this.outputItem = ModItems.itemEggDuck;
		
		this.setItemStackHandlers();
	}
	
}
