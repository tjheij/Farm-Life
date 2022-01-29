package tostimannetje.landleven.questing;

import java.util.ArrayList;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;

public class QuestLine {
	private ArrayList<QuestBase> quests = new ArrayList<QuestBase>();
	private QuestReward reward;
	private String name;
	private String iconPath;
	private State state = State.NOTSTARTED; 
	private int progress;
	
	public static enum State{
		NOTSTARTED, INPROGRESS, COMPLETED
	}
	
	public QuestLine(String name, String iconPath) {
		this.name = name;
		this.iconPath = iconPath;
	}
	
	public void addQuest(QuestBase quest) {
		quests.add(quest);
	}
	
	public QuestBase getQuest(int quest) {
		return quests.get(quest);
	}
	
	public void addReward(QuestReward reward) {
		this.reward = reward;
	}
	
	public QuestReward getReward() {
		return this.reward;
	}
	
	public String getName() {
		return name;
	}
	
	public ResourceLocation getIcon() {
		return new ResourceLocation(Reference.MODID, this.iconPath);
	}
	
	public ArrayList<QuestBase> getQuests(){
		return quests;
	}

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
		
		if(isCompleted()) {
			this.progress = quests.size();
		}
	}
	
	public void addProgress() {
		this.progress++;
	}
	
	public boolean isCompleted() {
		return progress >= quests.size();
	}
	
	public void completeQuestLine() {
		this.setState(State.COMPLETED);
	}
	
	public void reset() {
		this.setState(State.NOTSTARTED);
		this.progress = 0;
		for(int i = 0; i < this.quests.size(); i++) {
			if(this.quests.get(i).isCompleted()) {
				this.progress++;
			}
		}
	}
}
