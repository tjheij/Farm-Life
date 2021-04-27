package tostimannetje.landleven.blocks.machines;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import tostimannetje.landleven.blocks.BlockMachine;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntitySugarmachine;


public class BlockSugarmachine extends BlockMachine{

	public BlockSugarmachine(String name, int ID) {
		super(name, ID);
	}
	
	@Nullable
	@Override
	public TileEntityMachine createTileEntity(World world, IBlockState state) {
		return new TileEntitySugarmachine(this.getUnlocalizedName());
	}
	
}
