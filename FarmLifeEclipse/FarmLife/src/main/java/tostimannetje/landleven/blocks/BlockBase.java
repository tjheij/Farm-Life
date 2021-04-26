package tostimannetje.landleven.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import tostimannetje.landleven.init.ModBlocks;

public class BlockBase extends Block{

	public BlockBase(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModBlocks.farm_life_machines);
	}
	
}
