package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.RecipeHandler;

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
		this.inputItem = RecipeHandler.getInputs(this).get(0);
		this.outputItem = RecipeHandler.getOutputs(this).get(0);
		
		this.setItemStackHandlers();
	}
	
}
