package tostimannetje.landleven.gui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.network.MessageStoreBuy;
import tostimannetje.landleven.network.MessageStoreSetQuantity;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;
import tostimannetje.landleven.store.Store;
import tostimannetje.landleven.store.StoreItem;
import tostimannetje.landleven.store.StoreSection;
import tostimannetje.landleven.store.StoreSectionBlocks;
import tostimannetje.landleven.store.StoreSectionItems;

public class GuiStore extends GuiContainer{

	private static final ResourceLocation BACKGROUND = new ResourceLocation(Reference.MODID, "textures/gui/guistore.png");
	private static final ResourceLocation STOREITEM = new ResourceLocation(Reference.MODID, "textures/gui/guistorebuttons.png");
	private List<StoreSection> storeSections = new ArrayList<StoreSection>();
	private List<StoreItem> storeItems = new ArrayList<StoreItem>();
	private int[] storeAmounts;
	private int currentSection = 0;
	private int currentPage = 0;
	private int storewidth = 256;
	private int storeheight = 256;
	private int x,y;
	int coins;
	
	
	public GuiStore(Container container) {
		super(container);
		this.xSize = storewidth;
		this.ySize = storeheight;
		IQuest questCapability = Minecraft.getMinecraft().player.getCapability(QuestProvider.QUEST, null);
		this.coins = questCapability.getCoins();
		x = 14 + (width - xSize) / 2;
		y = 37 + (height - ySize) / 2;
	}

	@Override
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		
		storeSections = Store.getStoreSections();
		storeItems = storeSections.get(currentSection).getSection();
		storeAmounts = new int[storeItems.size()];
		
		for(int i = 0; i < storeItems.size(); i++) {
			storeAmounts[i] = 1;
		}
		
		x = 14 + (width - xSize) / 2;
		y = 37 + (height - ySize) / 2;
		
		for(int i = 0; i < storeSections.size(); i++) {
			this.buttonList.add(new GuiStoreButton(66 + i, x-12 +20 * i, y-37, 38 + 20 * i, 70, 20, 18, 0));
		}
		
		
		
		//If current page is not the last page, add next page button
		if(currentPage+1 < storeSections.get(currentSection).getSectionPageCount()) {
			this.buttonList.add(new GuiStoreButton( 0, x+231, y+59, 38, 28, 10, 21, 21));
		}
		//If current page is not the first page, add previous page button
		if(currentPage > 0) {
			this.buttonList.add(new GuiStoreButton( 1, x-13, y+59, 48, 28, 10, 21, 21));
		}
		
		for(int i = 1; i <= 12; i++) {
			//Draw if there are enough items on the page for the i'th
			if(i + currentPage * 12 <= storeItems.size()) {
				//Buttons that control amount to buy
				this.buttonList.add(new GuiStoreButton( i*5+1, x+4, y+20, 38, 0, 5, 5, 14));
				this.buttonList.add(new GuiStoreButton( i*5+2, x+4, y+28, 38, 5, 5, 9, 14));
				this.buttonList.add(new GuiStoreButton( i*5+3, x+29, y+20, 43, 0, 5, 5, 14));
				this.buttonList.add(new GuiStoreButton( i*5+4, x+29, y+28, 43, 5, 5, 9, 14));
				//Button to buy item
				this.buttonList.add(new GuiStoreButton( i*5+5, x+4, y+52, 48, 0, 31, 11, 11));
				
				if(x >= (width - xSize) / 2 + 204) {
					y+= StoreItem.HEIGHT;
					x = 14 + (width - xSize) / 2;
				}else{
					x += StoreItem.WIDTH;
				}
			}
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(BACKGROUND);
		int xBackground = (width - xSize) / 2;
		int yBackground = (height - ySize) / 2;
		drawTexturedModalRect(xBackground, yBackground, 0, 0, xSize, ySize);
		
		x = 14 + (width - xSize) / 2;
		y = 37 + (height - ySize) / 2;
		
		mc.getTextureManager().bindTexture(STOREITEM);
		drawModalRectWithCustomSizedTexture(x -12 + currentSection * 20, y-19, 38, 89, 20, 3, 100, 100);
		
		
		
		for(int i = 0; i + currentPage * 12 < storeItems.size() && i < 12; i++) {
			//Draw if there are enough items on the page for the i'th
			if(i + currentPage * 12 <= storeItems.size()) {
				//Draw Item background
				GlStateManager.color(1, 1, 1, 1);
				mc.getTextureManager().bindTexture(STOREITEM);
				this.drawTexturedModalRect(x, y, 0, 0, StoreItem.WIDTH, StoreItem.HEIGHT);
				
				//Draw Item icon
				if(storeSections.get(currentSection) instanceof StoreSectionItems) {
					drawItemIcon(storeItems.get(i + currentPage*12), this.x, this.y);
				}else if (storeSections.get(currentSection) instanceof StoreSectionBlocks) {
					RenderHelper.disableStandardItemLighting();
					RenderHelper.enableGUIStandardItemLighting();
					drawBlockIcon(storeItems.get(i + currentPage*12), this.x, this.y, (String)null);
				}
				
				//Draw Amount
				String amount = String.valueOf(storeAmounts[i]);
				this.fontRenderer.drawString(amount, x + StoreItem.WIDTH / 2 - fontRenderer.getStringWidth(amount) / 2, y+ 40, 0x404040);
				
				if(x >= (width - xSize) / 2 + 204) {
					y+= StoreItem.HEIGHT;
					x = 14 + (width - xSize) / 2;
				}else {
					x += StoreItem.WIDTH;
				}
			}
		}
		
	}
	
