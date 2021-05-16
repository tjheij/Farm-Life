package tostimannetje.landleven.tileentity;

import tostimannetje.landleven.RecipeHandler;

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
		this.inputItem = RecipeHandler.getInputs(this).get(0);
		this.outputItem = RecipeHandler.getOutputs(this).get(0);
		
		this.setItemStackHandlers();
	}
	
}
