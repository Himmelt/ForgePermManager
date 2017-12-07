package org.soraworld.fpm.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.handler.ClientEventHandler;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        System.out.println("ClientProxy.registerEventHandler->super");
        super.registerEventHandler();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
