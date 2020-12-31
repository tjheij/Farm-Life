package tostimannetje.landleven.questing;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class QuestProvider implements ICapabilitySerializable<NBTBase>{
	@CapabilityInject(IQuest.class)
	public static final Capability<IQuest> QUEST = null;
	
	private IQuest instance = QUEST.getDefaultInstance();
	
	private EntityPlayer player;
	
	public QuestProvider(EntityPlayer player) {
		this.player = player;
	}
	
	public EntityPlayer getPlayer() {
		return this.player;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing){
		return capability == QUEST;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing){
		return capability == QUEST ? QUEST.<T> cast(this.instance) : null;
	}
	
	@Override
	public NBTBase serializeNBT(){
		return QUEST.getStorage().writeNBT(QUEST, this.instance, null);
	}
	
	@Override
	public void deserializeNBT(NBTBase nbt){
		QUEST.getStorage().readNBT(QUEST, this.instance, null, nbt);
		instance.loadActiveQuest();
	}
}
