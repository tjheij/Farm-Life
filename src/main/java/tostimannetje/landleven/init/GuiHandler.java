package tostimannetje.landleven.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tostimannetje.landleven.container.ContainerAnimal;
import tostimannetje.landleven.container.ContainerMachine;
import tostimannetje.landleven.container.ContainerMarket;
import tostimannetje.landleven.container.ContainerStore;
import tostimannetje.landleven.gui.GuiAnimal;
import tostimannetje.landleven.gui.GuiMachine;
import tostimannetje.landleven.gui.GuiMarket;
import tostimannetje.landleven.gui.GuiQuestbook;
import tostimannetje.landleven.gui.GuiStore;
import tostimannetje.landleven.tileentity.TileEntityAnimal;
import tostimannetje.landleven.tileentity.TileEntityCheesemachine;
import tostimannetje.landleven.tileentity.TileEntityCookiemachine;
import tostimannetje.landleven.tileentity.TileEntityCow;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntityMarket;
import tostimannetje.landleven.tileentity.TileEntityMill;
import tostimannetje.landleven.tileentity.TileEntityPastamachine;
import tostimannetje.landleven.tileentity.TileEntityStore;
import tostimannetje.landleven.tileentity.TileEntityWinemachine;

public class GuiHandler implements IGuiHandler{
	
	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == -1) {
			return null;
		}else if(ID == 0) {
			return new ContainerStore(player.inventory, (TileEntityStore)world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID == 1) {
			return new ContainerMarket(player.inventory, (TileEntityMarket)world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID < 1000) {
			return new ContainerMachine(player.inventory, (TileEntityMachine)world.getTileEntity(new BlockPos(x, y, z)));
		}else if (ID < 2000) {
			return new ContainerAnimal(player.inventory, (TileEntityAnimal)world.getTileEntity(new BlockPos(x, y, z)));
		}else {
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == -1) {
			return new GuiQuestbook();
		}else if(ID == 0) {
			return new GuiStore(getServerGuiElement(ID, player, world, x, y, z),  (TileEntityStore) world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID == 1) {
			return new GuiMarket(getServerGuiElement(ID, player, world, x, y, z),  (TileEntityMarket) world.getTileEntity(new BlockPos(x, y, z)));
		}else if(ID < 1000) {
			return new GuiMachine(player.inventory, getServerGuiElement(ID, player, world, x, y, z),  (TileEntityMachine) world.getTileEntity(new BlockPos(x, y, z)));	
		}else if(ID < 2000) {
			return new GuiAnimal(player.inventory, getServerGuiElement(ID, player, world, x, y, z),  (TileEntityAnimal) world.getTileEntity(new BlockPos(x, y, z)));
		}else {
			return null;
		}
		
	}

}
