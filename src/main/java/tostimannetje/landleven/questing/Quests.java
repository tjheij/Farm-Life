package tostimannetje.landleven.questing;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.questing.QuestLine.State;
import tostimannetje.landleven.questing.QuestBase.QuestState;

public class Quests implements IQuest{
	private ArrayList<QuestLine> questLines = new ArrayList<QuestLine>();
	private QuestLine activeQuestLine;
	private QuestBase activeQuest;
	public boolean firstTime = true;
	
	private int coins = 0;
	
	public Quests() {
		initQuests();
	}
	
	public void initQuests() {
		
		QuestLine breadAndWine = new QuestLine("Bread and Wine", "textures/items/itemmachine_winegrape.png");
		breadAndWine.addQuest(new QuestBase(ModItems.itemCheese, 20, "items/itemmachine_cheese.png"));
		breadAndWine.addQuest(new QuestBase(ModItems.itemWineGrape, 50, "items/itemmachine_winegrape.png"));
		breadAndWine.addQuest(new QuestBase(ModItems.itemFlourOats, 45, "items/itemmachine_flouroats.png"));
		breadAndWine.addQuest(new QuestBase(ModItems.itemBreadOats, 45, "items/itemmachine_breadoats.png"));
		breadAndWine.addQuest(new QuestBase(ModItems.itemWineChardonnay, 60, "items/itemmachine_winechardonnay.png"));
		questLines.add(breadAndWine);
		
		QuestLine pastaParty = new QuestLine("Pasta Party", "textures/items/itemmachine_pastacherrytomato.png");
		pastaParty.addQuest(new QuestBase(ModItems.itemBasilicum, 45, "items/item_basilicum.png"));
		pastaParty.addQuest(new QuestBase(ModItems.itemCherryTomato, 50, "items/item_cherrytomato.png"));
		pastaParty.addQuest(new QuestBase(ModItems.itemChipPastamachine, 1, "blocks/machines/pastamachine.png"));
		pastaParty.addQuest(new QuestBase(ModItems.itemPastaBasilicum, 45, "items/itemmachine_pastabasilicum.png"));
		pastaParty.addQuest(new QuestBase(ModItems.itemPastaCherryTomato, 50, "items/itemmachine_pastacherrytomato.png"));
		questLines.add(pastaParty);
		
		QuestLine cookies = new QuestLine("Cookies", "textures/items/itemmachine_cookieoats.png");
		cookies.addQuest(new QuestBase(ModItems.itemOats, 30, "items/item_oats.png"));
		cookies.addQuest(new QuestBase(ModItems.itemCookieOats, 30, "items/itemmachine_cookieoats.png"));
		cookies.addQuest(new QuestBase(ModItems.itemPurpleRose, 40, "items/item_purplerose.png"));
		cookies.addQuest(new QuestBase(ModItems.itemWhiteChocolate, 10, "items/item_whitechocolate.png"));
		cookies.addQuest(new QuestBase(ModItems.itemCookieWhiteChocolate, 10, "items/itemmachine_cookiewhitechocolate.png"));
		questLines.add(cookies);
		
		QuestLine cinema = new QuestLine("Cinema", "textures/items/itemmachine_popcornvanilla.png");
		cinema.addQuest(new QuestBase(ModItems.itemCorn, 100, "items/item_corn.png"));
		cinema.addQuest(new QuestBase(ModItems.itemChipPopcornmachine, 1, "blocks/machines/popcornmachine.png"));
		cinema.addQuest(new QuestBase(ModItems.itemPopcornVanilla, 25, "items/itemmachine_popcornvanilla.png"));
		cinema.addQuest(new QuestBase(ModItems.itemPopcornSugar, 30, "items/itemmachine_popcornsugar.png"));
		cinema.addQuest(new QuestBase(ModItems.itemPopcornChocolate, 45, "items/itemmachine_popcornchocolate.png"));
		questLines.add(cinema);
		
		QuestLine pancakes = new QuestLine("Pancakes for everyone", "textures/items/itemmachine_popcornvanilla.png");
		pancakes.addQuest(new QuestBase(ModItems.itemFlourOats, 50, "items/itemmachine_flouroats.png"));
		pancakes.addQuest(new QuestBase(ModItems.itemEggDuck, 50, "items/itemanimal_eggduck.png"));
		pancakes.addQuest(new QuestBase(ModItems.itemChipPancakemachine, 1, "blocks/machines/pancakemachine.png"));
		pancakes.addQuest(new QuestBase(ModItems.itemPancakeMaple, 50, "items/itemmachine_pancakemaple.png"));
		pancakes.addQuest(new QuestBase(ModItems.itemPancakeChocolate, 50, "items/itemmachine_pancakechocolate.png"));
		questLines.add(pancakes);
		
		QuestLine coffeecrazy = new QuestLine("Coffee Crazy", "textures/items/itemmachine_coffee.png");
		coffeecrazy.addQuest(new QuestBase(ModItems.itemLavender, 60, "items/item_lavender.png"));
		coffeecrazy.addQuest(new QuestBase(ModItems.itemCheeseBuffalo, 70, "items/itemmachine_cheesebuffalo.png"));
		coffeecrazy.addQuest(new QuestBase(ModItems.itemSausageBeef, 80, "items/itemmachine_sausagebeef.png"));
		coffeecrazy.addQuest(new QuestBase(ModItems.itemWineGrape, 90, "items/itemmachine_winegrape.png"));
		coffeecrazy.addQuest(new QuestBase(ModItems.itemCoffee, 100, "items/itemmachine_coffee.png"));
		questLines.add(coffeecrazy);
		
		QuestLine cornforcorn = new QuestLine("Corn for Corn", "textures/items/item_corn.png");
		cornforcorn.addQuest(new QuestBase(ModItems.itemCorn, 150, "items/item_corn.png"));
		cornforcorn.addQuest(new QuestBase(ModItems.itemMilkBuffalo, 150, "items/itemanimal_milkbuffalo.png"));
		cornforcorn.addQuest(new QuestBase(ModItems.itemCornflakeGrape, 50, "items/itemmachine_cornflakegrape.png"));
		cornforcorn.addQuest(new QuestBase(ModItems.itemCornflakeWalnut, 50, "items/itemmachine_cornflakewalnut.png"));
		cornforcorn.addQuest(new QuestBase(ModItems.itemCornflakeBanana, 50, "items/itemmachine_cornflakebanana.png"));
		questLines.add(cornforcorn);
		
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
				break;
			}
		}
		
		for(int i = 0; i < activeQuestLine.getQuests().size(); i++) {
			if(activeQuestLine.getQuest(i).getQuestState() != QuestState.COMPLETED) {
				activeQuest = activeQuestLine.getQuest(i);
				return;
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
	}

	@Override
	public void completeActiveQuestLine() {
		activeQuestLine.completeQuestLine();
		
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
	
	public String getCoinsString() {
		if(coins >= 10000) {
			return "$ " + Math.round(coins / 1000)+"K";
		}else {
			return "$ " + coins;
		}
	}
	
	public boolean getFirstTime() {
		return firstTime;
	}
	
	public void setFirstTime(boolean isFirstTime) {
		this.firstTime = isFirstTime;
	}

	@Override
	public void syncQuest(int[][] questProgress) {
		//Syncs client progress with server progress
		//Loops through questProgress and sets this instance to those values
		//Marks completed quests and questlines as completed and the next as inprogress
		
		for(int i = 0; i < questProgress.length; i++) {
			for(int j = 0; j < questProgress[i].length; j++) {
				
				if(this.getQuestLines().size() > i) { 
					if(this.getQuestLine(i).getQuests().size() > j) {
						this.getQuest(i,j).setProgress(questProgress[i][j]);
						
						// If quest is completed, mark as complete
						if(this.getQuest(i,j).isCompleted()) {
							this.getQuest(i,j).completeQuest();
							//If questline has another quest, set inprogress
							//Else mark questline as complete, if there is another questline set first quest inprogress
							if(this.getQuestLine(i).getQuests().size() > j+1) {
								this.getQuest(i,j+1).setQuestState(QuestState.INPROGRESS);
							}else {
								this.getQuestLine(i).completeQuestLine();;
								if(this.getQuestLines().size() > i+1) {
									this.getQuestLine(i+1).setState(State.INPROGRESS);
									this.getQuest(i+1,0).setQuestState(QuestState.INPROGRESS);
								}
							}
						}
					}
				}
				
			}
		}
	}
	
}
