package tostimannetje.landleven.blocks.animals;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import tostimannetje.landleven.blocks.BlockAnimal;
import tostimannetje.landleven.tileentity.TileEntityAnimal;
import tostimannetje.landleven.tileentity.TileEntityOx;

public class BlockAnimalOx extends BlockAnimal{

	public BlockAnimalOx(String name, int ID, int price) {
		super(name, ID, price);
	}

	@Nullable
	@Override
	public TileEntityAnimal createTileEntity(World world, IBlockState state) {
		return new TileEntityOx(this.getUnlocalizedName());
	}
}
