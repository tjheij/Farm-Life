package tostimannetje.landleven.network;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.ItemHandlerHelper;
import tostimannetje.landleven.container.ContainerMachine;
import tostimannetje.landleven.container.ContainerStore;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;
import tostimannetje.landleven.store.Store;
import tostimannetje.landleven.store.StoreItem;
import tostimannetje.landleven.tileentity.TileEntityMachine;

public class MessageStoreBuy implements IMessage{

	public int sectionid;
	public int itemid;
	
	public MessageStoreBuy(){}
	
	public MessageStoreBuy(byte sectionid, byte itemid){
		this.sectionid = sectionid;
		this.itemid = itemid;
    }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.sectionid = buf.readByte();
		this.itemid = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(sectionid);
		buf.writeByte(itemid);
	}
	
	
	public static class Handler implements IMessageHandler<MessageStoreBuy, IMessage>{

		@Override
		public IMessage onMessage(MessageStoreBuy message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().player;
			Container container = player.openContainer;
			if(container instanceof ContainerStore) {
				((ContainerStore) container).buy(player, message.sectionid, message.itemid);
			}

			return null;
		}
		
	}
}
