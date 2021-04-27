package tostimannetje.landleven.blocks;

import javax.annotation.Nonnull;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.tileentity.TileEntityMarket;

public class BlockMarket extends BlockTileEntity<TileEntityMarket>{

	private int ID = 0;
	
	public BlockMarket(String name, Material material, int ID) {
		super(name, material);
		this.ID = ID;
		setHardness(3f);
		setResistance(5f);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
	    return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
	    return false;
	}

	@Nonnull
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
	    return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, @Nonnull IBlockAccess blockAccess, @Nonnull BlockPos pos, EnumFacing side) {
	    return true;
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			player.openGui(Main.instance, ID, world, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override
	public Class<TileEntityMarket> getTileEntityClass() {
		return TileEntityMarket.class;
	}

	@Override
	public TileEntityMarket createTileEntity(World world, IBlockState state) {
		return new TileEntityMarket();
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		TileEntityMarket tile = getTileEntity(world, pos);
		IItemHandler itemHandler = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		ItemStack stack = itemHandler.getStackInSlot(0);
		if (!stack.isEmpty()) {
			EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
			world.spawnEntity(item);
		}
		super.breakBlock(world, pos, state);
	}

}
