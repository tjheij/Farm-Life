package tostimannetje.landleven;

import net.minecraft.block.BlockCrops;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.network.MessageCoinsToClient;
import tostimannetje.landleven.network.MessageQuestToClient;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;

public class EventHandler {
	
    @SubscribeEvent
    public void playerTickEvent(PlayerTickEvent event) {
    	if(!event.player.world.isRemote) {
    		if(event.phase == Phase.START) {
			    IQuest quest = event.player.getCapability(QuestProvider.QUEST, null);
			    if(quest.getActiveQuest() != null) {
		    		//Check if the quest can be completed
		    		if(quest.getActiveQuest().isCompleted()) {
		    			String message = "You have completed the quest: Produce " + quest.getActiveQuest().getGoal() + " " 
		    					+ I18n.format(quest.getActiveQuest().getItem().getUnlocalizedName() + ".name");
		    			event.player.sendMessage(new TextComponentString(message));
		    			quest.completeActiveQuest();
		    			if(quest.getActiveQuestLine().isCompleted()) {
		    				String message2 = "You have completed the quest line: " + quest.getActiveQuestLine().getName();
			    			event.player.sendMessage(new TextComponentString(message2));
		    				quest.completeActiveQuestLine();
		    			}
		    			event.player.world.playSound(null, new BlockPos(event.player.posX, event.player.posY, event.player.posZ), 
		    					SoundsHandler.questCompleted, SoundCategory.AMBIENT, 1.0f, 1.0f);
		    			
		    		//Check the total amount of quest items in inventory and set progress
		    		}else {
			    		int total = 0;
					    for(int i = 0; i < 36; i++) {
					    	if(event.player.inventory.getStackInSlot(i).getItem() == quest.getActiveQuest().getItem()) {
					    		total += event.player.inventory.getStackInSlot(i).getCount();
					    	}
					    }
					    quest.getActiveQuest().setProgress(total);
		    		}
		    		
		    		NetworkHandler.sendToPlayer(new MessageQuestToClient(event.player), (EntityPlayerMP) event.player);
			    }
    		}
    	}
    }
    
	@SubscribeEvent
	public void onPlayerLogIn(PlayerLoggedInEvent event){
		EntityPlayer player = event.player;
		IQuest quest = player.getCapability(QuestProvider.QUEST, null);

		NetworkHandler.sendToPlayer(new MessageQuestToClient(player), (EntityPlayerMP) player);
		NetworkHandler.sendToPlayer(new MessageCoinsToClient(player), (EntityPlayerMP) player);
		
		if(quest.getFirstTime()) {
			quest.setFirstTime(false);
			quest.loadActiveQuest();
			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.itemStartingPouch));
			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.itemQuestbook));
		}
	}
	
	@SubscribeEvent
	public void onDimensionChange(PlayerChangedDimensionEvent event) {
		EntityPlayer player = event.player;

		NetworkHandler.sendToPlayer(new MessageQuestToClient(player), (EntityPlayerMP) player);
		NetworkHandler.sendToPlayer(new MessageCoinsToClient(player), (EntityPlayerMP) player);
	}
    
	@SubscribeEvent
	public void onPlayerClone(PlayerRespawnEvent event) {
		EntityPlayer player = event.player;
		
		NetworkHandler.sendToPlayer(new MessageQuestToClient(player), (EntityPlayerMP) player);
		NetworkHandler.sendToPlayer(new MessageCoinsToClient(player), (EntityPlayerMP) player);
	}
	
	@SubscribeEvent
	public void onCropHarvest(BlockEvent.HarvestDropsEvent event) {
		if(event.getState().getBlock() instanceof BlockCrops) {
			if(event.getWorld().getBlockState(new BlockPos(event.getPos().getX(), event.getPos().getY()-1, event.getPos().getZ()))
					.getBlock() == ModBlocks.blockFertilizedLand){
				System.out.println("DROPS");
				int length = event.getDrops().size(); 
				for(int i = 0; i < length; i++) {
					event.getDrops().add(event.getDrops().get(i));
				}
			}
		}
	}
	
}
