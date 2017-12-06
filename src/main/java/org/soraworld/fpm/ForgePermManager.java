package org.soraworld.fpm;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.soraworld.fpm.api.ServerPermAPI;
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
        MinecraftForge.EVENT_BUS.register(new Object() {
            @SubscribeEvent
            public void onplayer(PlayerInteractEvent.LeftClickBlock event1) {
                System.out.println("KKK" + event1.getEntityPlayer());
            }
        });
        proxy.registerEventHandler();
    }

    @Mod.EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        ServerPermAPI.getPermManager().saveAll();
    }
}
