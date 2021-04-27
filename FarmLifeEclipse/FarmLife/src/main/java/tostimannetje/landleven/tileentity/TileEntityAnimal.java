package tostimannetje.landleven.tileentity;

import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tostimannetje.landleven.CustomItemStackHandler;
import tostimannetje.landleven.blocks.BlockAnimal;

public class TileEntityAnimal extends TileEntityProducer {
	
	private boolean isFilled;
	
	public TileEntityAnimal() {
		super();
	}
	
	public TileEntityAnimal(String name){
		super(name);
	}
	
	@Override
	public void setItemStackHandlers() {
		inputSlot = new CustomItemStackHandler(3);
		outputSlot = new CustomItemStackHandler(3);
	}
	
	public void update(){
		//There was no input and now there is, so the block needs to update it's model
		if(isFilled ^ hasInputItem()) {
			isFilled = !isFilled;
			sendUpdates();	
		}
		
		//If there is input and room, it should process
		if(hasInputItem() && outputSlot.getStackInSlot(2).isEmpty()){
			processTimer++;
		}else {
			processTimer = 0;
		}
		
		//The process is done
		if (!this.world.isRemote) {
			moveItemStacks();
			
			if(processTimer >= processTimeNeeded){
				processTimer = 0;
				inputSlot.extractItem(0, 1, false);
				inputSlot.setStackInSlot(0, inputSlot.getStackInSlot(1));
				inputSlot.setStackInSlot(1, inputSlot.getStackInSlot(2));
				inputSlot.setStackInSlot(2, ItemStack.EMPTY);
				
				for(int i = 0; i < 3; i++) {
					if(outputSlot.getStackInSlot(i).isEmpty()) {
						outputSlot.insertItem(i, new ItemStack(outputItem), false);
						break;
					}
				}
				
				this.markDirty();
			}
		}
	}
	
	public boolean getState() {
		return isFilled;
	}
	
	public void setState(boolean state) {
		this.isFilled = state;
	}
    
    @Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setBoolean("state", isFilled);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.isFilled = compound.getBoolean("state");
	}
    
    @Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
    
    private void sendUpdates() {
		world.markBlockRangeForRenderUpdate(pos, pos);
		world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos).withProperty(BlockAnimal.STATE, isFilled), 3);
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
