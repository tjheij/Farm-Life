package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.RecipeHandler;

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
		this.inputItem = RecipeHandler.getInputs(this).get(0);
		this.outputItem = RecipeHandler.getOutputs(this).get(0);
		
		this.setItemStackHandlers();
	}
	
}
