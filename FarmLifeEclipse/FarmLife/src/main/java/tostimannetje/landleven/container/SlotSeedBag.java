package tostimannetje.landleven.container;

import javax.annotation.Nullable;

import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotSeedBag extends SlotItemHandler{

	public SlotSeedBag(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		return stack.getItem() instanceof ItemSeeds;
	}
	
}
