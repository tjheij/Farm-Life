package tostimannetje.landleven;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tostimannetje.landleven.questing.QuestProvider;

public class CapabilityHandler{

	public static final ResourceLocation QUEST = new ResourceLocation(Reference.MODID, "quest");

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent event){
		if (!(event.getObject() instanceof EntityPlayer)) return;
		
		event.addCapability(QUEST, new QuestProvider((EntityPlayer)event.getObject()));
	}
}
