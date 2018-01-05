package org.soraworld.fpm.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.api.ForgePermAPI;
import org.soraworld.fpm.core.PermissionManager;

import java.io.File;

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

    public void preInit(FMLPreInitializationEvent event) {
        permManager = new PermissionManager(new File(event.getModConfigurationDirectory(), "fpm"), Side.CLIENT);
        ForgePermAPI.setPermManager(permManager);
    }

}
