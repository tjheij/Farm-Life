package tostimannetje.landleven.tileentity;

import java.util.Set;

import tostimannetje.landleven.init.ModItems;

public class TileEntityOx extends TileEntityAnimal{

	public TileEntityOx() {
		super();
		init();
	}
	
	public TileEntityOx(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemGrass;
		this.outputItem = ModItems.itemBeef;
		
		this.setItemStackHandlers();
	}
	
}
