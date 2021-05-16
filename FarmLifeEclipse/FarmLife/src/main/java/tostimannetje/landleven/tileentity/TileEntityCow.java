package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.RecipeHandler;

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
		this.inputItem = RecipeHandler.getInputs(this).get(0);
		this.outputItem = RecipeHandler.getOutputs(this).get(0);
		
		this.setItemStackHandlers();
	}
	
}
