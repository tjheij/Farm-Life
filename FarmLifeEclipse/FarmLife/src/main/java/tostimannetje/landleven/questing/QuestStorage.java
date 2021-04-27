package tostimannetje.landleven.questing;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import tostimannetje.landleven.questing.QuestBase.QuestState;

public class QuestStorage implements IStorage<IQuest>{

	@Override
	public NBTBase writeNBT(Capability<IQuest> capability, IQuest instance, EnumFacing side) {
		NBTTagList nbtTagListIn = new NBTTagList();
		
		for (int i = 0; i < instance.getQuestLines().size(); ++i){
			for(int j = 0; j < instance.getQuestLines().get(i).getQuests().size(); j++) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.setString("Quest"+i+"-"+j, instance.getQuest(i,j).getQuestState().name());
            nbtTagListIn.appendTag(nbttagcompound);
			}
        }
		NBTTagCompound nbtCoins = new NBTTagCompound();
		nbtCoins.setInteger("Coins", instance.getCoins());
		nbtCoins.setBoolean("FirstTime", instance.getFirstTime());
		nbtTagListIn.appendTag(nbtCoins);
		
		return nbtTagListIn;
	}

	@Override
	public void readNBT(Capability<IQuest> capability, IQuest instance, EnumFacing side, NBTBase nbt) {
		NBTTagList nbtTagListIn = (NBTTagList) nbt;
		
		int i = 0;
		int j = 0;
		
		for (int k = 0; k < nbtTagListIn.tagCount()-1; ++k){
			if(instance.getQuest(i,j) != null) {
				NBTTagCompound nbttagcompound = nbtTagListIn.getCompoundTagAt(k);
            	instance.getQuest(i,j).setQuestState(QuestState.valueOf(nbttagcompound.getString("Quest"+i+"-"+j)));
            	if(instance.getQuest(i,j).getQuestState() == QuestState.COMPLETED) {
            		instance.getQuest(i,j).setProgress(instance.getQuest(i,j).getGoal());
            	}
            	if(instance.getQuestLines().get(i).getQuests().size()-1 > j) {
            		j++;
            	}else {
            		i++;
            		j = 0;
            	}
			}
        }
		NBTTagCompound nbtCoins = nbtTagListIn.getCompoundTagAt(nbtTagListIn.tagCount()-1);
		instance.setCoins(nbtCoins.getInteger("Coins"));
		instance.setFirstTime(nbtCoins.getBoolean("FirstTime"));
	}

}
