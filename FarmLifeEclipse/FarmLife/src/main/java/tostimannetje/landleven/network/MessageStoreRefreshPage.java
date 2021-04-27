package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.container.ContainerStore;

public class MessageStoreRefreshPage implements IMessage{

	public int storeAmount;
	
	public MessageStoreRefreshPage(){

    }
	
	public MessageStoreRefreshPage(byte storeAmount){
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
	
	
	public static class Handler implements IMessageHandler<MessageStoreRefreshPage, IMessage>{

		@Override
		public IMessage onMessage(MessageStoreRefreshPage message, MessageContext ctx) {
			EntityPlayer player = ctx.getServerHandler().player;
			Container container = player.openContainer;
			if(container instanceof ContainerStore) {
			
				((ContainerStore) container).storeAmount = message.storeAmount;
			}

			return null;
		}
		
	}
}
