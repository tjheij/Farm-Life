package tostimannetje.landleven.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBaseProduct extends ItemFood{

	public static final double coinsPerFood = 35.0;
	public static final double satPerStep = 0.2;
	public int sellValue;
	public boolean isFood;
	public List<ItemBaseProduct> ingredients = new ArrayList<ItemBaseProduct>();
	public int processingSteps;
	
	public ItemBaseProduct(String name, int sellValue, boolean isFood) {
		super(getAmount(sellValue), 0, false);
		setUnlocalizedName(name);
		setRegistryName(name);
		this.sellValue = sellValue;
		this.isFood = isFood;
	}

	public static int getAmount(int sellValue) {
		return (int)Math.ceil(sellValue/coinsPerFood);
	}
	
	public float getSaturation() {
		return (float) (getProcessingSteps()*satPerStep);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("SellValue: $" + getSellValue());
		if(!ingredients.isEmpty()) {
			tooltip.add("Steps: " + getProcessingSteps());
		}
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
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving){
        if (entityLiving instanceof EntityPlayer){
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(new ItemFood(getAmount(sellValue), getSaturation(), false), stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));

            if (entityplayer instanceof EntityPlayerMP){
                CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
            }
        }

        stack.shrink(1);
        return stack;
    }
	
	public int getProcessingSteps() {
		int sum = 0;
		if(!ingredients.isEmpty()) {
			for(int i = 0; i < ingredients.size(); i++) {
				sum += ingredients.get(i).getProcessingSteps();
			}
			
			sum += 1;
		}
		return sum;
	}
}
