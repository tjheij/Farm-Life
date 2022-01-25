package tostimannetje.landleven.gui;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.SlotItemHandler;
import tostimannetje.landleven.Reference;
import tostimannetje.landleven.container.ContainerTractor;
import tostimannetje.landleven.container.SlotFertilizer;
import tostimannetje.landleven.container.SlotSeedBag;
import tostimannetje.landleven.entity.EntityTractor;
import tostimannetje.landleven.entity.EntityTractor.TractorModes;
import tostimannetje.landleven.init.ModItems;
import tostimannetje.landleven.network.MessageMachineSelect;
import tostimannetje.landleven.network.NetworkHandler;

@SideOnly(Side.CLIENT)
public class GuiTractor extends GuiContainer{

	private static final ResourceLocation TRACTOR_GUI_TEXTURE = new ResourceLocation(Reference.MODID, "textures/gui/guitractor.png");
	private static final String[] MODE_TEXTURES = new String[] {ModItems.itemSeedBag.getUnlocalizedName(), ModItems.itemFruitBasket.getUnlocalizedName(), ModItems.itemFertilizer.getUnlocalizedName()};
	private List<GuiMachineButton> buttons = Lists.<GuiMachineButton>newArrayList();
    private InventoryPlayer playerInventory;
    private EntityTractor tractor;

    public GuiTractor(InventoryPlayer playerInventory, Container container, EntityTractor tractor) {
        super(container);
        this.playerInventory = playerInventory;
        this.xSize = 176;
        this.ySize = 200;
        this.tractor = tractor;
    }

    @Override
	public void initGui(){
		super.initGui();
		this.buttons.clear();
		int i = (this.width - this.xSize) / 2 + 7;
		int j = (this.height - this.ySize) / 2 + 15;
		int buttonsCreated = 0;
			  
		for(int buttonX = 0; buttonX < 3; buttonX++) {
			this.buttons.add(new GuiMachineButton( 
					buttonsCreated, i + buttonX*31, j, 22, 22, new ResourceLocation(Reference.MODID, "textures/items/"+ MODE_TEXTURES[buttonsCreated].substring(5) +".png")));
			buttonsCreated++;
		}	  
			
		this.buttonList.addAll(buttons);
	  
		for(int x = 0; x < buttons.size(); x++){
			if(buttonList.get(x).id == tractor.getTractorMode().getValue()){
				buttons.get(x).isSelected = true;
			}
		}
	}
    
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        
        refresh();
        
        for(int i = 0; i < buttons.size();i++) {
			if(buttons.get(i).isMouseOver()) {
				String mode = TractorModes.values()[i].toString();
				mode = mode.substring(0,1) + mode.substring(1).toLowerCase();
				this.drawHoveringText(mode, mouseX, mouseY);
			}
		}
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRenderer.drawString(new TextComponentTranslation("container.tractor.name").getUnformattedText(), 8, 6, 4210752);
        
        String inventory = "";
        switch(tractor.getTractorMode()) {
        case PLANTING:
        	inventory = new TextComponentTranslation("container.tractor_seeds.name").getUnformattedText();
        	break;
        case HARVESTING:
        	inventory = new TextComponentTranslation("container.tractor_harvest.name").getUnformattedText();
        	break;
        case FERTILIZING:
        	inventory = new TextComponentTranslation("container.tractor_fertilizer.name").getUnformattedText();
        	break;
        }
        this.fontRenderer.drawString(inventory, 8, 39, 4210752);
        
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TRACTOR_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
        
        if(tractor.getTractorMode() == TractorModes.FERTILIZING) {
        	this.drawTexturedModalRect(i + 7, j + 50, 176, 0, 54, 54);
        	this.drawTexturedModalRect(i + 115, j + 50, 176, 0, 54, 54);
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
		tractor.setTractorMode(TractorModes.values()[b.id]);
	}
	
	public void deactivateButton(GuiMachineButton b){
		b.isSelected = false;
	}
	
	public void refresh() {
			
		ContainerTractor container = (ContainerTractor) this.inventorySlots;
		
		for(int i = 0; i < container.inventorySlots.size(); i++) {
			if(container.getSlot(i).yPos < 118 && container.getSlot(i).xPos > 0) {
				container.getSlot(i).xPos -= 1000;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			if(i == this.tractor.getTractorMode().getValue()) {
				if(i == 0) {
					for(int j = 0; j < 27; j++) {
						if(container.getSlot(j) instanceof SlotSeedBag) {
							container.getSlot(j).xPos += 1000;
						}
					}
				}else if(i == 1) {
					for(int j = 0; j < 27; j++) {
						if(container.getSlot(j+27) instanceof SlotItemHandler) {
							container.getSlot(j+27).xPos += 1000;
						}
					}
				}else if(i == 2) {
					for(int j = 0; j < 9; j++) {
						if(container.getSlot(j+54) instanceof SlotFertilizer) {
							container.getSlot(j+54).xPos += 1000;
						}
					}
				}
			}
		}
	}
}
