package tostimannetje.landleven.questing;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;

public class QuestBase {

	private int progress;
	private int goal;
	
	private Item item;
	private ResourceLocation icon;
	
	private QuestType type;
	private QuestState state = QuestState.NOTSTARTED; 
	
	public static enum QuestType{
		ITEM, SELL
	}
	
	public static enum QuestState{
		NOTSTARTED, INPROGRESS, COMPLETED
	}
	
	public QuestBase(QuestType type, Item item, int goal, String iconPath) {
		this.type = type;
		this.item = item;
		this.goal = goal;
		
		this.icon = new ResourceLocation(Reference.MODID, "textures/"+iconPath);
	}
	
	public QuestType getQuestType() {
		return this.type;
	}
	
	public Item getItem() {
		return this.item;
	}
	
	public int getProgress() {
		return this.progress;
	}
	
	public int getGoal() {
		return this.goal;
	}
	
	public QuestState getQuestState() {
		return this.state;
	}
	
	public void setQuestState(QuestState state) {
		this.state = state;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
		
		if(isCompleted()) {
			this.progress = goal;
		}
		
	}
	
	public void addProgress() {
		this.progress++;
	}
	
	public ResourceLocation getIcon() {
		return this.icon;
	}
	
	public boolean isCompleted() {
		return this.progress >= this.goal;
	}
	
	public void completeQuest() {
		this.setQuestState(QuestState.COMPLETED);
	}
	
	public void reset() {
		this.setQuestState(QuestState.NOTSTARTED);
		this.progress = 0;
	}
	
}
