package org.soraworld.fpm.proxy;

import net.minecraftforge.common.MinecraftForge;
import org.soraworld.fpm.handler.ClientEventHandler;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        super.registerEventHandler();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
