package tostimannetje.landleven.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestBase;
import tostimannetje.landleven.questing.QuestProvider;

public class MessageQuestProgressToClient implements IMessage{

	private IQuest value;
	private int progress = 0;
	private int questLine = 0;
	private int quest = 0;
	
	public MessageQuestProgressToClient(){
		this.value = null;
    }

    public MessageQuestProgressToClient(EntityPlayer player, QuestBase quest){
    	this.value = player.getCapability(QuestProvider.QUEST, null);
    	this.progress = quest.getProgress();
    	
    	boolean found = false;
    	for(int i = 0; i < value.getQuestLines().size() && !found; i++) {
    		for(int j = 0; j < value.getQuestLine(i).getQuests().size(); j++) {
    			if(quest == value.getQuest(i,j)) {
	    			this.questLine = i;
	    			this.quest = j;
	    			found = true;
    			}
    		}
    	}
    }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.questLine = buf.readInt();
		this.quest = buf.readInt();
		this.progress = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.questLine);
		buf.writeInt(this.quest);
		buf.writeInt(this.progress);
	}
	
	public static class Handler implements IMessageHandler<MessageQuestProgressToClient, IMessage>{

		@Override
		public IMessage onMessage(MessageQuestProgressToClient message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
		       Entity player = Minecraft.getMinecraft().player;
		       IQuest quest = player.getCapability(QuestProvider.QUEST, null);
		       quest.syncQuestProgress(message.questLine, message.quest, message.progress);
		       quest.loadActiveQuest();
			});
			return null;
		}	
	}
}
