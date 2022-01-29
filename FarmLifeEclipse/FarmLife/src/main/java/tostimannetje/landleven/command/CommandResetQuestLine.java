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
import tostimannetje.landleven.questing.QuestLine;
import tostimannetje.landleven.questing.QuestProvider;

public class CommandResetQuestLine extends CommandBase{

	private final List<String> aliases = new ArrayList<String>();
	
	@Override
	public String getName() {
		return "resetQuestLine";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "resetQuestLine <questLine>";
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
		if(args.length < 1) {
			return;
		}
		
		String sql = args[0];
		int questLine = 0;
		
		try {
			questLine = Integer.parseInt(sql);
		}catch(NumberFormatException e){
			sender.sendMessage(new TextComponentString("Questline not valid"));
		}
		
		if(sender instanceof EntityPlayer) {
			IQuest iquest = ((EntityPlayer) sender).getCapability(QuestProvider.QUEST, null);
			
			if(questLine >= iquest.getQuestLines().size()) {
				sender.sendMessage(new TextComponentString("Questline does not exist"));
			}else {
				iquest.resetQuest(questLine,0);
			}
			
			QuestLine line = iquest.getQuestLine(0);
			for(int i = 0; i < line.getQuests().size(); i++) {
				System.out.println("Command: " + line.getQuest(i).getQuestState());
			}
		}
		
		NetworkHandler.sendToPlayer(new MessageResetQuestToClient(questLine,0), (EntityPlayerMP) sender);
		sender.sendMessage(new TextComponentString("Reset questline " + questLine));
	}

}
