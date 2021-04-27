package tostimannetje.landleven.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBaseProduct extends ItemFood{

	public int sellValue;
	public boolean isFood;
	
	public ItemBaseProduct(String name, int sellValue, boolean isFood) {
		super(getAmount(), getSaturation(), false);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.sellValue = sellValue;
		this.isFood = isFood;
	}

	public static int getAmount() {
		return 0;
	}
	
	public static float getSaturation() {
		return 0;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(" SellValue: $" + getSellValue());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public int getSellValue() {
		return sellValue;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		if(!this.isFood) {
			return EnumAction.NONE;
		}else {
			return EnumAction.EAT;
		}
    }

	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(!this.isFood) {
			ItemStack itemstack = playerIn.getHeldItem(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}else {
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
    }
}
