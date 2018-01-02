package org.soraworld.fpm;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.soraworld.fpm.api.ForgePermAPI;
import org.soraworld.fpm.api.PermManager;
import org.soraworld.fpm.config.Config;
import org.soraworld.fpm.core.PermissionManager;
import org.soraworld.fpm.proxy.CommonProxy;
import org.soraworld.fpm.storage.StorageManager;

import java.io.File;

@Mod(
        modid = Constants.MOD_ID,
        name = Constants.MOD_NAME,
        version = Constants.MOD_VERSION
)
public class FPManager {

    @SidedProxy(clientSide = Constants.CLIENT_PROXY, serverSide = Constants.SERVER_PROXY)
    private static CommonProxy proxy;

    public static final Logger LOGGER = LogManager.getLogger(Constants.MOD_ID);

    @Mod.EventHandler
    public void Init(FMLPreInitializationEvent event) {
        PermManager manager = ForgePermAPI.getPermManager();
        if (manager instanceof PermissionManager) {
            PermissionManager fpm = (PermissionManager) manager;
            fpm.setConfig(new Config(new File(event.getModConfigurationDirectory(), "fpm/settings.cfg"), Constants.MOD_VERSION));
            fpm.setStorageManager(new StorageManager(event.getModConfigurationDirectory()));
            fpm.loadGroups();
        }
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.registerEventHandler();
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        //event.registerServerCommand(new ModCommand("fpm"));
        //ServerPermManager.getInstance().loadGroups();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        //ServerPermManager.getInstance().saveAll();
    }

    public static CommonProxy getProxy() {
        return proxy;
    }
}
