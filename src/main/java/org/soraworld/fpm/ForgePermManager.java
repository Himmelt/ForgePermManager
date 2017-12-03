package org.soraworld.fpm;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.soraworld.fpm.handler.EventHandler;

@Mod(
        modid = "fpm",
        name = "ForgePermManager",
        version = "1.0"
)
public class ForgePermManager {

    @Mod.EventHandler
    void Init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

}
