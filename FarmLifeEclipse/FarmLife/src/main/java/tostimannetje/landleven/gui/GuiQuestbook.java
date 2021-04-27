package tostimannetje.landleven.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestLine;
import tostimannetje.landleven.questing.QuestProvider;
import tostimannetje.landleven.questing.QuestBase;
import tostimannetje.landleven.questing.QuestBase.QuestState;
import tostimannetje.landleven.questing.QuestLine.State;

public class GuiQuestbook extends GuiScreen{

	private static final ResourceLocation GUI = new ResourceLocation(Reference.MODID, "textures/gui/guiquestbook.png");
	private static final ResourceLocation GUIQUEST = new ResourceLocation(Reference.MODID, "textures/gui/guiquest.png");
	private List<GuiQuestButton> buttons = Lists.<GuiQuestButton>newArrayList();
	private QuestLine questLine;
	private QuestBase quest;
	private int layer = 0;
	
	private int xSize = 176;
	private int ySize = 166;
	
	public GuiQuestbook() {
	}
	
	@Override
	public void initGui()
	{
		buttonList.clear();
		buttons.clear();
		Keyboard.enableRepeatEvents(true);
		IQuest questCapability = Minecraft.getMinecraft().player.getCapability(QuestProvider.QUEST, null);
		ArrayList<QuestLine> questLines = questCapability.getQuestLines();
		
		for(int i = 0; i < questLines.size(); i++) {
			this.buttons.add(new GuiQuestButton( i, questLines.get(i), 14 + (width - xSize) / 2, 22 + (height-ySize)/2 + i*22, 148, 22, questLines.get(i).getIcon()));
		}
		this.buttonList.addAll(buttons);
	}
	
	public void initGuiQuestLine(QuestLine questLine) {
		buttonList.clear();
		buttons.clear();

		ArrayList<QuestBase> quests = questLine.getQuests();
		for(int i = 0; i < quests.size(); i++) {
			this.buttons.add(new GuiQuestButton( i, quests.get(i), 14 + (width - xSize) / 2, 22 + (height-ySize)/2 + i*22, 148, 22, quests.get(i).getIcon()));
		}
		this.buttonList.addAll(buttons);
	}
	
	public void initGuiQuest(QuestBase quest) {
		buttonList.clear();
		buttons.clear();
		String questText = "";
		
		questText = "Produce "+quest.getGoal()+" "+I18n.format(quest.getItem().getUnlocalizedName() + ".name");
		
		fontRenderer.drawString(questText, width/2 - fontRenderer.getStringWidth(questText) / 2, 6 + height/2, 0x404040);
	}
	
	@Override
	public void updateScreen()
	{	
		super.updateScreen();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		if(layer == 0) {
			drawQuestBook(mouseX, mouseY, partialTicks);
		}else if(layer == 1) {
			drawQuestLine(mouseX, mouseY, partialTicks);
		}else {
			drawQuest(mouseX, mouseY, partialTicks);
		}
	}
	
	private void drawQuestBook(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		mc.getTextureManager().bindTexture(GUI);
		IQuest questCapability = Minecraft.getMinecraft().player.getCapability(QuestProvider.QUEST, null);
		int questSize = questCapability.getQuestLines().size();
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawModalRectWithCustomSizedTexture(x, y, 0, 0, 176, 22, 256, 256);
		for(int i = 0; i < questSize; i++) {
			drawModalRectWithCustomSizedTexture(x, y + 22 + i*22, 0, 23, 176, 22, 256, 256);
		}
		drawModalRectWithCustomSizedTexture(x, y + 22 + questSize * 22, 0, 46, 176, 18, 256, 256);
		String text = "Questbook";
		fontRenderer.drawString(text, width / 2 - fontRenderer.getStringWidth(text) / 2, y + 8, 0x404040);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	private void drawQuestLine(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		mc.getTextureManager().bindTexture(GUI);
		int questSize = questLine.getQuests().size();
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawModalRectWithCustomSizedTexture(x, y, 0, 0, 176, 22, 256, 256);
		for(int i = 0; i < questSize; i++) {
			drawModalRectWithCustomSizedTexture(x, y + 22 + i*22, 0, 23, 176, 22, 256, 256);
		}
		drawModalRectWithCustomSizedTexture(x, y + 22 + questSize * 22, 0, 46, 176, 18, 256, 256);
		String text = questLine.getName();
		fontRenderer.drawString(text, width / 2 - fontRenderer.getStringWidth(text) / 2, y + 8, 0x404040);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	private void drawQuest(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
		mc.getTextureManager().bindTexture(GUIQUEST);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		String questText = "Produce "+quest.getGoal()+" "+I18n.format(quest.getItem().getUnlocalizedName() + ".name");
		fontRenderer.drawString(questText, width / 2 - fontRenderer.getStringWidth(questText) / 2, y + 24, 0x404040);
		GlStateManager.color(1, 1, 1, 1);
		mc.getTextureManager().bindTexture(quest.getIcon());
		drawScaledCustomSizeModalRect(x + 57, y + 52, 0, 0, 16, 16, 64, 64, 16, 16);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	protected void actionPerformed(GuiButton b){
		IQuest questCapability = Minecraft.getMinecraft().player.getCapability(QuestProvider.QUEST, null);
		if(layer == 0) {//Main to questline
			questLine = questCapability.getQuestLines().get(b.id);
			if(questLine.getState() == State.INPROGRESS){
				initGuiQuestLine(questLine);
				layer = 1;
			}
		}else if (layer == 1) {//questline to quest
			quest = questLine.getQuests().get(b.id);
			if(quest.getQuestState() == QuestState.INPROGRESS) {
				initGuiQuest(quest);
				layer = 2;
			}
		}else {
			if(quest.isCompleted()) {
				quest.completeQuest();
				ArrayList<QuestBase> quests = questLine.getQuests();
				int questIndex = quests.indexOf(quest);
				if(quests.size()-1 >= questIndex + 1) {
					quest = quests.get(quests.indexOf(quest)+1);
					quest.setQuestState(QuestState.INPROGRESS);
				}
			}
				
		}
	}
	
	@Override
	public void onGuiClosed()
    {
		layer = 0;
    }
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        if (keyCode == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(keyCode))
        {
            this.mc.player.closeScreen();
        }
    }
}
