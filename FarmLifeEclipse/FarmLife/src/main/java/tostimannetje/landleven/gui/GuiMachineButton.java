package tostimannetje.landleven.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;

public class GuiMachineButton extends GuiButton{

	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/guibuttons.png");
	protected ResourceLocation ITEM_TEXTURE;
	public boolean isSelected;
	private int iconx;
	private int icony;
	
	public GuiMachineButton(int buttonId, int x, int y, int widthIn, int heightIn, final ResourceLocation itemTexture) {
		super(buttonId, x, y, widthIn, heightIn, "");
		this.ITEM_TEXTURE = itemTexture;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        int i = this.getHoverState(this.hovered);
        int j = this.getSelected();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        this.drawTexturedModalRect(this.x, this.y, 0 + j*22, 0 + (i-1)*22, this.width, this.height);
        drawIcon(mc, ITEM_TEXTURE);
        this.mouseDragged(mc, mouseX, mouseY);
    }
	
	public void drawIcon(Minecraft mc, ResourceLocation ITEM_TEXTURE){
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(ITEM_TEXTURE);
        this.drawModalRectWithCustomSizedTexture(this.x + 3, this.y + 3, 0, 0, 16, 16, 16, 16);
	}
	
	//0 = not selected, 1 = selected
	public int getSelected(){
		return isSelected ?  1 :  0;
	}
	
	
}
