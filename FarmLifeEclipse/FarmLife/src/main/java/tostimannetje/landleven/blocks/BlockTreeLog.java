package tostimannetje.landleven.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.init.ModTrees;

public class BlockTreeLog extends BlockLog {

	private Item fruit;
	
	public BlockTreeLog(String name, Item fruit) {
		super();
		this.fruit = fruit;
		setUnlocalizedName(name);
		setRegistryName(name);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LOG_AXIS, EnumAxis.Y));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();

		switch (meta & 12) {
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
			break;

		case 4:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
			break;

		case 8:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
			break;

		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
		}

		return state;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = 0;

		switch (state.getValue(LOG_AXIS)) {
		case X:
			meta |= 4;
			break;

		case Y:
			meta |= 8;
			break;

		case Z:
			meta |= 12;
		}

		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LOG_AXIS });
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(this), 1);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if(player.getHeldItemMainhand().getItem() == ModItems.itemFruitBasket){
			if (!world.isRemote) {
				BlockPos root = findRoot(world, pos, state);
				if(isStillSoil(world, root) && getTrunkHeight(world, root) == 6) {
					EntityItem item = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(fruit));
					world.spawnEntity(item);
					
					destroyTree(world, root);
					plantSapling(world, root, ModTrees.getSapling(this));
				}
			}
		}
		
		return true;
	}
	
	private BlockPos findRoot(World world, BlockPos pos, IBlockState state) {
		while(world.getBlockState(pos) == state) {
			pos = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
		}
		return new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
	}
	
	private boolean isStillSoil(World world, BlockPos root) {
		root = new BlockPos(root.getX(), root.getY() - 1, root.getZ());
		IBlockState state = world.getBlockState(root);
		return state == Blocks.DIRT.getDefaultState() || state == Blocks.GRASS.getDefaultState();
	}
	
	private int getTrunkHeight(World world, BlockPos root) {
		int height = 0;
		while(world.getBlockState(new BlockPos(root.getX(), root.getY() + height, root.getZ())) == this.getDefaultState()) {
			height++;
		}
		return height;
	}
	
	private void destroyTree(World world, BlockPos root) {
		for(int i = 0; i < 8; i++) {
			world.setBlockToAir(new BlockPos(root.getX(), root.getY()+i, root.getZ()));
			
			if(i >= 3) {
				for(int j = -2; j < 3; j++) {
					for(int k = -2; k < 3; k++) {
						world.setBlockToAir(new BlockPos(root.getX()+j, root.getY()+i, root.getZ()+k));
					}
				}
			}
		}
	}
	
	private void plantSapling(World world, BlockPos root, Block sapling) {
		world.setBlockState(root, sapling.getDefaultState());
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}
}
