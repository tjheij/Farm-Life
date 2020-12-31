package tostimannetje.landleven.gui;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.network.MessageMachineSelect;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.tileentity.TileEntityMachine;

public class GuiMachine extends GuiContainer{

	private static final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/guimachine.png");
	private List<GuiMachineButton> buttons = Lists.<GuiMachineButton>newArrayList();
	private final InventoryPlayer playerInv;
	
	private TileEntityMachine te;
	private ItemStack[] inputList;
	private ItemStack[] extraInputList;
	
	private int guiHeight = 0;
	
	public GuiMachine(InventoryPlayer playerInv, Container container, TileEntityMachine te) {
		super(container);
		this.playerInv = playerInv;
		this.te = te;
		this.inputList = te.getInputList();
		this.extraInputList = te.getExtraInputList();
	}

	@Override
	public void initGui(){
		super.initGui();
		this.buttons.clear();
		System.out.println("INIT GUI");
		int i = (this.width - this.xSize) / 2 + 31;
		int j = (this.height - this.ySize) / 2 + 17;
		int buttonsCreated = 0;
			  
		for(int row = 0; row < (int)Math.floor(inputList.length / 5.0); row++) {
			for(int column = 0; column < 5; column++) {
				this.buttons.add(new GuiMachineButton( 
						buttonsCreated, i + column*23, j, 22, 22, new ResourceLocation(Reference.MODID, "textures/items/"+ inputList[buttonsCreated].getItem().getUnlocalizedName().substring(5) +".png")));
				buttonsCreated++;
			}
			j+=23;
		}	  
		int extraWidth = (inputList.length % 5) * 23;
		for(int column = 0; column < inputList.length % 5; column++) {
			this.buttons.add(new GuiMachineButton( 
					buttonsCreated, (this.width - extraWidth)/2 + 1 + column*23, j, 22, 22, new ResourceLocation(Reference.MODID, "textures/items/"+ inputList[buttonsCreated].getItem().getUnlocalizedName().substring(5) +".png")));
				buttonsCreated++;
		}
			
		this.buttonList.addAll(buttons);
	  
		for(int x = 0; x < buttons.size(); x++){
			if(buttonList.get(x).id == te.getField(2)){
				buttons.get(x).isSelected = true;
			}
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(GUI);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		//Title
		drawTexturedModalRect(x, y, 0, 0, xSize, 17);
		y += 17;
		
		for(int i = 0; i < (int)Math.ceil(inputList.length / 5.0); i++) {
			//Select inputlist
			drawTexturedModalRect(x, y, 0, 18, xSize, 26);
			y+= 26;
		}
		
		//Extra inputs
		if(extraInputList.length > 0) {
			int extraWidth = (extraInputList.length % 5) * 19;
			drawTexturedModalRect(x, y, 0, 45, xSize, 19);
			for(int j = 0; j < 5 && j < extraInputList.length; j++) {
				mc.getTextureManager().bindTexture(GUI);
				drawTexturedModalRect((this.width - extraWidth)/2 + j*19, y, 176, 16, 18, 18);
				
				GlStateManager.pushMatrix();
		    	GlStateManager.enableAlpha();
		    	GlStateManager.enableBlend();
		    	GlStateManager.color(1, 1, 1, 0.5f);
				mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID, "textures/items/"+ extraInputList[j].getItem().getUnlocalizedName().substring(5) +".png"));
				drawModalRectWithCustomSizedTexture((this.width - extraWidth)/2 + j*19+1, y+1, 0, 0, 16, 16, 16, 16);
				GlStateManager.disableBlend();
			    GlStateManager.disableAlpha();
			    GlStateManager.popMatrix();
			}
			y+= 19;
		}
		
		mc.getTextureManager().bindTexture(GUI);
		//Inventory
		drawTexturedModalRect(x, y, 0, 65, xSize, 118);
		
		int progress = this.getProgressScaled(24);
        this.drawTexturedModalRect(x + 76, y + 4, 176, 0, progress + 1, 16);
    }

    private int getProgressScaled(int pixels){
    	int processTime = this.te.getField(0);
        int processTimeNeeded = this.te.getField(1);
        return processTimeNeeded != 0 && processTime != 0 ? processTime * pixels / processTimeNeeded : 0;
    }

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		guiHeight = 17 + 26*(int)Math.ceil(inputList.length/5.0) + 19*(int)Math.ceil(extraInputList.length/5.0);
		String name = this.te.getDisplayName().getUnformattedText();
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, guiHeight + 24, 4210752);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		
		for(int i = 0; i < buttons.size();i++) {
			if(buttons.get(i).isMouseOver()) {
				this.drawHoveringText(inputList[i].getDisplayName(), mouseX, mouseY);
			}
		}
		
		for(int i = 0; i < extraInputList.length;i++) {
			int x = (width - (extraInputList.length % 5) * 19) / 2 + i*19;
			System.out.println((int)Math.ceil(buttons.size()/5.0));
			//int y = (height - (17 + 26*(int)Math.ceil(buttons.size()/5.0))) / 2 - 19;
			int y = (height - ySize) / 2 + 17 + 26*(int)Math.ceil(buttons.size()/5.0);
			if(mouseX > x && mouseX <= x + 18 && mouseY > y && mouseY <= y + 18) {
				this.drawHoveringText("Extra input: " + extraInputList[i].getDisplayName(), mouseX, mouseY);
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton b){
		for(int i = 0; i < buttonList.size(); i++){
			deactivateButton(buttons.get(i));
		}
		
		activateButton((GuiMachineButton) b);
		NetworkHandler.sendToServer(new MessageMachineSelect((byte)b.id));
	}
	
	public void activateButton(GuiMachineButton b){
		b.isSelected = true;
	}
	
	public void deactivateButton(GuiMachineButton b){
		b.isSelected = false;
	}
}
