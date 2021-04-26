package tostimannetje.landleven.container;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tostimannetje.landleven.tileentity.TileEntityMachine;

public class SlotExtraInput extends SlotItemHandler{
	
	private TileEntityMachine te;
	private int index;
	
	public SlotExtraInput(IItemHandler itemHandler, int index, int xPosition, int yPosition, TileEntityMachine te) {
		super(itemHandler, index, xPosition, yPosition);
		this.index = index;
		this.te = te;
	}
	
	@Override
	public boolean isItemValid(@Nonnull ItemStack stack) {
		return stack.getItem() == te.getExtraInputList()[this.index].getItem() && super.isItemValid(stack);
	}
}
