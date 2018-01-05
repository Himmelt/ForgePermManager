package org.soraworld.fpm.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import org.soraworld.fpm.Constants;
import org.soraworld.fpm.core.PermissionManager;
import org.soraworld.fpm.handler.EventBusHandler;
import org.soraworld.fpm.handler.FMLServerEventHandler;

public class CommonProxy {

    final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MOD_ID);
    PermissionManager permManager;
    int MSG_ID = 0;

    public void registerEventHandler() {
        MinecraftForge.EVENT_BUS.register(new EventBusHandler());
        MinecraftForge.EVENT_BUS.register(new FMLServerEventHandler());
    }

    public SimpleNetworkWrapper getNetwork() {
        return network;
    }

    public void preInit(FMLPreInitializationEvent event) {

    }

}
