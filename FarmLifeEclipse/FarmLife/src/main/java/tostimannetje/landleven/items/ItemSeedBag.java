package tostimannetje.landleven.items;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tostimannetje.landleven.Main;
import tostimannetje.landleven.container.ISeedBagProvider;
import tostimannetje.landleven.init.ModBlocks;

public class ItemSeedBag extends ItemBase{

	private final int numSlots = 27;
	
	public ItemSeedBag(String name) {
		super(name);
		this.setMaxStackSize(1);
	}


	@Override
	@Nullable
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt){
        return new ISeedBagProvider(numSlots);
    }
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, 
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		if(!worldIn.isRemote) {
			if(!player.isSneaking()) {
				IItemHandler bag = player.getHeldItem(hand).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
				if(bag != null) {
					for(int i = -1; i < 2; i++) {
						for(int j = -1; j < 2; j++) {
							BlockPos checkPos = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ() + j);
							BlockPos cropPos = new BlockPos(pos.getX() + i, pos.getY() + 1, pos.getZ() + j);
							if(worldIn.getBlockState(checkPos).getBlock() == Blocks.FARMLAND || worldIn.getBlockState(checkPos).getBlock() == ModBlocks.blockFertilizedLand
									&& worldIn.getBlockState(cropPos).getBlock() == Blocks.AIR) {
								plantSeed(bag, player, worldIn, checkPos, cropPos, hand, facing, hitX, hitY, hitZ);
							}
						}
					}
				}
			}
		}
		
        return EnumActionResult.FAIL;
    }
	
	//Called to plant a seed from the IItemHandler at checkPos
	private void plantSeed(IItemHandler bag, EntityPlayer player, World worldIn, BlockPos checkPos, BlockPos cropPos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		for(int k = 0;  k < bag.getSlots(); k++) {
			if(!bag.getStackInSlot(k).isEmpty()) {
				//If the seed is planted successfully, the block should update and the seed should be removed
				if(bag.getStackInSlot(k).getItem().onItemUse(player, worldIn, checkPos, hand, facing, hitX, hitY, hitZ) == EnumActionResult.SUCCESS) {
					worldIn.markBlockRangeForRenderUpdate(cropPos, cropPos);
					worldIn.notifyBlockUpdate(cropPos, Blocks.AIR.getDefaultState(), worldIn.getBlockState(cropPos), 3);
					worldIn.scheduleBlockUpdate(cropPos, worldIn.getBlockState(cropPos).getBlock(),0,0);
					bag.extractItem(k, 1, false);
					break;
				}
			}
		}
	}
	
	@Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if(player.isSneaking()) {
        	player.openGui(Main.instance, -2, world, 0, 0, 0);
        }

        return super.onItemRightClick(world, player, hand);
    }
}
