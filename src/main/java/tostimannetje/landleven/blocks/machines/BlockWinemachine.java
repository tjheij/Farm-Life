package tostimannetje.landleven.blocks.machines;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import tostimannetje.landleven.blocks.BlockMachine;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntityWinemachine;

public class BlockWinemachine extends BlockMachine{

	public BlockWinemachine(String name, int ID) {
		super(name, ID);
	}
	
	@Nullable
	@Override
	public TileEntityMachine createTileEntity(World world, IBlockState state) {
		return new TileEntityWinemachine(this.getUnlocalizedName());
	}
}
