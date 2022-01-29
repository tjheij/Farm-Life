package tostimannetje.landleven.command;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import tostimannetje.landleven.network.MessageResetQuestToClient;
import tostimannetje.landleven.network.NetworkHandler;
import tostimannetje.landleven.questing.IQuest;
import tostimannetje.landleven.questing.QuestProvider;

public class CommandResetAllQuests extends CommandBase{

	private final List<String> aliases = new ArrayList<String>();
	
	@Override
	public String getName() {
		return "resetAllQuests";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "resetAllQuests";
	}

	@Override
	public List<String> getAliases(){
		return aliases;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayer) {
			IQuest iquest = ((EntityPlayer) sender).getCapability(QuestProvider.QUEST, null);
			iquest.resetAllQuests();
			NetworkHandler.sendToPlayer(new MessageResetQuestToClient(-1,0), (EntityPlayerMP) sender);
			sender.sendMessage(new TextComponentString("Reset all quests"));
		}
	}

}
