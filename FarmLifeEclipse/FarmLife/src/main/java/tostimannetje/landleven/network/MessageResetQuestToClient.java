package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;

public class MessageResetQuestToClient implements IMessage{
;
	private int questLine = 0;
	private int quest = 0;
	
	public MessageResetQuestToClient(){}

    public MessageResetQuestToClient(int questLine, int quest){
    	this.questLine = questLine;
    	this.quest = quest;
    }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.questLine = buf.readInt();
		this.quest = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.questLine);
		buf.writeInt(this.quest);
	}
	
	public static class Handler implements IMessageHandler<MessageResetQuestToClient, IMessage>{

		@Override
		public IMessage onMessage(MessageResetQuestToClient message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
		       Entity player = Minecraft.getMinecraft().player;
		       IQuest quest = player.getCapability(QuestProvider.QUEST, null);
		       quest.syncReset(message.questLine, message.quest);
		       quest.loadActiveQuest();
			});
			return null;
		}	
	}
}
