package tostimannetje.landleven.questing;

public class QuestReward {

	private RewardType type;
	private int reward = 0;
	
	public QuestReward(RewardType type, int reward) {
		this.type = type;
		this.reward = reward;
	}
	
	public RewardType getRewardType() {
		return this.type;
	}
	
	public int getReward() {
		return this.reward;
	}
	
	public enum RewardType {
		COINS,
		UNLOCK
	}
	
}
