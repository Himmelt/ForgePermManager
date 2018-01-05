package org.soraworld.fpm.proxy;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.soraworld.fpm.api.ForgePermAPI;
import org.soraworld.fpm.core.PermissionManager;

import java.io.File;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy {

    @Override
    public void registerEventHandler() {
        super.registerEventHandler();
    }

    public void preInit(FMLPreInitializationEvent event) {
        permManager = new PermissionManager(new File(event.getModConfigurationDirectory(), "fpm"), Side.SERVER);
        ForgePermAPI.setPermManager(permManager);
    }
}
