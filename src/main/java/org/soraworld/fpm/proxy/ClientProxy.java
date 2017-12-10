package org.soraworld.fpm.proxy;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.client.ClientPermManager;
import org.soraworld.fpm.message.EntireMessage;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private final ClientPermManager client = ClientPermManager.getInstance();

    @Override
    public void registerEventHandler() {
        super.registerEventHandler();
        network.registerMessage((entire, ctx) -> {
            client.download(entire);
            return null;
        }, EntireMessage.class, MSG_ID++, Side.CLIENT);
    }
}
