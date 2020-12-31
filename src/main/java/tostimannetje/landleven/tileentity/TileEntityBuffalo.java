package tostimannetje.landleven.tileentity;

import java.util.Set;

import tostimannetje.landleven.init.ModItems;

public class TileEntityBuffalo extends TileEntityAnimal{

	public TileEntityBuffalo() {
		super();
		init();
	}
	
	public TileEntityBuffalo(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemCucumber;
		this.outputItem = ModItems.itemMilkBuffalo;
		
		this.setItemStackHandlers();
	}
	
}
