package tostimannetje.landleven.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;

public class GuiMarketButton extends GuiButton{

	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/guimarket.png");
	private String text = "";
	
	public GuiMarketButton(String name, int buttonId, int x, int y, int widthIn, int heightIn) {
		super(buttonId, x, y, widthIn, heightIn, name);
		text = name;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        int i = this.getHoverState(this.hovered);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        if(text.equals("Auto")) {
        	this.drawTexturedModalRect(this.x, this.y, 176, 30 + (i-1)*15, this.width, this.height);
        	mc.fontRenderer.drawString(text, this.x + 1 + width / 2 - mc.fontRenderer.getStringWidth(text) / 2, this.y + (height- mc.fontRenderer.FONT_HEIGHT)/2+1, 0x404040);
        }else { 
        	this.drawTexturedModalRect(this.x, this.y, 176, 0 + (i-1)*15, this.width, this.height);
        	if(text.equals("Collect")) {
        		mc.fontRenderer.drawString(text, this.x + 1 + width / 2 - mc.fontRenderer.getStringWidth(text) / 2, this.y + (height- mc.fontRenderer.FONT_HEIGHT)/2+1, 0x404040);
        	}
        }
        this.mouseDragged(mc, mouseX, mouseY);
    }
	
}
