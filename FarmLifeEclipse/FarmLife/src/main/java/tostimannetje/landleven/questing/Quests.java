package tostimannetje.landleven.questing;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.questing.QuestLine.State;
import tostimannetje.landleven.questing.QuestReward.RewardType;
import tostimannetje.landleven.questing.QuestBase.QuestState;
import tostimannetje.landleven.questing.QuestBase.QuestType;

public class Quests implements IQuest{
	private ArrayList<QuestLine> questLines = new ArrayList<QuestLine>();
	private QuestLine activeQuestLine;
	private QuestBase activeQuest;
	public boolean firstTime = true;
	
	private int coins = 0;
	private int questLevel = 0;
	
	public Quests() {
		initQuests();
	}
	
	public void initQuests() {
		
		QuestLine starting = new QuestLine("The first steps", "textures/items/item_clover.png");
		starting.addQuest(new QuestBase(QuestType.ITEM, ModBlocks.blockStore.getItemDropped(ModBlocks.blockStore.getDefaultState(),null,0), 1, "blocks/store_side.png"));
		starting.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemSeedsClover, 10, "items/itemseeds_clover.png"));
		starting.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemClover, 10, "items/item_clover.png"));
		starting.addQuest(new QuestBase(QuestType.ITEM, ModBlocks.blockMarket.getItemDropped(ModBlocks.blockMarket.getDefaultState(),null,0), 1, "blocks/market_side.png"));
		starting.addQuest(new QuestBase(QuestType.SELL, ModItems.itemClover, 10, "items/item_clover.png"));
		starting.addReward(new QuestReward(RewardType.COINS, 500));
		questLines.add(starting);
		
		
		
		/*
		QuestLine breadAndWine = new QuestLine("Bread and Wine", "textures/items/itemmachine_winegrape.png");
		breadAndWine.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCheese, 20, "items/itemmachine_cheese.png"));
		breadAndWine.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemWineGrape, 50, "items/itemmachine_winegrape.png"));
		breadAndWine.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemFlourOats, 45, "items/itemmachine_flouroats.png"));
		breadAndWine.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemBreadOats, 45, "items/itemmachine_breadoats.png"));
		breadAndWine.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemWineChardonnay, 60, "items/itemmachine_winechardonnay.png"));
		breadAndWine.addReward(new QuestReward(RewardType.UNLOCK, 1));
		questLines.add(breadAndWine);
		
		QuestLine pastaParty = new QuestLine("Pasta Party", "textures/items/itemmachine_pastacherrytomato.png");
		pastaParty.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemBasilicum, 45, "items/item_basilicum.png"));
		pastaParty.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCherryTomato, 50, "items/item_cherrytomato.png"));
		pastaParty.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemChipPastamachine, 1, "blocks/machines/pastamachine.png"));
		pastaParty.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPastaBasilicum, 45, "items/itemmachine_pastabasilicum.png"));
		pastaParty.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPastaCherryTomato, 50, "items/itemmachine_pastacherrytomato.png"));
		questLines.add(pastaParty);
		
		QuestLine cookies = new QuestLine("Cookies", "textures/items/itemmachine_cookieoats.png");
		cookies.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemOats, 30, "items/item_oats.png"));
		cookies.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCookieOats, 30, "items/itemmachine_cookieoats.png"));
		cookies.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPurpleRose, 40, "items/item_purplerose.png"));
		cookies.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemWhiteChocolate, 10, "items/item_whitechocolate.png"));
		cookies.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCookieWhiteChocolate, 10, "items/itemmachine_cookiewhitechocolate.png"));
		questLines.add(cookies);
		
		QuestLine cinema = new QuestLine("Cinema", "textures/items/itemmachine_popcornvanilla.png");
		cinema.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCorn, 100, "items/item_corn.png"));
		cinema.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemChipPopcornmachine, 1, "blocks/machines/popcornmachine.png"));
		cinema.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPopcornVanilla, 25, "items/itemmachine_popcornvanilla.png"));
		cinema.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPopcornSugar, 30, "items/itemmachine_popcornsugar.png"));
		cinema.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPopcornChocolate, 45, "items/itemmachine_popcornchocolate.png"));
		questLines.add(cinema);
		
		QuestLine pancakes = new QuestLine("Pancakes for everyone", "textures/items/itemmachine_popcornvanilla.png");
		pancakes.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemFlourOats, 50, "items/itemmachine_flouroats.png"));
		pancakes.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemEggDuck, 50, "items/itemanimal_eggduck.png"));
		pancakes.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemChipPancakemachine, 1, "blocks/machines/pancakemachine.png"));
		pancakes.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPancakeMaple, 50, "items/itemmachine_pancakemaple.png"));
		pancakes.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemPancakeChocolate, 50, "items/itemmachine_pancakechocolate.png"));
		questLines.add(pancakes);
		
		QuestLine coffeecrazy = new QuestLine("Coffee Crazy", "textures/items/itemmachine_coffee.png");
		coffeecrazy.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemLavender, 60, "items/item_lavender.png"));
		coffeecrazy.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCheeseBuffalo, 70, "items/itemmachine_cheesebuffalo.png"));
		coffeecrazy.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemSausageBeef, 80, "items/itemmachine_sausagebeef.png"));
		coffeecrazy.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemWineGrape, 90, "items/itemmachine_winegrape.png"));
		coffeecrazy.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCoffee, 100, "items/itemmachine_coffee.png"));
		questLines.add(coffeecrazy);
		
		QuestLine cornforcorn = new QuestLine("Corn for Corn", "textures/items/item_corn.png");
		cornforcorn.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCorn, 150, "items/item_corn.png"));
		cornforcorn.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemMilkBuffalo, 150, "items/itemanimal_milkbuffalo.png"));
		cornforcorn.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCornflakeGrape, 50, "items/itemmachine_cornflakegrape.png"));
		cornforcorn.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCornflakeWalnut, 50, "items/itemmachine_cornflakewalnut.png"));
		cornforcorn.addQuest(new QuestBase(QuestType.ITEM, ModItems.itemCornflakeBanana, 50, "items/itemmachine_cornflakebanana.png"));
		questLines.add(cornforcorn);
		
		*/
		
		this.getQuestLine(0).setState(State.INPROGRESS);
		this.getQuest(0,0).setQuestState(QuestState.INPROGRESS);
	}
	
	@Override
	public ArrayList<QuestLine> getQuestLines(){
		return questLines;
	}
	
	@Override
	public QuestLine getQuestLine(int questline) {
		return questLines.get(questline);
	}
	
	@Override
	public QuestBase getQuest(int questline, int quest) {
		return questLines.get(questline).getQuests().get(quest);
	}
	
	@Override
	public void loadActiveQuest() {
		for(int i = 0; i < questLines.size(); i++) {
			if(getQuestLine(i).getState() != State.COMPLETED) {
				activeQuestLine = getQuestLine(i);
				activeQuestLine.setState(State.INPROGRESS);
				break;
			}
		}
		
		if(activeQuestLine != null) {
			for(int i = 0; i < activeQuestLine.getQuests().size(); i++) {
				if(activeQuestLine.getQuest(i).getQuestState() != QuestState.COMPLETED) {
					activeQuest = activeQuestLine.getQuest(i);
					activeQuest.setQuestState(QuestState.INPROGRESS);
					return;
				}
			}
		}
	}
	
	@Override
	public QuestBase getActiveQuest() {
		return activeQuest;
	}
	
	@Override
	public QuestLine getActiveQuestLine() {
		return activeQuestLine;
	}
	
	@Override
	//Completes the active quest and activates the next quest in the active questline
	public void completeActiveQuest() {
		activeQuest.completeQuest();
		activeQuestLine.addProgress();
		
		//Set next quest in activeQuestLine as active
		for(int i = 0; i < activeQuestLine.getQuests().size(); i++) {
			if(activeQuestLine.getQuest(i).getQuestState() != QuestState.COMPLETED) {
				activeQuest = activeQuestLine.getQuest(i);
				activeQuest.setQuestState(QuestState.INPROGRESS);
				return;
			}
		}
		
		//If the line has no more quests, set activeQuest to null
		activeQuest = null;
	}

	@Override
	//Completes the active questline, gives reward and activates next questline
	public void completeActiveQuestLine(EntityPlayer player) {
		activeQuestLine.completeQuestLine();
		QuestReward reward = activeQuestLine.getReward();
		//If reward is coins, add to player's account, else unlock item in player's store
		IQuest quest = player.getCapability(QuestProvider.QUEST, null);
		String rewardMessage = "";
		
		if(reward.getRewardType() == RewardType.COINS) {
			quest.addCoins(reward.getReward());
			rewardMessage = "You have received: " + reward.getReward() + " coins!";
		}else {
			quest.addQuestLevel(reward.getReward());
			rewardMessage = "New items are available in the store!";
		}
		
		player.sendMessage(new TextComponentString(rewardMessage));
		
		//Activate next questline
		for(int i = 0; i < questLines.size(); i++) {
			if(getQuestLine(i).getState() != State.COMPLETED) {
				activeQuestLine = getQuestLine(i);
				activeQuestLine.setState(State.INPROGRESS);
				activeQuestLine.getQuest(0).setQuestState(QuestState.INPROGRESS);
				activeQuest = activeQuestLine.getQuest(0);
				return;
			}
		}
		
		//If this code is reached, all questlines are completed
		activeQuestLine = null;
		activeQuest = null;
	}
	
	@Override
	public int getCoins() {
		return coins;
	}

	@Override
	public void setCoins(int coins) {
		this.coins = coins;
	}

	@Override
	public void addCoins(int coins) {
		this.coins += coins;
	}

	@Override
	public void subtractCoins(int coins) {
		this.coins -= coins;
		if(coins < 0) coins = 0;
	}
	
	@Override
	public String getCoinsString() {
		if(this.coins >= 10000) {
			return "$ " + Math.round(this.coins / 1000)+"K";
		}else {
			return "$ " + this.coins;
		}
	}
	
	@Override
	public int getQuestLevel() {
		return this.questLevel;
	}
	
	@Override
	public void setQuestLevel(int level) {
		this.questLevel = level;
	}
	
	@Override
	public void addQuestLevel(int level) {
		this.questLevel += level;
	}
	
	public boolean getFirstTime() {
		return firstTime;
	}
	
	public void setFirstTime(boolean isFirstTime) {
		this.firstTime = isFirstTime;
	}

	@Override
	public void syncQuest(int[][] questProgress) {
		//Synchronizes client progress with server progress
		//Loops through questProgress and sets the client instance to those values
		//Marks completed quests and questlines as completed and the next as inprogress
		
		for(int i = 0; i < this.getQuestLines().size(); i++) {
			this.getQuestLine(i).setState(State.NOTSTARTED);
			
			for(int j = 0; j < this.getQuestLine(i).getQuests().size(); j++) {
				//Load progress into client instance
				this.getQuest(i,j).setProgress(questProgress[i][j]);
				
				// If quest is completed, mark as complete
				if(this.getQuest(i,j).isCompleted()) {
					this.getQuest(i,j).completeQuest();
					this.getQuestLine(i).addProgress();
				//If the first quest of the line is not completed, set to inprogress
				}else if(j == 0) {
					this.getQuestLine(i).setState(State.INPROGRESS);
					this.getQuest(i,j).setQuestState(QuestState.INPROGRESS);
				//If the previous quest of the line is completed, set to inprogress
				}else if(this.getQuest(i,j-1).isCompleted()) {
					this.getQuestLine(i).setState(State.INPROGRESS);
					this.getQuest(i,j).setQuestState(QuestState.INPROGRESS);
				//Else the quest is not started yet
				}else {
					this.getQuest(i,j).setQuestState(QuestState.NOTSTARTED);
				}
			}
			
			//If the questline is completed and there is another questline, set first quest inprogress
			if(this.getQuestLine(i).isCompleted() && this.getQuestLine(i).getState() != State.COMPLETED) {
				this.getQuestLine(i).completeQuestLine(); 
				if(this.getQuestLines().size() > i+1) {
					this.getQuestLine(i+1).setState(State.INPROGRESS);
					this.getQuest(i+1,0).setQuestState(QuestState.INPROGRESS);
				}
			}
		}
	}
	
	//Called by server when quest progress is made
	//Syncs the progress to the client
	public void syncQuestProgress(int questLine, int quest, int progress) {
		this.getQuest(questLine,quest).setProgress(progress);
	}
	
	//Called by server when a quest is completed
	//Syncs the completion to the client
	public void syncQuestCompleted(int questLine, int quest) {
		this.getQuest(questLine,quest).setProgress(this.getQuest(questLine,quest).getGoal());
		
		// Mark as complete
		this.getQuest(questLine,quest).completeQuest();
		this.getQuestLine(questLine).addProgress();	
		
		//If the questline is completed and there is another questline, set first quest inprogress
		if(this.getQuestLine(questLine).isCompleted()) {
			this.getQuestLine(questLine).completeQuestLine(); 
			if(this.getQuestLines().size() > questLine+1) {
				this.getQuestLine(questLine+1).setState(State.INPROGRESS);
				this.getQuest(questLine+1,0).setQuestState(QuestState.INPROGRESS);
			}
		}
	}
	
	@Override
	public void syncReset(int questLine, int quest) {
		if(questLine == -1) {
			this.resetAllQuests();
		}else {
			this.resetQuest(questLine,quest);
		}
	}
	
	@Override
	public void resetQuest(int questLine, int quest) {
		for(int i = quest; i < this.questLines.get(questLine).getQuests().size(); i++) {
			this.questLines.get(questLine).getQuest(i).reset();
			this.questLines.get(questLine).reset();
		}
		
		//Reassign activequest and activequestline
		if(activeQuest != null) {
			this.activeQuest.reset();
			this.activeQuestLine.reset();
		}
		this.loadActiveQuest();
	}
	
	@Override
	public void resetAllQuests() {
		for(int i = 0; i < this.questLines.size(); i++) {
			this.resetQuest(i, 0);
		}
	}
}
