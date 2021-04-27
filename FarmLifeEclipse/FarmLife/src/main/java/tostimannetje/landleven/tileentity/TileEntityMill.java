package tostimannetje.landleven.tileentity;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import tostimannetje.landleven.blocks.machines.BlockMill;
import tostimannetje.landleven.blocks.machines.BlockMill.States;
import tostimannetje.landleven.init.ModItems;

public class TileEntityMill extends TileEntityMachine{
	
	private TileEntityMill master;
	private boolean hasMaster, isMaster;
	private States state = States.SINGLE;
	private int masterX, masterY, masterZ;

	public TileEntityMill() {
		super();
		init();
	}
	
	public TileEntityMill(String name) {
		super(name);
		init();
	}

	public void init() {
		this.recipeList.put(new ItemStack(ModItems.itemWheat), new ItemStack(ModItems.itemFlourWheat));
		this.recipeList.put(new ItemStack(ModItems.itemOats), new ItemStack(ModItems.itemFlourOats));
		this.recipeList.put(new ItemStack(ModItems.itemCorn), new ItemStack(ModItems.itemFlourCorn));
		this.recipeList.put(new ItemStack(ModItems.itemRye), new ItemStack(ModItems.itemFlourRye));
		
		Set<ItemStack> keys = recipeList.keySet();
		
		inputs = keys.toArray(new ItemStack[keys.size()]);
		extraInputs = new ItemStack[] {};
		
		setItemStackHandlers();
		setInput();
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			if(hasMaster()) {
				if(this.master == null) {
	    			this.master = (TileEntityMill)world.getTileEntity(getMasterCoords());
	    		}
				
				if(facing == EnumFacing.DOWN){
					return (T)this.master.outputSlot;
				}else {
					return (T)this.master.inputSlot;
				}
			}
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public void update() {
	    if (!world.isRemote) {
	    	if(hasMaster()) {
	    		if(this.master == null) {
	    			this.master = (TileEntityMill)world.getTileEntity(getMasterCoords());
	    		}
		        if (isMaster()) {
		        	super.update();
		        }
	    	}else {
	    		// Constantly check if structure is formed until it is.
	    		if(checkMultiBlock()) {
	    			setupStructure();
	    		}
	    	}
	    }
	}
	
	/** Returns true if there is another TileEntityMill above this one, that has no master */
	public boolean checkMultiBlock() {
	    TileEntity tileAbove = world.getTileEntity(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()));
	    if(tileAbove != null && (tileAbove instanceof TileEntityMill)) {
	    	return !((TileEntityMill) tileAbove).hasMaster();
	    }
	    
	    return false;
	}
	
	/** Specifies this tile entity as the master for both entities and updates rendering*/
	public void setupStructure() {
		TileEntity tileAbove = world.getTileEntity(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()));
		if((tileAbove != null && (tileAbove instanceof TileEntityMill))) {
			TileEntityMill tile = (TileEntityMill) tileAbove;
    		tile.setMasterCoords(pos.getX(), pos.getY(), pos.getZ());
    		tile.setMaster(this);
    		tile.setState(States.INVISIBLE);
    		tile.sendUpdates();
			
			this.setMasterCoords(pos.getX(), pos.getY(), pos.getZ());
    		this.setMaster(this);
    		this.setState(States.RENDERER);
    		this.sendUpdates();
    	}
	}
	
	/** Resets a tile entity to it's single form without a master*/
	public void reset() {
		masterX = 0;
        masterY = 0;
        masterZ = 0;
        hasMaster = false;
        isMaster = false;
        state = States.SINGLE;
	}

	/** Checks if the master still exists */
	public boolean checkForMaster() {
        TileEntity tile = world.getTileEntity(new BlockPos(masterX, masterY, masterZ));
        return (tile != null && (tile instanceof TileEntityMill));
    }
	
	public States getState() {
		return state;
	}
	
	public void setState(States state) {
		this.state = state;
	}

    public TileEntityMill getMaster() {
    	return master;
    }
    
    public boolean hasMaster() {
    	return hasMaster;
    }
    
    public boolean isMaster() {
        return isMaster;
    }
    
    private void setMaster(TileEntityMill master){
        this.master = master;
        this.hasMaster = true;
        if(master == this) {
        	isMaster = true;
        }else {
        	isMaster = false;
        }
    }

    private void setMasterCoords(int masterX, int masterY, int masterZ) {
    	this.masterX = masterX;
    	this.masterY = masterY;
    	this.masterZ = masterZ;
    }
    
    public BlockPos getMasterCoords() {
    	return new BlockPos(this.masterX, this.masterY, this.masterZ);
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
    	super.writeToNBT(data);
        data.setInteger("masterX", masterX);
        data.setInteger("masterY", masterY);
        data.setInteger("masterZ", masterZ);
        data.setBoolean("hasMaster", hasMaster);
        data.setBoolean("isMaster", isMaster);
		data.setInteger("state", States.getInt(state));
		
        if (hasMaster() && isMaster()) {
            // Any other values should ONLY BE SAVED TO THE MASTER
        	data.setInteger("productselected", this.getField(2));
        }
    	
        return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
    	super.readFromNBT(data);
        masterX = data.getInteger("masterX");
        masterY = data.getInteger("masterY");
        masterZ = data.getInteger("masterZ");
        hasMaster = data.getBoolean("hasMaster");
        isMaster = data.getBoolean("isMaster");
        state = States.getState(data.getInteger("state"));
        if (hasMaster() && isMaster()) {
            // Any other values should ONLY BE READ BY THE MASTER
        	this.setField(2, data.getInteger("productselected"));
        }
    	
        
    }
    
    @Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
    
	public void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos).withProperty(BlockMill.STATE, state), 3);
		world.scheduleBlockUpdate(pos,this.getBlockType(),0,0);
		markDirty();
	}
    
    @Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return this.writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		handleUpdateTag(pkt.getNbtCompound());
		final IBlockState state = getWorld().getBlockState(getPos());
		getWorld().notifyBlockUpdate(getPos(), state, state, 3);
	}
}
