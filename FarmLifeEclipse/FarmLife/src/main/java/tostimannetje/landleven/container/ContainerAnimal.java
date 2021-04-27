package tostimannetje.landleven.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tostimannetje.landleven.tileentity.TileEntityAnimal;

public class ContainerAnimal extends Container{
	private TileEntityAnimal te;
	private int processTimer;
	private int processTimeNeeded;
	
	public ContainerAnimal(InventoryPlayer playerInv, final TileEntityAnimal te) {
		this.te = te;
		IItemHandler input = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
		IItemHandler output = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
		addSlotToContainer(new SlotInput(input, 0, 16, 58, te) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		addSlotToContainer(new SlotInput(input, 1, 34, 58, te) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		addSlotToContainer(new SlotInput(input, 2, 52, 58, te) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		addSlotToContainer(new SlotOutput(output, 0, 144, 58) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		
		addSlotToContainer(new SlotOutput(output, 1, 126, 58) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		
		addSlotToContainer(new SlotOutput(output, 2, 108, 58) {
			@Override
			public void onSlotChanged() {
				te.markDirty();
			}
		});
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18,  90 + i * 18));
			}
		}
	
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 148));
		}
	}
	
	public void addListener(IContainerListener listener){
        super.addListener(listener);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges(){
        super.detectAndSendChanges();
        
        for (int i = 0; i < this.listeners.size(); ++i){
            IContainerListener icontainerlistener = this.listeners.get(i);

            if (this.processTimer != this.te.getField(0)){
                icontainerlistener.sendWindowProperty(this, 0, this.te.getField(0));
            }

            if (this.processTimeNeeded != this.te.getField(1)){
                icontainerlistener.sendWindowProperty(this, 1, this.te.getField(1));
            }

        }

        this.processTimer = this.te.getField(0);
        this.processTimeNeeded = this.te.getField(1);
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data)
    {
        this.te.setField(id, data);
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	public TileEntityAnimal getAnimal(){
		return te;
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
	
}
