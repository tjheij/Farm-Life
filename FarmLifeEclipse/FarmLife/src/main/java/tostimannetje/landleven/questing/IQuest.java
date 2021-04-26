package tostimannetje.landleven.questing;

import java.util.ArrayList;

public interface IQuest {
	public ArrayList<QuestLine> getQuestLines();
	
	public QuestLine getQuestLine(int questline);
	public QuestBase getQuest(int questline, int quest);
	
	public void loadActiveQuest();
	public QuestLine getActiveQuestLine();
	public QuestBase getActiveQuest();
	public void completeActiveQuest();
	public void completeActiveQuestLine();
	
	public int getCoins();
	public void setCoins(int coins);
	public void addCoins(int coins);
	public void subtractCoins(int coins);
	
	public String getCoinsString();
	
	public boolean getFirstTime();
	public void setFirstTime(boolean isFirstTime);
	
	public void syncQuest(int[][] questProgress);
}
