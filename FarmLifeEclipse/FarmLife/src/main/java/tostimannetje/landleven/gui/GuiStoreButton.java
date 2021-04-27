package tostimannetje.landleven.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;

public class GuiStoreButton extends GuiButton{

	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/guistorebuttons.png");
	protected ResourceLocation ITEM_TEXTURE;
	public boolean isSelected;
	private int iconx;
	private int icony;
	private int heightTexture;
	private String text = "";
	
	public GuiStoreButton(int buttonId, int x, int y, int textureX, int textureY,int widthIn, int heightIn, int heightTexture) {
		super(buttonId, x, y, widthIn, heightIn, "");
		this.iconx = textureX;
		this.icony = textureY;
		this.heightTexture = heightTexture;
	}
	
	public GuiStoreButton(String name, int buttonId, int x, int y, int textureX, int textureY,int widthIn, int heightIn, int heightTexture) {
		super(buttonId, x, y, widthIn, heightIn, name);
		this.iconx = textureX;
		this.icony = textureY;
		this.text = name;
		this.heightTexture = heightTexture;
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
        this.drawTexturedModalRect(this.x, this.y, this.iconx, this.icony + (i-1)*this.heightTexture, this.width, this.height);
        if(!text.equals("")) {
        	mc.fontRenderer.drawString(this.text, this.x + this.width / 2 - mc.fontRenderer.getStringWidth(text) / 2, this.y + (this.height- mc.fontRenderer.FONT_HEIGHT)/2, 0x404040);
        }
        this.mouseDragged(mc, mouseX, mouseY);
    }
}
