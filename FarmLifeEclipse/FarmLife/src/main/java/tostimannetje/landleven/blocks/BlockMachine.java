package tostimannetje.landleven.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.tileentity.TileEntityMachine;

public class BlockMachine extends BlockTileEntity<TileEntityMachine> {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public int ID = 0;
	
	public BlockMachine(String name, int ID) {
		super(name, Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setCreativeTab(ModBlocks.farm_life_machines);
		setHardness(3f);
		setResistance(5f);
		this.ID = ID;
	}
	
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        this.setDefaultFacing(worldIn, pos, state);
    }

    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityMachine tile = getTileEntity(world, pos);
		tile.setInput();
		tile.setOutput();
		
		if (!world.isRemote) {
			IItemHandler input = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
			IItemHandler output = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
			
			//Open gui
			if(player.getHeldItemMainhand().getItem() == ModItems.itemInspector){
				player.openGui(Main.instance, ID, world, pos.getX(), pos.getY(), pos.getZ());
			//Add input
			}else if(player.getHeldItemMainhand().getItem() == tile.inputItem){
				for(int i = 0; i < 3; i++) {
					if(input.getStackInSlot(i).isEmpty()) {
						player.setHeldItem(hand, input.insertItem(i, player.getHeldItemMainhand(), false));
						return true;
					}
				}
			
			//Get output	
			}else{
				boolean given = false;
				
				for(int i = 2; i >= 0; --i) {
					if(!output.getStackInSlot(i).isEmpty()) {
						//If the player has a stack already, try to add the item to that stack
						if(player.inventory.hasItemStack(output.getStackInSlot(i))){
							for(int j = 0; j < 36; j++){
								if(player.inventory.getStackInSlot(j).getItem() == output.getStackInSlot(i).getItem()){
									if(player.inventory.getStackInSlot(j).getCount() < 64) {
										player.inventory.getStackInSlot(j).grow(1);
										given = true;
										break;
									}
								}
							}
						}	
						
						//If the player only had full stacks or didn't have the item, a new stack should be made
						if(!given) {
							for(int k = 0; k < 36; k++) {
								if(player.inventory.getStackInSlot(k).isEmpty()) {
									player.inventory.add(k, new ItemStack(output.getStackInSlot(i).getItem()));
									given = true;
									break;
								}
							}
						}
						
						if(given) {
							//Extract
							output.extractItem(i, 1, false);
							break;
						}
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public Class<TileEntityMachine> getTileEntityClass() {
		return TileEntityMachine.class;
	}
	
	@Nullable
	@Override
	public TileEntityMachine createTileEntity(World world, IBlockState state) {
		return new TileEntityMachine(this.getUnlocalizedName());
	}
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
        super.breakBlock(worldIn, pos, state);
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public int getMetaFromState(IBlockState state){
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    public IBlockState withRotation(IBlockState state, Rotation rot){
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn){
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState(){
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
}
