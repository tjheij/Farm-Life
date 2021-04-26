package tostimannetje.landleven.container;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntityProducer;

public class SlotInput extends SlotItemHandler{
	
	private TileEntityProducer te;
	
	public SlotInput(IItemHandler itemHandler, int index, int xPosition, int yPosition, TileEntityProducer te) {
		super(itemHandler, index, xPosition, yPosition);
		this.te = te;
	}
	
	@Override
	public boolean isItemValid(@Nonnull ItemStack stack) {
		return stack.getItem() == te.getInput() && super.isItemValid(stack);
	}
}
