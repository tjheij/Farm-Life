package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;

public class MessageCoinsToServer implements IMessage{

	private int coins;
	
	public MessageCoinsToServer(){
		this.coins = 0;
    }

    public MessageCoinsToServer(EntityPlayer player){
    	this.coins = player.getCapability(QuestProvider.QUEST, null).getCoins();
    }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.coins = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.coins);
	}
	
	public static class Handler implements IMessageHandler<MessageCoinsToServer, IMessage>{

		@Override
		public IMessage onMessage(MessageCoinsToServer message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				EntityPlayer player = ctx.getServerHandler().player;
				if(player != null) {
					IQuest quest = player.getCapability(QuestProvider.QUEST, null);
					if(quest != null) {
						quest.setCoins(message.coins);
					}
				}
			});
			return null;
		}	
	}
}
