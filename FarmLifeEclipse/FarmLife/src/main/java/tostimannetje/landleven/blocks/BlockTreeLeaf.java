package tostimannetje.landleven.blocks;

import java.util.List;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTreeLeaf extends BlockLeaves{
	
	public BlockTreeLeaf(String name) {
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.GLASS);
		setDefaultState(this.blockState.getBaseState().withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0)).withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;

		if (!((Boolean) state.getValue(DECAYABLE)).booleanValue()) {
			meta |= 4;
		}

		if (!((Boolean) state.getValue(CHECK_DECAY)).booleanValue()) {
			meta |= 8;
		}

		return meta;
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
	
	@Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune){}
	
	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return NonNullList.withSize(1, new ItemStack(this));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { DECAYABLE, CHECK_DECAY });
	}
}
