package tostimannetje.landleven.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import tostimannetje.landleven.container.ContainerAnimal;
import tostimannetje.landleven.container.ContainerMachine;
import tostimannetje.landleven.container.ContainerMarket;
import tostimannetje.landleven.container.ContainerSeedBag;
import tostimannetje.landleven.container.ContainerStore;
import tostimannetje.landleven.container.ContainerTractor;
import tostimannetje.landleven.gui.GuiAnimal;
import tostimannetje.landleven.gui.GuiMachine;
import tostimannetje.landleven.gui.GuiMarket;
import tostimannetje.landleven.gui.GuiQuestbook;
import tostimannetje.landleven.gui.GuiSeedBag;
import tostimannetje.landleven.gui.GuiStore;
import tostimannetje.landleven.gui.GuiTractor;
import tostimannetje.landleven.tileentity.TileEntityAnimal;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntityMarket;
import tostimannetje.landleven.tileentity.TileEntityStore;

public class GuiHandler implements IGuiHandler{
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == -2) {
			return new ContainerSeedBag(player.inventory, player.getHeldItemMainhand().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
		}else if(ID == -1) {
			return null;
		}else if(ID == 0) {
			return new ContainerStore(player.inventory, (TileEntityStore)world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID == 1) {
			return new ContainerMarket(player.inventory, (TileEntityMarket)world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID < 1000) {
			return new ContainerMachine(player.inventory, (TileEntityMachine)world.getTileEntity(new BlockPos(x, y, z)));
		}else if (ID < 2000) {
			return new ContainerAnimal(player.inventory, (TileEntityAnimal)world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID == 3000){
			return new ContainerTractor(player.inventory, world.getEntityByID(x).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null));
		}else {
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == -2) {
			return new GuiSeedBag(player.inventory, getServerGuiElement(ID, player, world, x, y, z));
		}else if(ID == -1) {
			return new GuiQuestbook();
		}else if(ID == 0) {
			return new GuiStore(getServerGuiElement(ID, player, world, x, y, z));
		}else if(ID == 1) {
			return new GuiMarket(getServerGuiElement(ID, player, world, x, y, z),  (TileEntityMarket) world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID < 1000) {
			return new GuiMachine(player.inventory, getServerGuiElement(ID, player, world, x, y, z),  (TileEntityMachine) world.getTileEntity(new BlockPos(x, y, z)));	
		}else if(ID < 2000) {
			return new GuiAnimal(player.inventory, getServerGuiElement(ID, player, world, x, y, z),  (TileEntityAnimal) world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID == 3000){
			return new GuiTractor(player.inventory, getServerGuiElement(ID, player, world, x, y, z));
		}else {
			return null;
		}
		
	}

}
