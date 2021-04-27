package tostimannetje.landleven.blocks.machines;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import tostimannetje.landleven.blocks.BlockMachine;
import tostimannetje.landleven.tileentity.TileEntityCoffeemachine;
import tostimannetje.landleven.tileentity.TileEntityMachine;


public class BlockCoffeemachine extends BlockMachine{

	public BlockCoffeemachine(String name, int ID) {
		super(name, ID);
	}
	
	@Nullable
	@Override
	public TileEntityMachine createTileEntity(World world, IBlockState state) {
		return new TileEntityCoffeemachine(this.getUnlocalizedName());
	}
	
}