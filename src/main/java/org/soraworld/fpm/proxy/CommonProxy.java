package org.soraworld.fpm.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.soraworld.fpm.Constants;
import org.soraworld.fpm.handler.EventBusHandler;

public class CommonProxy {

    final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MOD_ID);
    int MSG_ID = 0;

    public void registerEventHandler() {
        System.out.println("CommonProxy.registerEventHandler");
        MinecraftForge.EVENT_BUS.register(new EventBusHandler());
        //MessageManager.getNetwork().registerMessage(MessageHandler.class, TestMessage.class, 1, Side.SERVER);
    }

}