	public void drawItemIcon(StoreItem storeItem, int x, int y) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(storeItem.icon);
		drawModalRectWithCustomSizedTexture(x + 11, y + 17, 0, 0, 16, 16, 16, 16);
	}
	
	public void drawBlockIcon(StoreItem storeItem, int x, int y, String altText) {
		ItemStack stack = new ItemStack(storeItem.block);
		
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.translate(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        this.itemRender.zLevel = 200.0F;
        net.minecraft.client.gui.FontRenderer font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = fontRenderer;
        this.itemRender.renderItemAndEffectIntoGUI(stack, x+11, y+17);
        this.itemRender.renderItemOverlayIntoGUI(font, stack, x, y, altText);
        this.zLevel = 0.0F;
        this.itemRender.zLevel = 0.0F;
        
	}
	
	public int scale(int in, float size) {
		return Math.round(in / size);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		x = 14;
		y = 37;
		
		String name = I18n.format(ModBlocks.blockStore.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		
		IQuest quest = Minecraft.getMinecraft().player.getCapability(QuestProvider.QUEST, null);
		String coins = quest.getCoinsString();
		this.fontRenderer.drawString(coins, 250 - fontRenderer.getStringWidth(coins), 25, 0x404040);
		
		String sectionName = Store.getStoreSections().get(currentSection).getSectionName();
		this.fontRenderer.drawString(sectionName, 50 - fontRenderer.getStringWidth(sectionName), 25, 0x404040);
		
		for(int i = 0; i + currentPage * 12 < storeItems.size() && i < 12; i++) {
			//Draw if there are enough items on the page for the i'th
			if(i + currentPage * 12 <= storeSections.get(currentSection).getSection().size()) {
				String price = String.valueOf(storeItems.get(i + currentPage*12).price * storeAmounts[i]);
				this.fontRenderer.drawString(price, x + 19 - fontRenderer.getStringWidth(price) / 2, y+ 54, 0x404040);
				
				if (mouseX >= x + 10 + (width - xSize) / 2 && mouseX <= x + 28 + (width - xSize) / 2 && 
						mouseY >= y + 16 + (height - ySize) / 2 && mouseY <= y + 34 + (height - ySize) / 2){

					String line = "";
					if(storeSections.get(currentSection) instanceof StoreSectionItems) {
						line = I18n.format(storeItems.get(i + currentPage*12).item.getUnlocalizedName() + ".name");
					}else if(storeSections.get(currentSection) instanceof StoreSectionBlocks) {
						line = I18n.format(storeItems.get(i + currentPage*12).block.getUnlocalizedName() +".name");
					}
		            
		            this.drawHoveringText(line, mouseX - (width - xSize) / 2, mouseY - (height - ySize) / 2);
		        }
				
				if(x >= 204) {
					y+= StoreItem.HEIGHT;
					x = 14;
				}else {
					x += StoreItem.WIDTH;
				}
			}
		}
		
	}
	
	@Override
	protected void actionPerformed(GuiButton b){
		if(b.id == 0) {
			currentPage++;
			initGui();
		}else if(b.id == 1) {
			currentPage--;
			initGui();
		}else if(b.id > 1 && b.id <= 65) {
			//idrest determines which button is pressed for the specific storeItem
			//itemid determines for which storeItem a button has been pressed
			int idrest = b.id % 5;
			int itemid = (int) Math.ceil((b.id-5) / 5);
			switch(idrest) {
			case 1:
				storeAmounts[itemid] -= 1;
				if(storeAmounts[itemid] < 1) storeAmounts[itemid] = 1;
				break;
			case 2:
				storeAmounts[itemid] -= 64;
				if(storeAmounts[itemid] < 1) storeAmounts[itemid] = 1;
				break;
			case 3:
				storeAmounts[itemid] += 1;
				if(storeAmounts[itemid] > 1728) storeAmounts[itemid] = 1728;
				break;
			case 4:
				if(storeAmounts[itemid] == 1){ 
					storeAmounts[itemid] += 63;
				}else{
					storeAmounts[itemid] += 64;
				}
				if(storeAmounts[itemid] > 1728) storeAmounts[itemid] = 1728;
				break;
			case 0:
				if(storeItems.size() > itemid-1 + currentPage * 12) {
					if(coins >= storeAmounts[itemid-1] * storeItems.get(itemid-1).price) {
						NetworkHandler.sendToServer(new MessageStoreSetQuantity((byte)storeAmounts[itemid-1]));
						NetworkHandler.sendToServer(new MessageStoreBuy((byte) currentSection, (byte)(itemid-1 + currentPage * 12)));
					}
				}
				break;
			default:
				break;
			}
		}else if(b.id > 65) {
			this.currentSection = b.id - 66;
			this.currentPage = 0;
			initGui();
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}
