package tostimannetje.landleven.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tostimannetje.landleven.entity.EntityTractor;

public class ItemTractor extends ItemBase implements IHasPrice{

	private int price;
	
	public ItemTractor(String name, int price) {
		super(name);
		this.price = price;
		this.setMaxStackSize(1);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, 
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
		ItemStack stack = player.getHeldItem(hand);
		if(!worldIn.isRemote) {
			EntityTractor entityTractor = EntityTractor.create(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY() + 1.5D, (double)pos.getZ() + 0.5D);
			worldIn.spawnEntity(entityTractor);
		}
		
		stack.shrink(1);
		return EnumActionResult.SUCCESS;
    }

	@Override
	public Item getItem() {
		return this;
	}

	@Override
	public Block getBlock() {
		return null;
	}

	@Override
	public int getPrice() {
		return price;
	}
	
}
