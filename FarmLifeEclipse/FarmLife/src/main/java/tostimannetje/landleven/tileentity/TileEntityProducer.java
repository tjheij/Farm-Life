package tostimannetje.landleven.tileentity;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import tostimannetje.landleven.CustomItemStackHandler;

public class TileEntityProducer extends TileEntity implements ITickable{

	public final String name;
	
	public Item inputItem;
	public Item outputItem;
	protected int processTimer = 0;
	protected int processTimeNeeded = 200;
	
	protected CustomItemStackHandler inputSlot;
	protected CustomItemStackHandler outputSlot;
	
	public TileEntityProducer() {
		this.name = "";
	}
	
	public TileEntityProducer(String name){
		this.name = name;
	}
	
	public void setItemStackHandlers() {}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			if(facing == EnumFacing.DOWN){
				return (T)outputSlot;
			}else{
				return (T)inputSlot;
			}
		}
		return super.getCapability(capability, facing);
	}
	
	public void update(){
		moveItemStacks();
	}
	
	public void moveItemStacks() {
		moveItemStack(1);
		moveItemStack(2);
		moveItemStack(1);
	}
	
	public void moveItemStack(int slot) {
		if(slot > 0 && slot < inputSlot.getSlots()) {
			if(inputSlot.getStackInSlot(slot-1).isEmpty() && !inputSlot.getStackInSlot(slot).isEmpty()) {
				ItemStack stack = inputSlot.getStackInSlot(slot);
				inputSlot.setStackInSlot(slot, ItemStack.EMPTY);
				inputSlot.setStackInSlot(slot-1, stack);
			}
		}
	}
	
	public Item getInput(){
		return inputItem;
	}
	
	public boolean hasInputItem() {
		return inputSlot.getStackInSlot(0).getCount() > 0;
	}
	
	public int getField(int id){
		switch (id){
        case 0:
        	return this.processTimer;
        case 1:
        	return this.processTimeNeeded;
        default:
            return 0;
		}
    }
	
    public void setField(int id, int value) {
    	switch (id){
        case 0:
        	this.processTimer = value;
        	break;
        case 1:
        	this.processTimeNeeded = value;
        	break;
    	}
    }

    public int getFieldCount() {
        return 2;
    }
    
    @Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("process", this.getField(0));
		compound.setInteger("timeneeded", this.getField(1));
		compound.setTag("input", inputSlot.serializeNBT());
		compound.setTag("output", outputSlot.serializeNBT());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.setField(0, compound.getInteger("process"));
		this.setField(1, compound.getInteger("timeneeded"));
		inputSlot.deserializeNBT(compound.getCompoundTag("input"));
		outputSlot.deserializeNBT(compound.getCompoundTag("output"));
	}
	
	public String getNameForTranslation() {
        return this.name + ".name";
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation(this.getNameForTranslation());
    }
    
    @Override
    public NBTTagCompound getUpdateTag(){
    	NBTTagCompound tag = super.getUpdateTag();
    	writeToNBT(tag);
        return tag;
    }
	
    @Override
	public SPacketUpdateTileEntity getUpdatePacket() {
    	NBTTagCompound tag = new NBTTagCompound();
    	writeToNBT(tag);
		return new SPacketUpdateTileEntity(this.getPos(), 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.getNbtCompound();
		readFromNBT(tag);
	}
}