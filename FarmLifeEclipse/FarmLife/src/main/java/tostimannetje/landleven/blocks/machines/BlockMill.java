package tostimannetje.landleven.blocks.machines;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.blocks.BlockBase;
import tostimannetje.landleven.blocks.BlockMachine;
import tostimannetje.landleven.blocks.BlockTileEntity;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntityMill;

public class BlockMill extends BlockTileEntity<TileEntityMachine>{
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyEnum<States> STATE = PropertyEnum.create("state", States.class);
	public int ID = 0;
	
	public enum States implements IStringSerializable{
		SINGLE("single"), 
		INVISIBLE("invisible"), 
		RENDERER("renderer");
	
		private final String name;
		
		private States(String name) {
			this.name = name;
		}
		
		public static States getState(int i) {
			switch(i) {
			case 0:
				return SINGLE;
			case 1:
				return INVISIBLE;
			case 2:
				return RENDERER;
			default:
				return SINGLE;
			}
		}
		
		public static int getInt(States state) {
			switch(state) {
			case SINGLE:
				return 0;
			case INVISIBLE:
				return 1;
			case RENDERER:
				return 2;
			default:
				return 0;
			}
		}
		
		@Override
		public String getName() {
			return this.name;
		}
	}
	
	public BlockMill(String name, int ID) {
		super(name, Material.ROCK);
		this.setCreativeTab(ModBlocks.farm_life_machines);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(STATE, States.SINGLE));
		this.ID = ID;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
    public boolean isOpaqueCube(IBlockState state) {
    	return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Nullable
	@Override
	public TileEntityMachine createTileEntity(World world, IBlockState state) {
		return new TileEntityMill(this.getUnlocalizedName());
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileEntityMill tile = (TileEntityMill) world.getTileEntity(pos);
		if(tile != null) {
			if(tile.hasMaster()) {
				TileEntityMill master = tile.getMaster();
				if(master != null) {
					master.setInput();
					master.setOutput();
					
					if (!world.isRemote) {
						IItemHandler input = master.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
						IItemHandler output = master.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
						
						//Open gui
						if(player.getHeldItemMainhand().getItem() == ModItems.itemInspector){
							BlockPos mastercoords = master.getMasterCoords();
							player.openGui(Main.instance, ID, world, mastercoords.getX(), mastercoords.getY(), mastercoords.getZ());
						}else if(player.getHeldItemMainhand().getItem() == master.inputItem){
							addInput(input, player, hand);
						}else{
							retrieveOutput(output, player);
						}
					}
				}
			}
		}
		
		return true;
	}

	/** Adds input from hand into the machine */
	private void addInput(IItemHandler input, EntityPlayer player, EnumHand hand) {
		for(int i = 0; i < 3; i++) {
			if(input.getStackInSlot(i).isEmpty()) {
				System.out.println("insert");
				player.setHeldItem(hand, input.insertItem(i, player.getHeldItemMainhand(), false));
				return;
			}
		}
	}
	
	/** Retrieves output from machine into player inventory */
	private void retrieveOutput(IItemHandler output, EntityPlayer player) {
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
					output.extractItem(i, 1, false);;
				}
			}
		}
	}
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if(te instanceof TileEntityMill) {
			TileEntityMill teinstance =(TileEntityMill) te;
			if(teinstance.getState() != null) {
				return state.withProperty(STATE, teinstance.getState());
			}
		}
		
		return state;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

	@Override
    public int getMetaFromState(IBlockState state){
		return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
	    return new BlockStateContainer(this, new IProperty[] {FACING, STATE});
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
	
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer){
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
	
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }
	
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		if(!worldIn.isRemote) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if(tile != null && tile instanceof TileEntityMill) {
				TileEntityMill thisTile = (TileEntityMill) tile;
				
				if(thisTile.hasMaster()) {
					if(thisTile.isMaster()) {
						//Get tile above, reset it
						TileEntity tileNeighbour = worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()));
						if(tileNeighbour != null && tileNeighbour instanceof TileEntityMill) {
							((TileEntityMill) tileNeighbour).reset();
							((TileEntityMill) tileNeighbour).sendUpdates();
						}
					}else {
						//Get tile below, reset it
						TileEntity tileNeighbour = worldIn.getTileEntity(new BlockPos(pos.getX(), pos.getY()-1, pos.getZ()));
						if(tileNeighbour != null && tileNeighbour instanceof TileEntityMill) {
							((TileEntityMill) tileNeighbour).reset();
							((TileEntityMill) tileNeighbour).sendUpdates();
						}
					}
				}
			}
		}
		
        super.breakBlock(worldIn, pos, state);
    }
	
	public IBlockState withRotation(IBlockState state, Rotation rot){
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    public IBlockState withMirror(IBlockState state, Mirror mirrorIn){
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

	@Override
	public Class<TileEntityMachine> getTileEntityClass() {
		return TileEntityMachine.class;
	}
}
