package tostimannetje.landleven.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tostimannetje.landleven.init.ModBlocks;

public class ItemFertilizer extends ItemBase{

	public ItemFertilizer(String name) {
		super(name);
	}

	//If right-clicked with fertilizer on farmland, it should turn into fertilized land
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, 
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		Block block = worldIn.getBlockState(pos).getBlock();
		if(block == Blocks.FARMLAND) {
			turnToFertilizedLand(worldIn, pos);
			player.getHeldItem(hand).shrink(1);
		}
		
        return EnumActionResult.SUCCESS;
    }
	
	public void turnToFertilizedLand(World world, BlockPos pos) {
		world.setBlockState(pos, ModBlocks.blockFertilizedLand.getDefaultState());
	}
}