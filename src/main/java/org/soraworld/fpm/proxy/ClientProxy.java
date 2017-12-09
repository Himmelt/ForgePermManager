package org.soraworld.fpm.proxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.client.ClientPermManager;
import org.soraworld.fpm.message.EntireMessage;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        super.registerEventHandler();
        //MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        network.registerMessage((entire, ctx) -> {
            ClientPermManager.getInstance().download(entire);
            return null;
        }, EntireMessage.class, MSG_ID++, Side.CLIENT);
    }
}
