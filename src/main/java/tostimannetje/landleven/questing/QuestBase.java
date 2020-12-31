package tostimannetje.landleven.questing;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;

public class QuestBase {

	private int progress;
	private int goal;
	
	private Item item;
	private ResourceLocation icon;
	
	private QuestState state = QuestState.NOTSTARTED; 
	
	public static enum QuestState{
		NOTSTARTED, INPROGRESS, COMPLETED
	}
	
	public QuestBase(Item item, int goal, String iconPath) {
		this.item = item;
		this.goal = goal;
		
		this.icon = new ResourceLocation(Reference.MODID, "textures/"+iconPath);
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public int getGoal() {
		return goal;
	}
	
	public QuestState getQuestState() {
		return state;
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
		return progress >= goal;
	}
	
	public void completeQuest() {
		this.setQuestState(QuestState.COMPLETED);
	}
	
}
