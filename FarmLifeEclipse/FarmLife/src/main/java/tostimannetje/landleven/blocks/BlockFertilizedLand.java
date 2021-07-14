package tostimannetje.landleven.blocks;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

public class BlockFertilizedLand extends BlockFarmland{

    public BlockFertilizedLand(String name){
    	super();
    	this.setHardness(0.5f);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
    }
    
    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable){
        EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        if(plantType == EnumPlantType.Crop) {
        	return true;
        }

        return false;
    }
}
