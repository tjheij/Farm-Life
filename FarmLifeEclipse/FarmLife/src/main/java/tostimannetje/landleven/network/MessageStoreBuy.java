package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.container.ContainerStore;

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
