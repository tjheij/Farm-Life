package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;

public class MessageCoinsToClient implements IMessage{

	private int coins;
	
	public MessageCoinsToClient(){
		this.coins = 0;
    }

    public MessageCoinsToClient(EntityPlayer player){
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
	
	public static class Handler implements IMessageHandler<MessageCoinsToClient, IMessage>{

		@Override
		public IMessage onMessage(MessageCoinsToClient message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
		       Entity player = Minecraft.getMinecraft().player;
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
