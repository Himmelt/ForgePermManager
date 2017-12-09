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
        MinecraftForge.EVENT_BUS.register(new EventBusHandler());
    }

    public SimpleNetworkWrapper getNetwork() {
        return network;
    }

}
