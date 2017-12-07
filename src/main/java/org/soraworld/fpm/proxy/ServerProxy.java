package org.soraworld.fpm.proxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        System.out.println("ServerProxy.registerEventHandler->super");
        super.registerEventHandler();
        //MinecraftForge.EVENT_BUS.register(new FMLEventHandler());
        //MinecraftForge.EVENT_BUS.register(new EventBusHandler());
    }
}
