package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.init.ModItems;

public class TileEntityHorseWhite extends TileEntityAnimal{

	public TileEntityHorseWhite() {
		super();
		init();
	}
	
	public TileEntityHorseWhite(String name) {
		super(name);
		init();
	}
	
	public void init() {
		this.inputItem = ModItems.itemCarrot;
		this.outputItem = ModItems.itemMilkHorse;
		
		this.setItemStackHandlers();
	}
	
}
