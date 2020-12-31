package tostimannetje.landleven.tileentity;

import java.util.Set;

import tostimannetje.landleven.init.ModItems;

public class TileEntityCow extends TileEntityAnimal{

	public TileEntityCow() {
		super();
		init();
	}
	
	public TileEntityCow(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemClover;
		this.outputItem = ModItems.itemMilk;
		
		this.setItemStackHandlers();
	}
	
}
