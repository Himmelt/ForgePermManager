package org.soraworld.fpm.proxy;

import net.minecraftforge.common.MinecraftForge;
import org.soraworld.fpm.handler.EventBusHandler;

public class CommonProxy {

    public void registerEventHandler() {
        System.out.println("CommonProxy.registerEventHandler");
        MinecraftForge.EVENT_BUS.register(new EventBusHandler());
        //MessageManager.getNetwork().registerMessage(MessageHandler.class, TestMessage.class, 1, Side.SERVER);
        //MessageManager.getNetwork().registerMessage(MessageHandler.class, TestMessage.class, 2, Side.CLIENT);
    }

    public PipeHead getPermManager() {
        return null;
    }
}
