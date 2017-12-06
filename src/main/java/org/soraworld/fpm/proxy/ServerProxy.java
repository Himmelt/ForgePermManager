package org.soraworld.fpm.proxy;

import net.minecraftforge.common.MinecraftForge;
import org.soraworld.fpm.handler.EventBusHandler;

public class ServerProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        super.registerEventHandler();
        //MinecraftForge.EVENT_BUS.register(new FMLEventHandler());
        MinecraftForge.EVENT_BUS.register(new EventBusHandler());
    }
}
