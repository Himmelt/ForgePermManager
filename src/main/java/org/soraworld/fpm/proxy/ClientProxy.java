package org.soraworld.fpm.proxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    //private final ClientPermManager client = ClientPermManager.getInstance();

    /*public void registerEventHandler() {
        super.registerEventHandler();
        network.registerMessage((entire, ctx) -> {
            //client.download(entire);
            return null;
        }, EntireMessage.class, MSG_ID++, Side.CLIENT);
        network.registerMessage((entire, ctx) -> {
            //client.receiveNodeMsg(entire);
            return null;
        }, GroupMsg.class, MSG_ID++, Side.CLIENT);
    }*/
}
