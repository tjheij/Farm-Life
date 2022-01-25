package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.container.ContainerMachine;
import tostimannetje.landleven.container.ContainerTractor;
import tostimannetje.landleven.entity.EntityTractor;
import tostimannetje.landleven.tileentity.TileEntityMachine;

public class MessageMachineSelect implements IMessage{

	public byte id;
	
	public MessageMachineSelect(){

    }

    public MessageMachineSelect(byte id){
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
	
	public static class Handler implements IMessageHandler<MessageMachineSelect, IMessage>{

		@Override
		public IMessage onMessage(MessageMachineSelect message, MessageContext ctx) {
			Container container = ctx.getServerHandler().player.openContainer;
			if(container instanceof ContainerMachine){
				TileEntityMachine machine = ((ContainerMachine)container).getMachine();
	            machine.receiveButtonEvent(message.id);
			} else if(container instanceof ContainerTractor){
				EntityTractor tractor = ((ContainerTractor)container).getTractor();
	            tractor.receiveButtonEvent(message.id);
	            ((ContainerTractor)container).refresh();
			}
			return null;
		}
		
	}
	
}
