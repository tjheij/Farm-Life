package tostimannetje.landleven.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tostimannetje.landleven.Reference;

public class NetworkHandler {
	private static SimpleNetworkWrapper INSTANCE;
	
	public static void init(){
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);
		INSTANCE.registerMessage(MessageMachineSelect.Handler.class, MessageMachineSelect.class, 1, Side.SERVER);
		INSTANCE.registerMessage(MessageMarketUpdate.Handler.class, MessageMarketUpdate.class, 2, Side.SERVER);
		INSTANCE.registerMessage(MessageStoreBuy.Handler.class, MessageStoreBuy.class, 3, Side.SERVER);
		INSTANCE.registerMessage(MessageStoreSetQuantity.Handler.class, MessageStoreSetQuantity.class, 4, Side.SERVER);
		INSTANCE.registerMessage(MessageCoinsToClient.Handler.class, MessageCoinsToClient.class, 5, Side.CLIENT);
		INSTANCE.registerMessage(MessageQuestToClient.Handler.class, MessageQuestToClient.class, 6, Side.CLIENT);
		INSTANCE.registerMessage(MessageCoinsToServer.Handler.class, MessageCoinsToServer.class, 7, Side.SERVER);
		INSTANCE.registerMessage(MessageQuestProgressToClient.Handler.class, MessageQuestProgressToClient.class, 8, Side.CLIENT);
		INSTANCE.registerMessage(MessageQuestCompletedToClient.Handler.class, MessageQuestCompletedToClient.class, 9, Side.CLIENT);
		INSTANCE.registerMessage(MessageResetQuestToClient.Handler.class, MessageResetQuestToClient.class, 10, Side.CLIENT);
	}
	
	public static void sendToServer(IMessage message){
		INSTANCE.sendToServer(message);
	}
	
	public static void sentToAllAround(IMessage message, TargetPoint target){
		INSTANCE.sendToAllAround(message, target);
	}
	
	public static void sendToPlayer(IMessage message, EntityPlayerMP player) {
		INSTANCE.sendTo(message, player);
	}
}
