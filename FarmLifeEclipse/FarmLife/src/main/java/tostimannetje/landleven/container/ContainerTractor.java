package tostimannetje.landleven.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tostimannetje.landleven.entity.EntityTractor;

public class ContainerTractor extends Container{
	
	private EntityTractor tractor;
	private IItemHandler storagePlanting;
	private IItemHandler storageHarvesting;
	private IItemHandler storageFertilizing;
	
	public ContainerTractor(InventoryPlayer playerInventory, EntityTractor tractor) {
		this.tractor = tractor;
        
		storagePlanting = tractor.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
		storageHarvesting = tractor.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		storageFertilizing = tractor.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		if(storagePlanting != null) {
			for (int i = 0; i < 3; ++i){
	            for (int j = 0; j < 9; ++j){
	                this.addSlotToContainer(new SlotSeedBag(storagePlanting, j + i * 9, 8 + j * 18, 51 + i * 18));
	            }
	        }
		}else {
			System.out.println("Planting handler is null");
		}
		
		if(storageHarvesting != null) {
			for (int i = 0; i < 3; ++i){
	            for (int j = 0; j < 9; ++j){
	                this.addSlotToContainer(new SlotItemHandler(storageHarvesting, j + i * 9, 8 + j * 18, 51 + i * 18));
	            }
	        }
		}else {
			System.out.println("Harvesting handler is null");
		}

		if(storageFertilizing != null) {
			for (int i = 0; i < 3; ++i){
	            for (int j = 0; j < 3; ++j){
	                this.addSlotToContainer(new SlotFertilizer(storageFertilizing, j + i * 3, 62 + j * 18, 51 + i * 18));
	            }
			}
		}else {
			System.out.println("Fertilizing handler is null");
		}
		
		for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 9; ++j){
                this.addSlotToContainer(new Slot(playerInventory, 9 + j + i * 9, 8 + j * 18, 118 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 176));
            
        }
		
        refresh();
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	public EntityTractor getTractor() {
		return this.tractor;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = inventorySlots.get(index);
	
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
	
			int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
	
			if (index < containerSlots) {
				if (!this.mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, containerSlots, false)) {
				return ItemStack.EMPTY;
			}
	
			if (itemstack1.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
	
			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
	
			slot.onTake(player, itemstack1);
		}
	
		return itemstack;
	}
	
	public void refresh() {
		
		for(int i = 0; i < this.inventorySlots.size(); i++) {
			if(this.getSlot(i).yPos < 118 && this.getSlot(i).xPos > 0) {
				this.getSlot(i).xPos -= 1000;
			}
		}
		
		
		for(int i = 0; i < 3; i++) {
			if(i == this.tractor.getTractorMode().getValue()) {
				if(i == 0) {
					for(int j = 0; j < storagePlanting.getSlots(); j++) {
						if(this.getSlot(j) instanceof SlotSeedBag) {
							this.getSlot(j).xPos += 1000;
						}
					}
				}else if(i == 1) {
					for(int j = 0; j < storageHarvesting.getSlots(); j++) {
						if(this.getSlot(j+27) instanceof SlotItemHandler) {
							this.getSlot(j+27).xPos += 1000;
						}
					}
				}else if(i == 2) {
					for(int j = 0; j < storageFertilizing.getSlots(); j++) {
						if(this.getSlot(j+54) instanceof SlotFertilizer) {
							this.getSlot(j+54).xPos += 1000;
						}
					}
				}
			}
		}
	}
}
