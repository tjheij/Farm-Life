package tostimannetje.landleven.tileentity;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import tostimannetje.landleven.items.ItemAnimalProduct;
import tostimannetje.landleven.items.ItemCropProduct;
import tostimannetje.landleven.items.ItemProduct;
import tostimannetje.landleven.items.ItemTreeProduct;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;

public class TileEntityMarket extends TileEntity implements ITickable{

	private boolean autoSell;
	
	private ItemStackHandler inventory = new ItemStackHandler(1);
	private int collectedCoins;
	
	public void update(){
		if(autoSell) {
			ItemStack stack = inventory.getStackInSlot(0);
			if(stack != null) {
				Item item = stack.getItem();
				if(item instanceof ItemProduct) {
					collectedCoins += ((ItemProduct) item).getSellValue() * stack.getCount();
					inventory.extractItem(0, stack.getCount(), false);
				}else if(item instanceof ItemCropProduct) {
					collectedCoins += ((ItemCropProduct) item).getSellValue() * stack.getCount();
					inventory.extractItem(0, stack.getCount(), false);
				}else if(item instanceof ItemTreeProduct) {
					collectedCoins += ((ItemTreeProduct) item).getSellValue() * stack.getCount();
					inventory.extractItem(0, stack.getCount(), false);
				}else if(item instanceof ItemAnimalProduct) {
					collectedCoins += ((ItemAnimalProduct) item).getSellValue() * stack.getCount();
					inventory.extractItem(0, stack.getCount(), false);
				}
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setTag("inventory", inventory.serializeNBT());
		compound.setBoolean("autosell", autoSell);
		compound.setInteger("collected", collectedCoins);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		this.autoSell = compound.getBoolean("autosell");
		this.collectedCoins = compound.getInteger("collected");
		
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
	
	public void receiveButtonEvent(byte id){
		if((int)id == 0) {
			this.autoSell = !this.autoSell;
		}else if((int)id == 1) {
			this.inventory.extractItem(0, this.inventory.getStackInSlot(0).getCount(), false);
		}else if((int)id == 2) {
			this.collectedCoins = 0;
		}
		
		//this.markDirty();
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 1);
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
	
	public int getCollectedCoins() {
		return this.collectedCoins;
	}
	
	public boolean isOnAutoMode() {
		return this.autoSell;
	}
	
}
