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

public class MessageStoreSetQuantity implements IMessage{

	public int storeAmount;
	
	public MessageStoreSetQuantity(){

    }
	
	public MessageStoreSetQuantity(byte storeAmount){
		this.storeAmount = storeAmount;
    }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.storeAmount = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(storeAmount);
	}
	
	
	public static class Handler implements IMessageHandler<MessageStoreSetQuantity, IMessage>{

		@Override
		public IMessage onMessage(MessageStoreSetQuantity message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().player;
			Container container = player.openContainer;
			if(container instanceof ContainerStore) {
			
				((ContainerStore) container).storeAmount = message.storeAmount;
			}

			return null;
		}
		
	}
}
