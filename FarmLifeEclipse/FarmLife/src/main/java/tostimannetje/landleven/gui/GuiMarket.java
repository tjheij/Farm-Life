package tostimannetje.landleven.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.items.ItemAnimalProduct;
import tostimannetje.landleven.items.ItemCropProduct;
import tostimannetje.landleven.items.ItemProduct;
import tostimannetje.landleven.items.ItemTreeProduct;
import tostimannetje.landleven.network.MessageCoinsToServer;
import tostimannetje.landleven.network.MessageMarketUpdate;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;
import tostimannetje.landleven.tileentity.TileEntityMarket;

public class GuiMarket extends GuiContainer{

	private static final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/guimarket.png");
	private TileEntityMarket te;
	private int coins;
	private IQuest questCapability;
	private int x,y;
	private boolean isOnAutoMode;
	
	public GuiMarket(Container container, TileEntityMarket te) {
		super(container);
		this.te = te;
		questCapability = Minecraft.getMinecraft().player.getCapability(QuestProvider.QUEST, null);
		this.coins = questCapability.getCoins();
	}

	 @Override
	 public void initGui(){
		super.initGui();
		x = (width - xSize) / 2;
		y = (height - ySize) / 2;
		this.isOnAutoMode = te.isOnAutoMode();
		initButtons();
	 }
	 
	 public void initButtons() {
		 this.buttonList.clear();
		 
		 this.buttonList.add(new GuiMarketButton("Auto", 0, x+140, y+45, 27, 15));
		 
		 //If is on auto mode, set collect button
		 if(!this.isOnAutoMode) {
			 this.buttonList.add(new GuiMarketButton("", 1, x+7, y+59, 48, 15));
		 }

		 //If is not on auto mode, set manual button
		 if(this.isOnAutoMode) {
			 this.buttonList.add(new GuiMarketButton("Collect", 2, x+64, y+59, 48, 15));
		 }
		 
	 }
	 
	 @Override
	 protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		x = (width - xSize) / 2;
		y = (height - ySize) / 2;
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(GUI);
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		//If is on auto mode, also draw red LED and collected coins bar
		if(this.isOnAutoMode) {
			this.drawTexturedModalRect(x+149, y+62, 176, 60, 9, 9);
			this.drawTexturedModalRect(x+65, y+39, 176, 69, 46, 13);
		}
	 }

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = I18n.format(ModBlocks.blockMarket.getUnlocalizedName() + ".name");
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		
		String desc = I18n.format("Sell your items here:");
		fontRenderer.drawString(desc, xSize / 2 - fontRenderer.getStringWidth(desc) / 2, 18, 0x404040);
		
		String coins = questCapability.getCoinsString();
		this.fontRenderer.drawString(coins, 171 - fontRenderer.getStringWidth(coins), 7, 0x404040);
		
		//If is on auto mode, also draw collected coins string
		if(this.isOnAutoMode) {
			String coinsAuto = String.valueOf(te.getCollectedCoins());
			this.fontRenderer.drawString(coinsAuto, xSize/2 - fontRenderer.getStringWidth(coinsAuto) / 2, 42, 0x404040);
		}
		
		//Calculate price of item in the slot
		String price = "";
		if(!this.isOnAutoMode) {
			IItemHandler slot = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
			Item item = slot.getStackInSlot(0).getItem();
			if(item instanceof ItemProduct) {
				price = "$" + String.valueOf(((ItemProduct) item).getSellValue()*slot.getStackInSlot(0).getCount());
			}else if(item instanceof ItemCropProduct) {
				price = "$" + String.valueOf(((ItemCropProduct) item).getSellValue()*slot.getStackInSlot(0).getCount());
			}else if(item instanceof ItemTreeProduct) {
				price = "$" + String.valueOf(((ItemTreeProduct) item).getSellValue()*slot.getStackInSlot(0).getCount());
			}else if(item instanceof ItemAnimalProduct) {
				price = "$" + String.valueOf(((ItemAnimalProduct) item).getSellValue()*slot.getStackInSlot(0).getCount());
			}else if(slot.getStackInSlot(0).getCount() == 0) {
				price = "";
			}
		}
		
		this.fontRenderer.drawString(price, 32 - fontRenderer.getStringWidth(price) / 2, 63, 0x404040);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
	
	@Override
	protected void actionPerformed(GuiButton b){
		if(b.id == 0) { 
			//Toggle auto mode
			isOnAutoMode = !isOnAutoMode;
			initButtons();
		}else if(b.id == 1) {
			// Sell items
			IItemHandler slot = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.NORTH);
			Item item = slot.getStackInSlot(0).getItem();
			if(item instanceof ItemProduct) {
				coins += ((ItemProduct) item).getSellValue() * slot.getStackInSlot(0).getCount();
				slot.extractItem(0, slot.getStackInSlot(0).getCount(), false);
			}else if(item instanceof ItemCropProduct) {
				coins += ((ItemCropProduct) item).getSellValue() * slot.getStackInSlot(0).getCount();
				slot.extractItem(0, slot.getStackInSlot(0).getCount(), false);
			}else if(item instanceof ItemTreeProduct) {
				coins += ((ItemTreeProduct) item).getSellValue() * slot.getStackInSlot(0).getCount();
				slot.extractItem(0, slot.getStackInSlot(0).getCount(), false);
			}else if(item instanceof ItemAnimalProduct) {
				coins += ((ItemAnimalProduct) item).getSellValue() * slot.getStackInSlot(0).getCount();
				slot.extractItem(0, slot.getStackInSlot(0).getCount(), false);
			}
			questCapability.setCoins(coins);
			NetworkHandler.sendToServer(new MessageCoinsToServer(Minecraft.getMinecraft().player));
		}else if(b.id == 2) {
			//Collect coins
			coins += te.getCollectedCoins();
			questCapability.addCoins(te.getCollectedCoins());
			NetworkHandler.sendToServer(new MessageCoinsToServer(Minecraft.getMinecraft().player));
		}
		
		NetworkHandler.sendToServer(new MessageMarketUpdate((byte)b.id));
	}
	
}
