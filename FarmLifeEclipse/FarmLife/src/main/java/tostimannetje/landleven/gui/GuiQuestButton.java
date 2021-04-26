package tostimannetje.landleven.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.init.ModBlocks;
import tostimannetje.landleven.questing.QuestBase;
import tostimannetje.landleven.questing.QuestLine;
import tostimannetje.landleven.questing.QuestBase.QuestState;
import tostimannetje.landleven.questing.QuestLine.State;

public class GuiQuestButton extends GuiButton{

	protected static final ResourceLocation QUEST = new ResourceLocation(Reference.MODID, "textures/gui/guiquestbutton.png");
	protected ResourceLocation ITEM_TEXTURE;
	private String text;
	private State state;
	private QuestState questState;
	private int iconx;
	private int icony;
	private QuestLine questLine;
	private QuestBase quest;
	private boolean isCheckButton;
	
	public GuiQuestButton(int buttonId, QuestLine questLine, int x, int y, int width, int height, final ResourceLocation itemTexture) {
		super(buttonId, x, y, width, height, questLine.getName());
		this.ITEM_TEXTURE = itemTexture;
		this.text = questLine.getName();
		this.questLine = questLine;
		this.state = questLine.getState();
	}
	
	public GuiQuestButton(int buttonId, QuestBase quest, int x, int y, int width, int height, final ResourceLocation itemTexture) {
		super(buttonId, x, y, width, height, quest.getProgress()+"/"+quest.getGoal());
		this.ITEM_TEXTURE = itemTexture;
		this.text = quest.getProgress()+"/"+quest.getGoal();
		this.quest = quest;
		this.questState = quest.getQuestState();
	}
	
	public GuiQuestButton(int buttonId, String checkButton, int x, int y, int width, int height, final ResourceLocation itemTexture) {
		super(buttonId, x, y, width, height, checkButton);
		this.ITEM_TEXTURE = itemTexture;
		this.text = checkButton;
		this.isCheckButton = true;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks){
        mc.getTextureManager().bindTexture(QUEST);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        int i = this.getHoverState(this.hovered);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        if(state == State.COMPLETED || questState == QuestState.COMPLETED) {
        	drawTexturedModalRect(this.x, this.y, 0, 66, width, height);
        }else if(state == State.INPROGRESS || questState == QuestState.INPROGRESS || isCheckButton) {
        	drawTexturedModalRect(this.x, this.y, 0, 0 + (i-1)*22, width, height);
        }else {
        	drawTexturedModalRect(this.x, this.y, 0, 44, width, height);
        }
        drawIcon(mc, ITEM_TEXTURE);
        drawText(mc, text);
        this.mouseDragged(mc, mouseX, mouseY);
    }
	
	public void drawIcon(Minecraft mc, ResourceLocation ITEM_TEXTURE){
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(ITEM_TEXTURE);
        this.drawModalRectWithCustomSizedTexture(this.x + 3, this.y + 3, 0, 0, 16, 16, 16, 16);
	}
	
	public void drawText(Minecraft mc, String text) {
		mc.fontRenderer.drawString(text, this.x + width / 2 - mc.fontRenderer.getStringWidth(text) / 2 + 8, this.y + (height- mc.fontRenderer.FONT_HEIGHT)/2, 0x404040);
	}
}
