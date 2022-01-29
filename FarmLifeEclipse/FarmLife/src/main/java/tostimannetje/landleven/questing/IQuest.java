package tostimannetje.landleven.questing;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public interface IQuest {
	public ArrayList<QuestLine> getQuestLines();
	
	public QuestLine getQuestLine(int questline);
	public QuestBase getQuest(int questline, int quest);
	
	public void loadActiveQuest();
	public QuestLine getActiveQuestLine();
	public QuestBase getActiveQuest();
	public void completeActiveQuest();
	public void completeActiveQuestLine(EntityPlayer player);
	
	public int getCoins();
	public void setCoins(int coins);
	public void addCoins(int coins);
	public void subtractCoins(int coins);
	
	public String getCoinsString();
	
	public int getQuestLevel();
	public void setQuestLevel(int level);
	public void addQuestLevel(int level);
	
	public boolean getFirstTime();
	public void setFirstTime(boolean isFirstTime);
	
	public void syncQuest(int[][] questProgress);
	public void syncQuestProgress(int questLine, int quest, int progress);
	public void syncQuestCompleted(int questLine, int quest);
	public void syncReset(int questLine, int quest);
	
	public void resetQuest(int questLine, int quest);
	public void resetAllQuests();
}
