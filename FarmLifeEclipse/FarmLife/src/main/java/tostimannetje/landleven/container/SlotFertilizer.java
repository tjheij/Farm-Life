package tostimannetje.landleven.container;

import javax.annotation.Nullable;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import tostimannetje.landleven.items.ItemFertilizer;

public class SlotFertilizer extends SlotItemHandler{

	public SlotFertilizer(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(@Nullable ItemStack stack) {
		if(stack.getItem() instanceof ItemFertilizer) {
			return true;
		}else if(stack.getItem() instanceof ItemDye) {
			EnumDyeColor enumdyecolor = EnumDyeColor.byDyeDamage(stack.getMetadata());
			if(enumdyecolor == EnumDyeColor.WHITE) {
				return true;
			}
		}
		return false; 
	}
	
}
