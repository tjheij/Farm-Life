package tostimannetje.landleven.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public abstract class TileEntityInventory extends TileEntity{

    public final ItemStackHandler slots;

    public TileEntityInventory(int slots, String name){

        this.slots = new ItemStackHandler(slots){
	        @SuppressWarnings("unused")
			public boolean canInsert(ItemStack stack, int slot){
	            return TileEntityInventory.this.isItemValidForSlot(slot, stack);
	        }
	
	        
	        @SuppressWarnings("unused")
			public boolean canExtract(ItemStack stack, int slot){
	            return TileEntityInventory.this.canExtractItem(slot, stack);
	        }
	
	        
	        public int getSlotLimit(int slot){
	            return TileEntityInventory.this.getMaxStackSizePerSlot(slot);
	        }
        };
    }

    public IItemHandler getItemHandler(EnumFacing facing){
        return this.slots;
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack){
        return true;
    }

    public boolean canExtractItem(int slot, ItemStack stack){
        return true;
    }

    public int getMaxStackSizePerSlot(int slot){
        return 64;
    }

    public boolean shouldSyncSlots(){
        return false;
    }

    @Override
    public void markDirty(){
        super.markDirty();
    }
}