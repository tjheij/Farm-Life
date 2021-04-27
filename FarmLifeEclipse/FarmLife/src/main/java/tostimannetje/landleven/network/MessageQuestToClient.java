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

public class MessageQuestToClient implements IMessage{

	private IQuest value;
	private int[] questLineProgress = new int[10];
	private int[][] questProgress = new int[10][10];
	private int questLineCount = 0;
	private int questCount = 0;
	
	public MessageQuestToClient(){
		this.value = null;
    }

    public MessageQuestToClient(EntityPlayer player){
    	this.value = player.getCapability(QuestProvider.QUEST, null);
    	this.questLineCount = value.getQuestLines().size();
    	
    	for(int i = 0; i < this.questLineCount; i++) {
    		this.questLineProgress[i] = value.getQuestLine(i).getProgress();
    		
			this.questCount = value.getQuestLine(i).getQuests().size();
			for(int j = 0; j < questCount; j++) {
				questProgress[i][j] = value.getQuest(i,j).getProgress();
			}
		}
    }
	
	@Override
	public void fromBytes(ByteBuf buf) {
		for(int i = 0; i < this.questProgress.length; i++) {
			questLineProgress[i] = buf.readInt();
			for(int j = 0; j < this.questProgress[i].length; j++) {
				questProgress[i][j] = buf.readInt();
			}
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		for(int i = 0; i < this.questProgress.length; i++) {
			buf.writeInt(questLineProgress[i]);
			for(int j = 0; j < this.questProgress[i].length; j++) {
				buf.writeInt(questProgress[i][j]);
			}
		}
	}
	
	public static class Handler implements IMessageHandler<MessageQuestToClient, IMessage>{

		@Override
		public IMessage onMessage(MessageQuestToClient message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
		       Entity player = Minecraft.getMinecraft().player;
		       IQuest quest = player.getCapability(QuestProvider.QUEST, null);
		       quest.syncQuest(message.questProgress);
		       quest.loadActiveQuest();
			});
			return null;
		}	
	}
}
