package tostimannetje.landleven.container;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.network.MessageCoinsToClient;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;
import tostimannetje.landleven.store.Store;
import tostimannetje.landleven.store.StoreItem;
import tostimannetje.landleven.store.StoreSection;
import tostimannetje.landleven.store.StoreSectionBlocks;
import tostimannetje.landleven.store.StoreSectionItems;
import tostimannetje.landleven.tileentity.TileEntityStore;

public class ContainerStore extends Container{
	private TileEntityStore te;
	public int storeAmount;
	
	
	public ContainerStore(InventoryPlayer playerInv, final TileEntityStore te) {
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 48 + j * 18, 174 + i * 18));
			}
		}
	
		for (int k = 0; k < 9; k++) {
			addSlotToContainer(new Slot(playerInv, k, 48 + k * 18, 232));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	public TileEntityStore getMachine(){
		return te;
	}
	
	public void buy(EntityPlayer player, int currentSection, int itemid) {
		List<StoreItem> storeItems = new ArrayList<StoreItem>();
		StoreSection storeSection = Store.getStoreSections().get(currentSection);
		storeItems = storeSection.getSection();
		Item item = ModItems.itemClover;
		
		IQuest questCapability = player.getCapability(QuestProvider.QUEST, null);
		int price = storeAmount * storeItems.get(itemid).price;
		
		if(questCapability.getCoins() >= price) {
			if(storeSection instanceof StoreSectionItems) {
				item = storeItems.get(itemid).item;
			}else if(storeSection instanceof StoreSectionBlocks) {
				item = Item.getItemFromBlock(storeItems.get(itemid).block);
			}
			
			for(int i = 0; i < storeAmount / 64; i++) {
				ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(item, 64));
			}
			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(item, storeAmount % 64));
			
			questCapability.subtractCoins(price);
			NetworkHandler.sendToPlayer(new MessageCoinsToClient(player), (EntityPlayerMP) player);

		}
	}
}
