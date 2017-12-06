package org.soraworld.fpm;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import org.soraworld.fpm.manager.ServerPermManager;
import org.soraworld.fpm.proxy.CommonProxy;

@Mod(
        modid = "fpm",
        name = "ForgePermManager",
        version = "1.0"
)
public class ForgePermManager {

    @SidedProxy(
            clientSide = "org.soraworld.fpm.proxy.ClientProxy",
            serverSide = "org.soraworld.fpm.proxy.ServerProxy"
    )
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        proxy.registerEventHandler();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerPermManager.getInstance().saveAll();
    }
}
