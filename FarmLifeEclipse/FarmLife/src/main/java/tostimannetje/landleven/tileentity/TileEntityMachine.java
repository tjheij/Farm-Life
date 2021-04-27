package tostimannetje.landleven.tileentity;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import tostimannetje.landleven.CustomItemStackHandler;

public class TileEntityMachine extends TileEntityProducer{
	
	protected Map<ItemStack, ItemStack> recipeList = Maps.<ItemStack, ItemStack>newLinkedHashMap();
	public ItemStack[] inputs = new ItemStack[0];
	public ItemStack[] extraInputs = new ItemStack[0];
	
	public int productSelected = 0;
	
	protected CustomItemStackHandler extraInputSlot;
	
	public TileEntityMachine() {
		super();
	}
	
	public TileEntityMachine(String name){
		super(name);
	}
	
	@Override
	public void setItemStackHandlers() {
		inputSlot = new CustomItemStackHandler(3) {
			@Override
			@Nonnull
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				if(stack.getItem() != inputItem) {
					return stack;
				}else {
					return super.insertItem(slot, stack, simulate);
				}
			}
		};
		outputSlot = new CustomItemStackHandler(3) {};
		extraInputSlot = new CustomItemStackHandler(extraInputs.length) {
			@Override
			@Nonnull
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				if(stack.getItem() != extraInputs[slot].getItem()) {
					return stack;
				}else {
					return super.insertItem(slot, stack, simulate);
				}
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
			if(facing == EnumFacing.DOWN){
				return (T)outputSlot;
			}else if(facing == EnumFacing.UP){
				return (T)inputSlot;
			}else {
				return (T)extraInputSlot;
			}
		}
		return super.getCapability(capability, facing);
	}
	
	
	//Looks at productSelected and determines what item can be put in
	public void setInput(){
		if(productSelected < inputs.length){
			if(inputs[productSelected].getItem() != null){
				inputItem = inputs[productSelected].getItem();
			}
		}
	}
	
	public void setOutput(){
		// Looks at first slot and determines what this will become
		if(!inputSlot.getStackInSlot(0).isEmpty()){
			for(int i = 0; i < inputs.length; i++) {
				if(inputSlot.getStackInSlot(0).getItem() == inputs[i].getItem()) {
					outputItem = recipeList.get(inputs[i]).getItem();
				}
			}
		}
	}
	
	@Override
	public void update(){
		setOutput();
		
		if (!this.world.isRemote) {
			if(canProcess()) {
				processTimer++;
			}else {
				processTimer = 0;
			}
			
			moveItemStacks();
			
			if(processTimer >= processTimeNeeded){
				processTimer = 0;
				inputSlot.extractItem(0, 1, false);
				inputSlot.setStackInSlot(0, inputSlot.getStackInSlot(1));
				inputSlot.setStackInSlot(1, inputSlot.getStackInSlot(2));
				inputSlot.setStackInSlot(2, ItemStack.EMPTY);
				
				for(int j = 0; j < extraInputs.length; j++) {
					extraInputSlot.extractItem(j, 1, false);
				}
				
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
	
	public boolean canProcess() {
		boolean check = true;
		
		//If there's no input, all outputs are full or the input isn't the selected recipe
		if(inputSlot.getStackInSlot(0).getCount() <= 0 || !outputSlot.getStackInSlot(2).isEmpty() || inputSlot.getStackInSlot(0).getItem() != getInput()){
			check = false;
		}
		
		//If one of the extra inputs is empty
		for(int j = 0; j < extraInputs.length; j++) {
			if(extraInputSlot.getStackInSlot(j).getCount() <= 0) {
				check = false;
			}
		}
		
		return check;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("productselected", this.getField(2));
		compound.setTag("extra", extraInputSlot.serializeNBT());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.setField(2, compound.getInteger("productselected"));
		if(extraInputs.length > 0) {
			extraInputSlot.deserializeNBT(compound.getCompoundTag("extra"));
		}
	}
	
	public ItemStack[] getInputList(){
		return inputs;
	}
	
	public ItemStack[] getExtraInputList(){
		return extraInputs;
	}
	
	public int getInputLength(){
		return inputs.length;
	}
	
	public int getExtraInputLength(){
		return extraInputs.length;
	}
	
	@Override
	public int getField(int id){
        return  id == 2 ? this.productSelected : super.getField(id);
    }

	@Override
    public void setField(int id, int value){
        super.setField(id, value);
		switch (id){
            case 2:
            	this.productSelected = value;
            	break;
        }
    }

    @Override
    public int getFieldCount(){
        return 3;
    }
	
	public void receiveButtonEvent(byte buttonId){
		this.setField(2, (int)buttonId);
		setInput();
		this.markDirty();
		IBlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 1);
	}
}
