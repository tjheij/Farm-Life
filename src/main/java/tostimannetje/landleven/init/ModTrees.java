package tostimannetje.landleven.init;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import tostimannetje.landleven.blocks.BlockSapling;
import tostimannetje.landleven.blocks.BlockTreeLeaf;
import tostimannetje.landleven.blocks.BlockTreeLog;

public class ModTrees {
	private static Map<BlockSapling, BlockTreeLog> saplingToWood = Maps.<BlockSapling, BlockTreeLog>newLinkedHashMap();
	private static Map<BlockSapling, BlockTreeLeaf> saplingToLeaf = Maps.<BlockSapling, BlockTreeLeaf>newLinkedHashMap();
	private static Map<BlockTreeLog, BlockSapling> woodToSapling = Maps.<BlockTreeLog, BlockSapling>newLinkedHashMap();
	
	public static void init() {
		addTree(ModBlocks.saplingApple, ModBlocks.logApple, ModBlocks.leafApple);
		addTree(ModBlocks.saplingCherry, ModBlocks.logCherry, ModBlocks.leafCherry);
		addTree(ModBlocks.saplingWhiteChocolate, ModBlocks.logWhiteChocolate, ModBlocks.leafWhiteChocolate);
		addTree(ModBlocks.saplingMaple, ModBlocks.logMaple, ModBlocks.leafMaple);
		addTree(ModBlocks.saplingChocolate, ModBlocks.logChocolate, ModBlocks.leafChocolate);
		addTree(ModBlocks.saplingLychee, ModBlocks.logLychee, ModBlocks.leafLychee);
		addTree(ModBlocks.saplingOrange, ModBlocks.logOrange, ModBlocks.leafOrange);
		addTree(ModBlocks.saplingWalnut, ModBlocks.logWalnut, ModBlocks.leafWalnut);
		addTree(ModBlocks.saplingBanana, ModBlocks.logBanana, ModBlocks.leafBanana);
		addTree(ModBlocks.saplingLime, ModBlocks.logLime, ModBlocks.leafLime);
		addTree(ModBlocks.saplingLemon, ModBlocks.logLemon, ModBlocks.leafLemon);
		addTree(ModBlocks.saplingAlmond, ModBlocks.logAlmond, ModBlocks.leafAlmond);
		addTree(ModBlocks.saplingCoconut, ModBlocks.logCoconut, ModBlocks.leafCoconut);
	}
	
	public static void addTree(BlockSapling sapling, BlockTreeLog wood, BlockTreeLeaf leaf) {
		saplingToWood.put(sapling, wood);
		saplingToLeaf.put(sapling, leaf);
		woodToSapling.put(wood, sapling);
	}
	
	public static BlockTreeLog getWood(BlockSapling sapling) {
		return saplingToWood.get(sapling);
	}
	
	public static BlockTreeLeaf getLeaf(BlockSapling sapling) {
		return saplingToLeaf.get(sapling);
	}
	
	public static BlockSapling getSapling(BlockTreeLog wood) {
		return woodToSapling.get(wood);
	}
}
