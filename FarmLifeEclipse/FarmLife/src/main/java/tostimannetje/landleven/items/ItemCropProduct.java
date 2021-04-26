package tostimannetje.landleven.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tostimannetje.landleven.init.ModItems;

public class ItemCropProduct extends Item{

	private int sellValue;
	
	public ItemCropProduct(String name, int sellValue) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.farm_life_crops);
		this.sellValue = sellValue;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(" SellValue: $" + getSellValue());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public int getSellValue() {
		return sellValue;
	}
	
}
