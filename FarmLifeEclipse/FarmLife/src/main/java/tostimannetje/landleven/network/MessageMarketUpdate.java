package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.container.ContainerMachine;
import tostimannetje.landleven.container.ContainerMarket;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.tileentity.TileEntityMachine;
import tostimannetje.landleven.tileentity.TileEntityMarket;

public class MessageMarketUpdate implements IMessage{

	public byte id;
	
	public MessageMarketUpdate(){}
	
	public MessageMarketUpdate(byte id){
		this.id = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.id = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(id);
	}
	
	public static class Handler implements IMessageHandler<MessageMarketUpdate, IMessage>{

		@Override
		public IMessage onMessage(MessageMarketUpdate message, MessageContext ctx) {
			Container container = ctx.getServerHandler().player.openContainer;
			if(container instanceof ContainerMarket){
				TileEntityMarket market = ((ContainerMarket)container).getTE();
	            market.receiveButtonEvent(message.id);
			}
			return null;
		}
		
	}
	
}
