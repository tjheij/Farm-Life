package tostimannetje.landleven;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import tostimannetje.landleven.tileentity.TileEntityMachine;

public class CustomItemStackHandler extends ItemStackHandler{
	public CustomItemStackHandler(int slots){
		super(slots);
	}
	
	@Override
	public int getSlotLimit(int slot){
		return 1;
	}
	
	@Override
	@Nonnull
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
    {
        if (stack.isEmpty())
            return ItemStack.EMPTY;

        validateSlotIndex(slot);

        ItemStack existing = this.stacks.get(slot);

        int limit = getStackLimit(slot, stack);

        if (!existing.isEmpty())
        {
        	//Not the same item
            if (!ItemHandlerHelper.canItemStacksStack(stack, existing))
                return stack;

            //Space left
            limit -= existing.getCount();
        }

        //No space
        if (limit <= 0)
            return stack;

        //At least 1 space
        if (!simulate)
        {
        	//Full space
            if (existing.isEmpty())
            {
                this.stacks.set(slot, ItemHandlerHelper.copyStackWithSize(stack, 1));
            }
            else
            {
                existing.grow(1);
            }
            onContentsChanged(slot);
        }
        return stack.getCount()-1 > 0 ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- 1) : ItemStack.EMPTY;
    }
}
