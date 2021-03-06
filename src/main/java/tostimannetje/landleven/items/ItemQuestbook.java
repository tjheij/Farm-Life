package tostimannetje.landleven.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.init.ModItems;

public class ItemQuestbook extends Item{

	public ItemQuestbook(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {	
      playerIn.openGui(Main.instance, -1, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
      
      return new ActionResult(EnumActionResult.SUCCESS, new ItemStack(this));
    }

	
}
