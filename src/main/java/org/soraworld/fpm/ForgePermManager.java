package org.soraworld.fpm;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.soraworld.fpm.proxy.CommonProxy;
import org.soraworld.fpm.server.ServerPermManager;

@Mod(
        modid = Constants.MOD_ID,
        name = Constants.MOD_NAME,
        version = Constants.MOD_VERSION
)
public class ForgePermManager {

    @SidedProxy(clientSide = Constants.CLIENT_PROXY, serverSide = Constants.SERVER_PROXY)
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.registerEventHandler();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerPermManager.getInstance().saveAll();
    }

    public static CommonProxy getProxy() {
        return proxy;
    }
}
