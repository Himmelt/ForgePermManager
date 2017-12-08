package org.soraworld.fpm.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.client.ClientPermManager;
import org.soraworld.fpm.client.handler.ClientEventHandler;
import org.soraworld.fpm.core.Group;
import org.soraworld.fpm.core.Permission;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        super.registerEventHandler();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        network.registerMessage((perm, ctx) -> {
            ClientPermManager.getInstance().download(perm);
            return null;
        }, Permission.class, MSG_ID++, Side.CLIENT);
        network.registerMessage((group, ctx) -> {
            ClientPermManager.getInstance().download(group);
            return null;
        }, Group.class, MSG_ID++, Side.CLIENT);
    }
}
