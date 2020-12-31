package tostimannetje.landleven.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.tileentity.TileEntityAnimal;
import tostimannetje.landleven.tileentity.TileEntityProducer;

public class GuiAnimal extends GuiContainer{

	private static final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/guianimal.png");
	
	private TileEntityProducer te;
	private InventoryPlayer playerInv;
	
	public GuiAnimal(InventoryPlayer playerInv, Container container, TileEntityAnimal te) {
		super(container);
		this.te = te;
		this.playerInv = playerInv;
		this.ySize = 172;
	}

	 @Override
	 public void initGui(){
	  super.initGui();
	 }
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(GUI);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MODID, "textures/items/"+ te.getInput().getUnlocalizedName().substring(5) +".png"));
		drawModalRectWithCustomSizedTexture(x + 80, y + 24, 0, 0, 16, 16, 16, 16);
		
		mc.getTextureManager().bindTexture(GUI);
		int progress = this.getProgressScaled(24);
        this.drawTexturedModalRect(x + 76, y + 58, 176, 0, progress + 1, 16);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String name = this.te.getDisplayName().getUnformattedText();
		fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, 79, 4210752);
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
		
		int x = (width - xSize) / 2 + 77;
		int y = (height - ySize) / 2 + 21;
		if(mouseX > x && mouseX <= x + 22 && mouseY > y && mouseY <= y + 22) {
			this.drawHoveringText(I18n.format(te.getInput().getUnlocalizedName() + ".name"), mouseX, mouseY);
		}
	}
	
	private int getProgressScaled(int pixels){
        int processTime = this.te.getField(0);
        int processTimeNeeded = this.te.getField(1);
        return processTimeNeeded != 0 && processTime != 0 ? processTime * pixels / processTimeNeeded : 0;
    }
}
