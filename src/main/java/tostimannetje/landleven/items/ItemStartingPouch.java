package tostimannetje.landleven.items;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;

public class ItemStartingPouch extends Item{

	private int startingCoins = 1000;
	
	public ItemStartingPouch(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {	
      IQuest questCapability = playerIn.getCapability(QuestProvider.QUEST, null);
      questCapability.addCoins(startingCoins);
      if(!worldIn.isRemote) {
	      String message = "You received $" + startingCoins;
	      playerIn.sendMessage(new TextComponentString(message));
      }
      return new ActionResult(EnumActionResult.PASS, ItemStack.EMPTY);
    }

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Right-click to receive some coins");
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
